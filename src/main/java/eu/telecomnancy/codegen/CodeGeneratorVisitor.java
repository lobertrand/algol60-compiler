package eu.telecomnancy.codegen;

import static java.lang.String.*;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;

public class CodeGeneratorVisitor implements ASTVisitor<CodeInfo> {

    private SymbolTable currentSymbolTable;
    private int currentTableNumber;
    private Assembly asm;
    private String[] input;
    private UniqueReference uniqueReference;

    public CodeGeneratorVisitor(
            SymbolTable symbolTable, Assembly asm, UniqueReference uniqueReference) {
        PredefinedCode.appendAliases(asm);
        PredefinedCode.appendOutstringCode(asm);
        PredefinedCode.appendItoaCode(asm);
        PredefinedCode.appendOutintegerOrRealCode(asm);
        PredefinedCode.appendLineCode(asm);
        PredefinedCode.appendDiv0Code(asm);
        PredefinedCode.appendIndexOutOfBoundsCode(asm);
        PredefinedCode.appendOutbooleanCode(asm);
        PredefinedCode.appendEntierCode(asm);

        asm.newline();
        asm.def("ORG", "LOAD_ADRS", "adresse de chargement");
        asm.def("START", "main", "adresse de démarrage");
        //        asm.code("LDW HP,HEAP_ADRS", "adresse de chargement du tas");
        asm.newline();
        asm.byteDef("NEWLINE", 10);

        asm.comment("Définitions de constantes");
        asm.putStringDefinitionsHere();
        asm.code("LDW HP, @0xA000", "initializing heap pointer");

        this.currentSymbolTable = symbolTable;
        this.currentTableNumber = 0;
        this.asm = asm;
        this.uniqueReference = uniqueReference;
    }

    public void setInput(String inputCode) {
        this.input = inputCode.split("\n");
    }

    private String getLineOfCode(DefaultAST ast) {
        if (input == null) return "Use function setInput() to display lines of code";
        int index = ast.getLine() - 1;
        return input[index].trim();
    }

    private void pushTable() {
        currentSymbolTable = currentSymbolTable.getChildWithNumber(++currentTableNumber);
    }

    private void popTable() {
        if (currentSymbolTable.getParent() == null) {
            throw new IllegalStateException("Did more popTable() than pushTable()");
        }
        currentSymbolTable = currentSymbolTable.getParent();
    }

    @Override
    public CodeInfo visit(DefaultAST ast) {
        for (DefaultAST t : ast) {
            t.accept(this);
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(RootAST ast) {
        // Beginning of the program
        asm.label("main", "Entry point");

        // Main block instructions
        ast.getChild(0).accept(this);

        // Final operations
        asm.comment("White space at the end");
        asm.code("LDW R0, #NEWLINE", "Put newline byte in R0");
        asm.code("TRP #WRITE_EXC", "Print newline");
        asm.code("TRP #WRITE_EXC", "Print newline");

        asm.comment("Exit");
        asm.code("TRP #EXIT_EXC", "Exit program");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(BlockAST ast) {
        pushTable();
        asm.newEnvironment();
        for (DefaultAST t : ast) {
            t.accept(this);
        }
        asm.endEnvironment();
        popTable();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(VarDecAST ast) {
        for (DefaultAST var : ast.getChild(1)) {
            String varName = var.getText();
            asm.comment("Put variable '" + varName + "' on the stack");
            asm.code("LDW WR, #0", "Initialize variable '" + varName + "' with 0");
            asm.code("LDW WR, -(SP)", "Place '" + varName + "' on the stack");
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcDecAST ast) {
        asm.beginProcedureDeclaration();

        DefaultAST procHeading = ast.findFirst(Algol60Parser.PROC_HEADING);
        String procName = procHeading.getChild(0).getText();
        Procedure procedure = currentSymbolTable.resolve(procName, Procedure.class);
        String label = procedure.getAsmLabel();

        asm.label(label, "Declaration of procedure '" + procName + "'");
        asm.newProcedureEnvironment();

        if (procedure.returnsAValue()) {
            asm.comment("Put result variable '" + procName + "' on the stack");
            asm.code("LDW WR, #0", "Initialize it with 0");
            asm.code("STW WR, -(SP)", "And place it on the stack");
        }

        DefaultAST block = ast.findFirst(Algol60Parser.BLOCK);

        pushTable(); // Enter symbol table of procedure
        for (DefaultAST t : block) {
            t.accept(this); // Procedure instructions
        }
        if (procedure.returnsAValue()) {
            int shift = procedure.getReturnValue().getShift();
            asm.comment("Store return value into R1");
            asm.code("LDW R1, (BP)" + shift, "Load value of '" + procName + "' into R1");
        }

        asm.endEnvironment();
        asm.code("RTS ", "Return to caller");
        popTable(); // Quit symbol table of procedure

        asm.endProcedureDeclaration();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcCallAST ast) {
        String name = ast.getChild(0).getText();
        DefaultAST parameters = ast.getChild(1);
        asm.comment("Call procedure '" + name + "'");

        // Push parameters on stack
        for (DefaultAST param : parameters) {
            param.accept(this);
        }
        Procedure procedure = currentSymbolTable.resolve(name, Procedure.class);

        // Compute static chaining into R1
        asm.comment("Compute static chaining");
        asm.code("LDW R1, BP", "Put current BP into R1");
        SymbolTable defTable = currentSymbolTable.whereIsDeclared(name, Procedure.class);
        int n = currentSymbolTable.getLevel() - defTable.getLevel();
        for (int i = 0; i < n; i++) {
            asm.code("ADQ -2, R1", "Point to static chaining");
            asm.code("LDW R1, (R1)", "Go up by one environment");
        }

        // Call the procedure using the generated procedure label (Procedure::getAsmLabel())
        String label = procedure.getAsmLabel();
        asm.code("JSR @" + label, "Call procedure '" + name + "'");

        // Pop parameters
        int nbParams = parameters.getChildCount();
        int paramSize = nbParams * 2;
        if (nbParams != 0) {
            asm.code("LDW WR, #" + paramSize, "WR = size of '" + name + "' parameters");
            asm.code("ADD WR, SP, SP", "Pop parameters");
        }

        // Push result is there is one and that it is used
        boolean resultIsAssigned = ast.getParent().getType() != Algol60Parser.BLOCK;
        if (procedure.returnsAValue() && resultIsAssigned) {
            asm.code("STW R1, -(SP)", "Save procedure result on the stack");
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IfExpressionAST ast) {

        String endLabel = uniqueReference.forLabel("endif");
        String elseLabel = uniqueReference.forLabel("else");

        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);

        String falseCondition = elseDef != null ? elseLabel : endLabel;

        asm.comment("If expression");
        condition.accept(this); // Push 'boolean' value on the stack
        asm.pop("R1");
        asm.code(
                "JEQ #" + falseCondition + "-$-2",
                "If condition is false, jump to end of if statement");

        asm.comment("Then");
        thenDef.accept(this);
        asm.code("LDW R1,-(SP)", "");
        asm.pop("R1");
        if (elseDef != null) {
            asm.code("JMP #" + endLabel + "-$-2", "Jump to end of if statement");
            asm.label(elseLabel, "Else");
            elseDef.accept(this);
            asm.code("LDW R1,-(SP)", "");
            asm.pop("R1");
        }

        asm.label(endLabel, "End of if statement");
        asm.code("NOP", "No operation");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IfStatementAST ast) {
        String endLabel = uniqueReference.forLabel("endif");
        String elseLabel = uniqueReference.forLabel("else");

        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);

        String falseCondition = elseDef != null ? elseLabel : endLabel;

        asm.comment("If statement");
        condition.accept(this); // Push 'boolean' value on the stack
        asm.pop("R1");
        asm.code(
                "JEQ #" + falseCondition + "-$-2",
                "If condition is false, jump to end of if statement");

        asm.comment("Then");
        thenDef.accept(this);

        if (elseDef != null) {
            asm.code("JMP #" + endLabel + "-$-2", "Jump to end of if statement");
            asm.label(elseLabel, "Else");
            elseDef.accept(this);
        }

        asm.label(endLabel, "End of if statement");
        asm.code("NOP", "No operation");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ForClauseAST ast) {
        asm.comment("ForClause");
        String startfor0 = uniqueReference.forLabel("startfor0");
        String startfor1 = uniqueReference.forLabel("startfor1");
        String endfor1 = uniqueReference.forLabel("endfor1");
        DefaultAST whileClause = ast.findFirst(Algol60Parser.WHILE);
        DefaultAST action = ast.findFirst(Algol60Parser.DO).getChild(0);
        DefaultAST init = ast.findFirst(Algol60Parser.INIT);
        DefaultAST until = ast.findFirst(Algol60Parser.UNTIL);
        DefaultAST step = ast.findFirst(Algol60Parser.STEP);

        // traitement de la variable de boucle à faire
        String identifier = init.getChild(0).getText();

        int nbChildren = init.getChildCount();
        if (nbChildren > 2) {
            asm.comment("iteration " + 1 + " of enum");
            DefaultAST leftPart = init.getChild(0);
            leftPart.accept(this);
            init.getChild(1).accept(this);
            asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
            storeValueOfRegIntoVariableUsingTempReg("R1", identifier, "R2");
            asm.code("LDW R12, #0", "Preparation for looping");
            String do_smth = uniqueReference.forLabel("action");
            String start_enum = uniqueReference.forLabel("enum_loop");
            asm.label(do_smth, "Action for enum");
            action.accept(this);
            asm.code("LDW R12, R12", "Checks R12 content");
            asm.code("JEQ #" + start_enum + "-$-2", "Jumps to start of enum");
            asm.code("RTS", "back to doing whatever");
            asm.label(start_enum, "Beginning of for enum");
            asm.code("LDW R12, #1", "Loads 1 in R12");
            for (int i = 2; i < nbChildren; i++) {
                asm.comment("iteration " + i + " of enum");
                init.getChild(i).accept(this);
                asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
                storeValueOfRegIntoVariableUsingTempReg("R1", identifier, "R2");
                asm.code("JSR @" + do_smth, "Next step of loop");
            }
        } else {
            init.accept(this);
        }

        if (whileClause != null) {
            whileClause.accept(this);
            asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
            asm.code("JLE #" + endfor1 + "-$-2", "Loops out when last result equals 0");
            asm.code("JNE #" + startfor1 + "-$-2", "Loops back when last result equals 1");

            asm.label(startfor1, "Start of loop");
            action.accept(this);
            whileClause.accept(this);
            asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
            asm.code("JNE #" + startfor1 + "-$-2", "Loops back when last result equals 1");

            asm.label(endfor1, "End of loop");
            asm.newline();
        }

        if (step != null) {
            step.getChild(0).accept(this);
        }

        if (until != null) {
            until.getChild(0).accept(this);
            asm.label(startfor0, "Start of loop");
            action.accept(this);
            // chargement des valeurs de boucle
            asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
            asm.code("LDW R2, (SP)+", "Pop value off the stack into R2");
            loadValueOfVariableIntoReg(identifier, "R3");
            // calcul des valeurs de boucles
            asm.code("ADD R2, R3, R3", "Add R2 and R3 in R3");
            storeValueOfRegIntoVariableUsingTempReg("R3", identifier, "R4");
            asm.code("STW R2, -(SP)", "Push R2 value on the stack");
            asm.code("STW R1, -(SP)", "Push R1 value on the stack");
            asm.code("SUB R1, R3, R1", "Subtract R1 by R3 in R1");
            asm.code("JGE #" + startfor0 + "-$-2", "Loops back when results is not equal to 0");
            asm.newline();
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(InitAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        String identifier = leftPart.getText();
        Variable variable = currentSymbolTable.resolve(identifier, Variable.class);
        int shift = variable.getShift();
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Init" + getLineOfCode(ast));
        rightPart.accept(this); // Puts the value on the stack

        if (currentSymbolTable.isDeclaredInScope(identifier)) {
            asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
            asm.code("STW R1, (BP)" + shift, "Store value into '" + identifier + "'");
        } else {
            asm.code("LDW R2, (SP)+", "Pop value off the stack into R2");
            putBasePointerOfNonLocalVariableIntoReg(identifier, "R1");
            asm.code("STW R2, (R1)" + shift, "Store value into '" + identifier + "'");
        }

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AssignmentAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        String identifier = leftPart.getText();
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Assignment " + getLineOfCode(ast));
        rightPart.accept(this); // Puts the value on the stack
        asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
        storeValueOfRegIntoVariableUsingTempReg("R1", identifier, "R2");
        return CodeInfo.empty();
    }

    private void storeValueOfRegIntoVariableUsingTempReg(String reg, String idf, String tmpReg) {
        if (reg.equals(tmpReg))
            throw new IllegalArgumentException("reg and tmpReg must be two different registers!");
        Variable variable = currentSymbolTable.resolve(idf, Variable.class);
        int shift = variable.getShift();
        if (currentSymbolTable.isDeclaredInScope(idf)) {
            asm.code(format("STW %s, (BP)%d", reg, shift), "Store value into '" + idf + "'");
        } else {
            putBasePointerOfNonLocalVariableIntoReg(idf, tmpReg);
            asm.code(
                    format("STW %s, (%s)%d", reg, tmpReg, shift), "Store value into '" + idf + "'");
        }
    }

    @Override
    public CodeInfo visit(ArrayDecAST ast) {
        asm.comment("Array declaration " + getLineOfCode(ast));
        DefaultAST type = ast.getChild(0);
        String ArrayName = ast.getChild(1).getText();
        Array array = currentSymbolTable.resolve(ArrayName, Array.class);
        String uniqueArrayName = array.getAsmLabel();
        asm.code("STW HP, -(SP)", "Store the origin of the array in the heap onto the stack");
        DefaultAST bound = ast.getChild(2);
        int nbChildren = bound.getChildCount();
        asm.code("LDQ 1,R7", "reset R7");
        for (int i = 0; i < nbChildren; i++) {
            DefaultAST newBound = bound.getChild(i);
            DefaultAST first = newBound.getChild(0);

            DefaultAST last = newBound.getChild(1);
            first.accept(this);

            asm.code("LDW R5, (SP)+", "Pop first bound value into R5");
            last.accept(this);
            asm.code("LDW R2, (SP)+", "Pop second bound value into R2");
            asm.code("STW R5, -(SP)", "Store the first bound in the stack");
            asm.code("STW R2, -(SP)", "Store de the second bound in the stack");

            asm.code("SUB R2,R5,R6", "put the result of first bound - last bound in R6");
            asm.code(
                    "JGE #" + uniqueArrayName + i + "-$-2 ",
                    "Jump to the end of this loop if the bounds are corrects ");
            asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
            asm.label(uniqueArrayName + i, "create the end of the " + i + " loop");
            asm.code("ADI R6,R6,#1", "R6 now contain the number of element in the index");
            asm.code(
                    "MUL R6,R7,R7", "R7 is updated to contain the number of elements in the array");
        }
        asm.code("LDW R3,#0", "charge R3 avec la valeur par default");
        asm.label(uniqueArrayName, "create label " + uniqueArrayName);
        asm.code(
                "STW R3, (HP)+",
                "Store default string value in the heap the increment heap pointer");
        asm.code("ADQ -1,R7", "number of elements left to initialize");
        asm.code(
                "JNE #" + uniqueArrayName + "-$-2 ",
                "if we still have elements to initialize we loop");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayAssignmentAST ast) {
        /*
        //FONCTIONNEL 1 DIMENSION
        String identifier = ast.getChild(0).getText();
        Array array = currentSymbolTable.resolve(identifier, Array.class);
        int shift = array.getShift();
        asm.comment("Array assignment " + getLineOfCode(ast));
        DefaultAST indices = ast.getChild(1);
        for (DefaultAST index : indices) {
            index.accept(this);
        }
        ast.getChild(2).accept(this);
        asm.code("LDW R1, (SP)+", "Pop value from stack");
        asm.code("LDW R2, (SP)+", "Pop index from stack");

        if (currentSymbolTable.isDeclaredInScope(identifier)) {
            asm.code("LDW R3, (BP)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (BP)" + (shift - 2), "R4 <- lower bound");
        } else {
            putBasePointerOfNonLocalVariableIntoReg(identifier, "R4");
            asm.code("LDW R3, (R4)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (R4)" + (shift - 2), "R4 <- lower bound");
        }

        asm.code("SUB R2, R4, R4", "index - lower bound -> R4");
        asm.code("ADD R4, R4, R4", "R4 * 2 : element shift");
        asm.code("ADD R3, R4, R4", "@impl + element shift : @element");
        asm.code("STW R1, (R4)", "Store value at @element");

         */
        // TEST MULTI DIMENTIONNEL
        String name = ast.getChild(0).getText();
        DefaultAST indices = ast.getChild(1);
        int dim = indices.getChildCount();
        Array array = currentSymbolTable.resolve(name, Array.class);
        DefaultAST index = indices.getChild(0); // Dimension 1
        asm.comment("Array call " + getLineOfCode(ast));
        index.accept(this); // Push index on stack using R1
        asm.pop("R1", "Pop index into R1");
        int shift = array.getShift();
        if (currentSymbolTable.isDeclaredInScope(name)) {
            asm.code("LDW R3, (BP)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (BP)" + (shift - 2), "Lower bound");
            asm.code("LDW R5, (BP)" + (shift - 4), "R5 <- Upper bound for the index");
        } else {
            putBasePointerOfNonLocalVariableIntoReg(name, "R5");
            asm.code("LDW R3, (R5)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (R5)" + (shift - 2), "Lower bound");
            asm.code("LDW R5, (R5)" + (shift - 4), "R5 <- Upper bound for the index");
        }
        asm.code("SUB R5,R1,R9", "just to check if the bound are correct");
        asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        asm.code("SUB R1,R4,R10", "R10 now contain Index(N)-Inf(N)");
        asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        for (int i = 1; i < dim; i++) {
            index = indices.getChild(i);
            asm.push("R3", "Save @impl on stack");
            index.accept(this); // Push index on stack using R1
            asm.pop("R1", "Pop index into R1");
            asm.pop("R3", "Restore @impl into R3");
            if (currentSymbolTable.isDeclaredInScope(name)) {
                asm.code("LDW R4, (BP)" + (shift - 4 * i - 2), "R4 <- lower bound for the index");
                asm.code("LDW R5, (BP)" + (shift - 4 * (i + 1)), "R5 <- Upper bound for the index");
            } else {
                putBasePointerOfNonLocalVariableIntoReg(name, "R5");
                asm.code("LDW R4, (R5)" + (shift - 4 * i - 2), "R4 <- lower bound");
                asm.code("LDW R5, (R5)" + (shift - 4 * (i + 1)), "R5 <- Upper bound for the index");
            }
            asm.code(
                    "SUB R5,R4,R9",
                    "load R9 with upper-lower (here if R9<0 there is a problem with bounds");
            asm.code("ADQ 1,R9", "add 1 to it ");
            asm.code("MUL R10,R9,R10", "R10 now contain R10* (upper-lower)");
            asm.code("SUB R5,R1,R9", "just to check if the bound are correct");
            asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
            asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
            asm.code("SUB R1,R4,R9", "R9 now contain index-lower");
            asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
            asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
            asm.code("ADD R10,R9,R10", "R10 now contain R10 + index - lower");
        }
        asm.code("ADD R10,R10,R10", "R10 *2 because 1 elt size is 2");
        asm.code("ADD R3, R10, R10", "@impl + element shift : @element");

        asm.push("R10", "Save @element on stack");
        ast.getChild(2).accept(this);
        asm.pop("R1", "Pop right value of assignment into R1");
        asm.pop("R10", "Pop @element into R10");
        asm.code("STW R1, (R10)", "Store value at @element");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(MultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Multiplication");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("MUL R1, R2, R1", "Mul first and second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(DivAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Division");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1"); // right
        asm.code("JEQ #div0-$-2", "Jump to div0 0 if previous result equals 0");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2"); // left
        asm.code("DIV R2, R1, R1", "Divide first by second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayCallAST ast) {
        /*
        String name = ast.getChild(0).getText();
        DefaultAST indices = ast.getChild(1);
        int dims = indices.getChildCount();
        if (dims > 1) {
            Array array = currentSymbolTable.resolve(name, Array.class);
            int shift = array.getShift();
            if (currentSymbolTable.isDeclaredInScope(name)) {
                asm.code("LDW R1, (BP)" + shift, "R1 <- @impl");
            } else {
                putBasePointerOfNonLocalVariableIntoReg(name, "R4");
                asm.code("LDW R1, (R4)" + shift, "R1 <- @impl");
            }
            // R1 contient l'indice de l'élément à recup si j'ai bien compris
            ArrayIndex(dims, "R1");
            asm.code("STW R1, -(SP)", "Put it on the stack");
        } else {

            // FONCTIONNEL à 1 DIM

            DefaultAST index = indices.getChild(0); // Dimension 1
            index.accept(this); // Push index on stack using R1
            asm.comment("Array call " + getLineOfCode(ast));
            asm.pop("R1", "Pop index into R1");

            Array array = currentSymbolTable.resolve(name, Array.class);
            int shift = array.getShift();
            if (currentSymbolTable.isDeclaredInScope(name)) {
                asm.code("LDW R3, (BP)" + shift, "R3 <- @impl");
                asm.code("LDW R4, (BP)" + (shift - 2), "R4 <- lower bound");
            } else {
                putBasePointerOfNonLocalVariableIntoReg(name, "R4");
                asm.code("LDW R3, (R4)" + shift, "R3 <- @impl");
                asm.code("LDW R4, (R4)" + (shift - 2), "R4 <- lower bound");
            }

            putBasePointerOfNonLocalVariableIntoReg(name, "R4");
            asm.code("LDW R1, (R4)" + shift, "R1 <- @impl");
        }
        // R1 contient l'indice de l'élément à recup si j'ai bien compris
        ArrayIndex(dims, "R1");
        asm.code("STW R1, -(SP)", "Put it on the stack");
         */
        /*
        //FONCTIONNEL à 1 DIM
        String name = ast.getChild(0).getText();
        DefaultAST indices = ast.getChild(1);

        DefaultAST index = indices.getChild(0); // Dimension 1
        index.accept(this); // Push index on stack using R1
        asm.comment("Array call " + getLineOfCode(ast));
        asm.pop("R1", "Pop index into R1");


            asm.code("SUB R1, R4, R4", "index - lower bound -> R4");
            asm.code("ADD R4, R4, R4", "R4 * 2 : element shift");
            asm.code("ADD R3, R4, R4", "@impl + element shift : @element");
            asm.code("LDW R1,(R4)", "load the element of the array into R1");
            asm.code("STW R1, -(SP)", "Put it on the stack");
        }

        asm.code("SUB R1, R4, R4", "index - lower bound -> R4");
        asm.code("ADD R4, R4, R4", "R4 * 2 : element shift");
        asm.code("ADD R3, R4, R4", "@impl + element shift : @element");
        asm.code("LDW R1,(R4)", "load the element of the array into R1");
        asm.code("STW R1, -(SP)", "Put it on the stack");

         */

        // TEST MULTI DIMENTIONNEL
        String name = ast.getChild(0).getText();
        DefaultAST indices = ast.getChild(1);
        int dim = indices.getChildCount();
        Array array = currentSymbolTable.resolve(name, Array.class);
        asm.comment("Array call " + getLineOfCode(ast));
        DefaultAST index = indices.getChild(0); // Dimension 1
        index.accept(this); // Push index on stack using R1
        asm.pop("R1", "Pop index into R1");
        int shift = array.getShift();
        if (currentSymbolTable.isDeclaredInScope(name)) {
            asm.code("LDW R3, (BP)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (BP)" + (shift - 2), "Lower bound");
            asm.code("LDW R5, (BP)" + (shift - 4), "R5 <- Upper bound for the index");
        } else {
            putBasePointerOfNonLocalVariableIntoReg(name, "R5");
            asm.code("LDW R3, (R5)" + shift, "R3 <- @impl");
            asm.code("LDW R4, (R5)" + (shift - 2), "Lower bound");
            asm.code("LDW R5, (R5)" + (shift - 4), "R5 <- Upper bound for the index");
        }
        asm.code("SUB R5,R1,R9", "just to check if the bound are correct");
        asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        asm.code("SUB R1,R4,R10", "R10 now contain Index(N)-Inf(N)");
        asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        for (int i = 1; i < dim; i++) {
            index = indices.getChild(i);
            asm.push("R3", "Save @impl on stack");
            index.accept(this); // Push index on stack
            asm.pop("R1", "Pop index into R1");
            asm.pop("R3", "Restore @impl into R3");
            if (currentSymbolTable.isDeclaredInScope(name)) {
                asm.code("LDW R4, (BP)" + (shift - 4 * i - 2), "R4 <- lower bound for the index");
                asm.code("LDW R5, (BP)" + (shift - 4 * (i + 1)), "R5 <- Upper bound for the index");
            } else {
                putBasePointerOfNonLocalVariableIntoReg(name, "R5");
                asm.code("LDW R4, (R5)" + (shift - 4 * i - 2), "R4 <- lower bound");
                asm.code("LDW R5, (R5)" + (shift - 4 * (i + 1)), "R5 <- Upper bound for the index");
            }
            asm.code(
                    "SUB R5,R4,R9",
                    "load R9 with upper-lower (here if R9<0 there is a problem with bounds");
            asm.code("ADQ 1,R9", "add 1 to it ");
            asm.code("MUL R10,R9,R10", "R10 now contain R10* (upper-lower)");
            asm.code("SUB R5,R1,R9", "just to check if the bound are correct");
            asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
            asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
            asm.code("SUB R1,R4,R9", "R9 now contain index-lower");
            asm.code("JGE #4 ", "Jump to the end of this loop if the bounds are corrects ");
            asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
            asm.code("ADD R10,R9,R10", "R10 now contain R10 + index - lower");
        }
        asm.code("ADD R10,R10,R10", "R10 *2 because 1 elt size is 2");
        asm.code("ADD R3, R10, R10", "@impl + element shift : @element");
        asm.code("LDW R1,(R10)", "load the element of the array into R1");
        asm.code("STW R1, -(SP)", "Put it on the stack");

        return CodeInfo.empty();
    }

    public void ArrayIndex(int dims, String regImpl) {
        asm.push("R1");
        asm.code("LDW R6,#0", "");
        for (int n = 0; n <= dims - 2; n++) {
            int shift1 = (1 + n * 2) * 2;
            int shift2 = (1 + (n + 1) * 2) * 2;
            int shift3 = (1 + (n + 1) * 2 + 1) * 2;
            asm.code("LDW R5 ,#" + dims, "");
            asm.code("LDW R2 , (SP)-" + shift1, "");
            asm.code("LDW R3 , (SP)-" + shift2, "");
            asm.code("LDW R4 , (SP)-" + shift3, "");
            asm.code("SUB R2 , R5 ,R2 ", "");
            asm.code("SUB R3 , R4 , R3", "");
            asm.code("ADQ 1 , R1", "");
            asm.code("MULT R2 , R3 , R2", "");
            asm.code("ADD R6 , R2 , R6 ", "");
        }
        int shift = (1 + (dims - 1) * 2) * 2;
        int dim = dims - 1;
        asm.code("LDQ " + dim + ",R2", "");
        asm.code("LDW R3 , (SP)-" + shift, "");
        asm.code("SUB R3 ,R2 ,R2", "");
        asm.code("ADD R2 ,R2,R2", "");
        asm.code("ADD " + regImpl + ",R2 ,R1", "");
    }

    @Override
    public CodeInfo visit(IntAST ast) {
        String value = ast.getText();
        asm.code("LDW R1, #" + value, "Load int value " + value);
        asm.code("STW R1, -(SP)", "Put it on the stack");
        CodeInfo c = CodeInfo.empty();
        c.setValue(value);
        return c;
    }

    @Override
    public CodeInfo visit(Pow10AST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        int base = Math.round(Float.parseFloat(leftPart.toString()));
        int power = Math.round(Float.parseFloat(rightPart.toString()));
        int result = (int) (base * Math.pow(10, power));
        asm.comment("POWER_10");
        asm.code("LDW R1, #" + result, "Loads result in R1");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(PowAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String label = uniqueReference.forLabel("pow_loop");
        asm.comment("POWER");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R2, (SP)+", "Pop first value from the stack into R2");
        asm.code("LDW R1, #1", "Loads 1 in R1");
        asm.code("LDW R3, (SP)+", "Pop second value from the stack into R3");
        asm.label(label, "start of pow loop");
        asm.code("MUL R1, R3, R1", "Mul R1 and R3 values in R1");
        asm.code("ADQ -1, R2", "Substract 1 to R2");
        asm.code("JNE #" + label + "-$-2", "Loops back if not last result is not equal 0");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(RealAST ast) {
        float floatValue = Float.parseFloat(ast.getText());
        int intValue = Math.round(floatValue);
        String strValue = valueOf(intValue);
        asm.code("LDW R1, #" + strValue, "Load int value " + strValue);
        asm.code("STW R1, -(SP)", "Put it on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(StrAST ast) {
        String content = ast.getText();
        String label = uniqueReference.forString();
        asm.string(label, content);
        asm.code("LDW R1, #" + label, "Load address of string into R1");
        asm.code("STW R1, -(SP)", "Stack string pointer");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IntDivAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Division");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1"); // right
        asm.code("JEQ #div0-$-2", "Jump to div0 0 if previous result equals 0");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2"); // left
        asm.code("DIV R2, R1, R1", "Divide first by second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AddAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Addition");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("ADD R1, R2, R1", "Add first and second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(MinusAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Subtraction");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1, R1", "reduce first value from the second ");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(LabelDecAST ast) {
        String labelName = ast.getChild(0).getText();
        Label label = currentSymbolTable.resolve(labelName, Label.class);
        String uniqueLabelName = label.getAsmLabel();
        asm.label(uniqueLabelName, "create label " + uniqueLabelName);

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(GoToAST ast) {
        String labelName = ast.getChild(0).getText();
        Label l = currentSymbolTable.resolve(labelName, Label.class);
        String uniqueLabelName = l.getAsmLabel();
        SymbolTable labelTable = currentSymbolTable.whereIsDeclared(labelName, Label.class);
        int n = currentSymbolTable.getLevel() - labelTable.getLevel();
        for (int i = 0; i < n; i++) {
            asm.endEnvironment();
        }
        asm.code("JMP #" + uniqueLabelName + "-$-2 ", "jump to the " + uniqueLabelName + " Label");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IdentifierAST ast) {
        String name = ast.getText();
        loadValueOfVariableIntoReg(name, "R1");
        asm.push("R1", "Push value of '" + name + "' on stack");
        return CodeInfo.empty();
    }

    private void loadValueOfVariableIntoReg(String name, String reg) {
        Symbol variable = currentSymbolTable.resolve(name);
        int shift = variable.getShift();
        if (currentSymbolTable.isDeclaredInScope(name)) {
            asm.code(
                    format("LDW %s, (BP)%d", reg, shift),
                    "Load value of '" + name + "' into " + reg);
        } else {
            putBasePointerOfNonLocalVariableIntoReg(name, reg);
            asm.code(
                    format("LDW %s, (%s)%d", reg, reg, shift),
                    "Load value of '" + name + "' into " + reg);
        }
    }

    private void putBasePointerOfNonLocalVariableIntoReg(String idf, String reg) {
        int diff =
                currentSymbolTable.getLevel() - currentSymbolTable.whereIsDeclared(idf).getLevel();
        asm.code("LDW " + reg + ", BP", "Make a copy of current BP into " + reg);
        for (int i = 0; i < diff; i++) {
            asm.code(
                    format("ADQ -2, %s", reg), "Make " + reg + " point to current SC (static ch.)");
            asm.code(format("LDW %s, (%s)", reg, reg), "Go up by one environment");
        }
    }

    public CodeInfo visit(LogicalValueAST ast) {
        // juste pour memo : 1 -> true, 0 -> false
        String name = ast.getText();
        if (name.equals("true")) {
            asm.code("LDW R1, #1", "Load value 1 (true)");
        } else {
            asm.code("LDW R1, #0", "Load value 0 (false)");
        }
        asm.code("STW R1, -(SP)", "Put it on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(NotAST ast) {
        DefaultAST child = ast.getChild(0);
        child.accept(this);
        String notfalse = uniqueReference.forLabel("notfalse");
        String nottrue = uniqueReference.forLabel("nottrue");
        asm.comment("Not");
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("JEQ #" + notfalse + "-$-2", "Jump to notfalse when last result equals 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + nottrue + "-$-2", "Jump to" + nottrue + "when last result equals 0");
        asm.label(notfalse, "Label for notfalse");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(nottrue, "Label for not when done");
        asm.code("STW R1, -(SP)", "Put it on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(AndAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("And");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("MUL R1, R2, R1", "Mul first and second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");

        asm.newline();

        return CodeInfo.empty();
    }

    public CodeInfo visit(OrAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String or0 = uniqueReference.forLabel("or");
        String or1 = uniqueReference.forLabel("or");
        asm.comment("Or");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("ADD R1, R2, R1", "Add first and second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");

        asm.code("JEQ #" + or0 + "-$-2", "Jump to " + or0 + " when last result equals 0");
        asm.code("LDW R1, #1", "Loaded true");
        asm.code("JNE #" + or1 + "-$-2", "Jump to " + or1 + " when last result equals 1");
        asm.label(or0, "Label for or when false");
        asm.code("LDW R1, #0", "Loaded false");
        asm.label(or1, "Label for and or done");
        asm.code("STW R1, -(SP)", "Put it on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(ImplyAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String imply0 = uniqueReference.forLabel("imply");
        String imply1 = uniqueReference.forLabel("imply");
        asm.comment("Imply");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("JEQ #" + imply0 + "-$-2", "Jumps to " + imply0 + " when last results equals 0");
        asm.code("LDW R2, #0", "Loaded false");
        asm.code("JEQ #" + imply1 + "-$-2", "Jumps to " + imply1 + " when last results equals 0");
        asm.label(imply0, "Label for imply");
        asm.code("LDW R2, #1", "Loaded true");

        asm.label(imply1, "Label for imply");
        asm.code("ADD R1, R2, R1", "Add first and second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(EquivalentAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String equi0 = uniqueReference.forLabel("equi");
        String equi1 = uniqueReference.forLabel("equi");
        asm.comment("Equivalent");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code("JEQ #" + equi0 + "-$-2", "Jumps to " + equi0 + " when last results equals 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + equi1 + "-$-2", "Jumps to " + equi1 + " when last results equals 0");

        asm.label(equi0, "Label for equivalent");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(equi1, "Label for equivalent");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(GreaterThanAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String gt0 = uniqueReference.forLabel("gt");
        String gt1 = uniqueReference.forLabel("gt");
        asm.comment("Greater than");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code("JGT #" + gt0 + "-$-2", "Jumps to " + gt0 + " when last results is gter than 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + gt1 + "-$-2", "Jumps to " + gt1 + " when last results equals 0");

        asm.label(gt0, "Label for greater than");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(gt1, "Label for greater than");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(LessThanAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String lt0 = uniqueReference.forLabel("lt");
        String lt1 = uniqueReference.forLabel("lt");
        asm.comment("Less than");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code("JLW #" + lt0 + "-$-2", "Jumps to " + lt0 + " when last results is less than 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + lt1 + "-$-2", "Jumps to " + lt1 + " when last results equals 0");

        asm.label(lt0, "Label for less than");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(lt1, "Label for less than");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(GreaterEqualAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String ge0 = uniqueReference.forLabel("ge");
        String ge1 = uniqueReference.forLabel("ge");
        asm.comment("Greater or equal");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code(
                "JGE #" + ge0 + "-$-2",
                "Jumps to " + ge0 + " when last results is gter or equal 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + ge1 + "-$-2", "Jumps to " + ge1 + " when last results equals 0");

        asm.label(ge0, "Label for greater or equal");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(ge1, "Label for greater or equal");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(LessEqualAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String le0 = uniqueReference.forLabel("le");
        String le1 = uniqueReference.forLabel("le");
        asm.comment("Less or equal");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code(
                "JLE #" + le0 + "-$-2",
                "Jumps to " + le0 + " when last results is less or equal 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + le1 + "-$-2", "Jumps to " + le1 + " when last results equals 0");

        asm.label(le0, "Label for less or equal");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(le1, "Label for less or equal");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(EqualAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String eq0 = uniqueReference.forLabel("eq");
        String eq1 = uniqueReference.forLabel("eq");
        asm.comment("Equal");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code("JEQ #" + eq0 + "-$-2", "Jumps to " + eq0 + " when last results equals 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + eq1 + "-$-2", "Jumps to " + eq1 + " when last results equals 0");

        asm.label(eq0, "Label for equal");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(eq1, "Label for equal");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(NotEqualAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String neq0 = uniqueReference.forLabel("neq");
        String neq1 = uniqueReference.forLabel("neq");
        asm.comment("Not equal");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2");
        asm.code("SUB R2, R1 , R1", "reduce first value from the second ");
        asm.code("JNE #" + neq0 + "-$-2", "Jumps to " + neq0 + " when last results is not equal 0");
        asm.code("LDW R1, #0", "Loaded false");
        asm.code("JEQ #" + neq1 + "-$-2", "Jumps to " + neq1 + " when last results equals 0");

        asm.label(neq0, "Label for not equal");
        asm.code("LDW R1, #1", "Loaded true");
        asm.label(neq1, "Label for not equal");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        asm.newline();
        return CodeInfo.empty();
    }

    public CodeInfo visit(SwitchDecAST ast) {
        DefaultAST switchName = ast.getChild(0);
        String switchString = switchName.getText();
        Switch s = currentSymbolTable.resolve(switchString, Switch.class);

        DefaultAST IDList = ast.getChild(1);
        asm.comment("Switch '" + switchName + "' declaration");
        asm.code(
                "JMP #" + s.getEndAsmLabel() + "-$-2 ",
                "jump to the end of the label declaration of the switch '" + switchName + "'");
        asm.label(s.getBeginAsmLabel(), "The beginning of the switch declaration ");
        for (DefaultAST label : IDList) {
            String labelName = label.getText();
            Label l = currentSymbolTable.resolve(labelName, Label.class);
            String uniqueLabelName = l.getAsmLabel();
            asm.code(
                    "JMP #" + uniqueLabelName + "-$-2 ",
                    "jump to the " + uniqueLabelName + " Label");
        }
        asm.label(s.getEndAsmLabel(), "The end of the switch declaration ");
        return CodeInfo.empty();
    }

    public CodeInfo visit(SwitchCallAST ast) {
        DefaultAST switchName = ast.getChild(0);
        String switchString = switchName.getText();
        Switch s = currentSymbolTable.resolve(switchString, Switch.class);
        DefaultAST index = ast.getChild(1);
        index.accept(this);
        asm.comment(getLineOfCode(ast));
        asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        asm.code("JGT #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        int nbLabels = s.getLabelNumber();
        asm.code("ADI R1,R3,#-" + nbLabels, "check if the bounds are corrects");
        asm.code("JLE #4 ", "Jump to the end of this loop if the bounds are corrects ");
        asm.code("JMP #index_oob-$-2", "Print error out of bound message and exit");
        asm.code("LDW R2, #4 ", "Put int value 4 into R2");
        asm.code("MUL R1, R2, R1", "R1 becomes 4*R1 so the index is ok");
        asm.code("ADQ -4, R1", "R1 becomes R1-4 so the index is perfect");

        asm.code("LDW R2, #" + s.getBeginAsmLabel(), "stockage of the BEGIN address");

        asm.code("ADD R2, R1, R1", "Put address of correct jump into R1");
        asm.code("JEA (R1)", "jump to the R1 element of the '" + switchName + "' switch");

        return CodeInfo.empty();
    }
}
