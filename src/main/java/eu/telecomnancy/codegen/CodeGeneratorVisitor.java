package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;

public class CodeGeneratorVisitor implements ASTVisitor<CodeInfo> {

    private SymbolTable currentSymbolTable;
    private int currentTableNumber;
    private Assembly asm;
    private String[] input;

    public CodeGeneratorVisitor(SymbolTable symbolTable, Assembly asm) {
        PredefinedCode.appendAliases(asm);
        PredefinedCode.appendOutstringCode(asm);
        PredefinedCode.appendItoaCode(asm);
        PredefinedCode.appendOutintegerOrRealCode(asm);
        PredefinedCode.appendLineCode(asm);
        PredefinedCode.appendDiv0Code(asm);
        PredefinedCode.appendOutbooleanCode(asm);

        asm.newline();
        asm.def("ORG", "LOAD_ADRS", "adresse de chargement");
        asm.def("START", "main", "adresse de démarrage");

        asm.newline();
        asm.byteDef("NEWLINE", 10);

        asm.comment("Définitions de constantes");
        asm.putStringDefinitionsHere();

        this.currentSymbolTable = symbolTable;
        this.currentTableNumber = 0;
        this.asm = asm;
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

        // int size = currentSymbolTable.getLocalVariableSize();
        // asm.comment("Prepare main environment");
        //        asm.code("LDW R1, #" + size, "Local variables size into R1");
        // asm.code("ADQ -2, SP", "Decrement stack pointer");
        // asm.code("STW BP, (SP)", "Save base pointer on the stack");
        // asm.code("LDW BP, SP", "Update base pointer");
        //        asm.code("SUB SP, R1, SP", "Make space for local variables on the stack");

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

        asm.label(label, "declaration de la fonction");
        asm.newEnvironment();

        if (procedure.returnsAValue()) {
            asm.comment("Put result variable '" + procName + "' on the stack");
            asm.code("LDW WR, #0", "Initialize it with 0");
            asm.code("LDW WR, -(SP)", "And place it on the stack");
        }

        DefaultAST block = ast.findFirst(Algol60Parser.BLOCK);

        pushTable(); // Enter symbol table of procedure
        for (DefaultAST t : block) {
            t.accept(this); // Procedure instructions
        }
        if (procedure.returnsAValue()) {
            int shift = procedure.getReturnValue().getShift();
            asm.comment("Store return value into R0");
            asm.code("LDW R0, (BP)" + shift, "Load value of '" + procName + "' into R0");
        }

        asm.endEnvironment();
        asm.code("RTS ", "retour au programme appelant");
        popTable(); // Quit symbol table of procedure

        asm.endProcedureDeclaration();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcCallAST ast) {
        String name = ast.getChild(0).getText();
        DefaultAST parameters = ast.getChild(1);
        asm.comment("Call procedure '" + name + "'");
        for (DefaultAST param : parameters) {
            // Evaluate the argument value and put it on the stack (in other visit() methods)
            param.accept(this);
        }
        Procedure procedure = currentSymbolTable.resolve(name, Procedure.class);
        // Call the procedure using the generated procedure label (Procedure::getAsmLabel())
        String label = procedure.getAsmLabel();
        asm.code("JSR @" + label, "Call procedure '" + name + "'");
        int nbParams = parameters.getChildCount();
        int paramSize = nbParams * 2;
        if (nbParams != 0) {
            asm.code("LDW WR, #" + paramSize, "WR = size of '" + name + "' parameters");
            asm.code("ADD WR, SP, SP", "Pop parameters");
        }
        if (procedure.getType() != Type.VOID) {
            asm.code("STW R0, -(SP)", "Save procedure result on the stack");
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IfExpressionAST ast) {

        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        condition.accept(this);
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        thenDef.getChild(0).accept(this);

        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);
        elseDef.getChild(0).accept(this);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IfStatementAST ast) {
        String endLabel = UniqueReference.forLabel("endif");
        String elseLabel = UniqueReference.forLabel("else");

        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);

        String falseCondition = elseDef != null ? elseLabel : endLabel;

        asm.comment("If statement");
        condition.accept(this); // Push 'boolean' value on the stack
        asm.popValueIntoRegister("R1");
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
        String startfor = UniqueReference.forLabel("startfor");
        String endfor = UniqueReference.forLabel("endfor");
        DefaultAST init = ast.findFirst(Algol60Parser.INIT);

        // traitement de la variable de boucle
        String identifeir = init.getChild(0).getText();

        init.accept(this);
        /*DefaultAST whileClause = ast.findFirst(Algol60Parser.WHILE);
        asm.code("ADQ -2,SP", "");
        if (whileClause != null) {
            whileClause.getChild(0).accept(this);
        }*/
        DefaultAST step = ast.findFirst(Algol60Parser.STEP);
        if (step != null) {
            step.getChild(0).accept(this);
        }
        DefaultAST until = ast.findFirst(Algol60Parser.UNTIL);
        if (until != null) {
            until.getChild(0).accept(this);
        }
        asm.label(startfor, "Start of loop");
        DefaultAST action = ast.findFirst(Algol60Parser.DO).getChild(0);
        action.accept(this);
        // chargement des valeurs de boucle
        asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
        asm.code("LDW R2, (SP)+", "Pop value off the stack into R2");
        asm.code("LDW R3, (SP)+", "Pop value off the stack into R3");
        // calcul des valeurs de boucles
        asm.code("ADD R2, R3, R3", "Add R2 and R3 in R3");
        asm.code("STW R3, -(SP)", "Push resulting value on the stack");
        asm.code("STW R2, -(SP)", "Push R2 value on the stack");
        asm.code("STW R1, -(SP)", "Push R1 value on the stack");
        asm.code("SUB R1, R3, R1", "Substract R1 by R2 in R1");
        asm.code("JNE #" + startfor + "-$-2", "Loops back when results is not equal to 0");
        asm.newline();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(InitAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        leftPart.accept(this);
        int nbChildren = ast.getChildCount();
        for (int i = 1; i < nbChildren; i++) {
            DefaultAST rightPart = ast.getChild(i);
            rightPart.accept(this);
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AssignmentAST ast) {
        // TODO: Finish the assignment of values (int values first)
        DefaultAST leftPart = ast.getChild(0);
        String identifier = leftPart.getText();
        Variable variable = currentSymbolTable.resolve(identifier, Variable.class);
        int shift = variable.getShift();
        DefaultAST rightPart = ast.getChild(1);

        asm.comment("Assignment: " + getLineOfCode(ast));
        rightPart.accept(this); // Puts the value on the stack
        asm.code("LDW R1, (SP)+", "Pop value off the stack into R1");
        asm.code("STW R1, (BP)" + shift, "Store value into '" + identifier + "'");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayDecAST ast) {

        DefaultAST boundList = ast.getChild(2);
        CodeInfo firstBound = CodeInfo.empty();
        CodeInfo lastBound = CodeInfo.empty();
        int a = 1;
        for (DefaultAST bound : boundList) {
            DefaultAST first = bound.getChild(0);
            DefaultAST last = bound.getChild(1);
            firstBound = first.accept(this);
            lastBound = last.accept(this);
            int firstBoundInt = firstBound.getValue();
            int lastBoundInt = lastBound.getValue();
            a = a * (lastBoundInt - firstBoundInt + 1);
            asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
            asm.code("LDW R1, (SP)+", "Pop first value from the stack into R1");
        }

        asm.code(
                String.format("LDW R1, #%s", a), "Initialize variable  with the size of the array");
        asm.code("LDW R1, -(SP)", "Place on the stack");
        for (int i = 0; i <= a; i++) {
            asm.code("LDW WR, #0", "Initialize variable  with 0");
            asm.code("LDW WR, -(SP)", "Place on the stack");
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayAssignmentAST ast) {
        DefaultAST indices = ast.getChild(1);
        for (DefaultAST index : indices) {
            index.accept(this);
        }
        DefaultAST rightPart = ast.getChild(2);
        rightPart.accept(this);
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
        asm.code("JEQ #div0_-$-2", "Jump to div0 0 if previous result equals 0");
        asm.code("LDW R2, (SP)+", "Pop second value from the stack into R2"); // left
        asm.code("DIV R2, R1, R1", "Divide first by second value");
        asm.code("STW R1, -(SP)", "Push resulting value on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayCallAST ast) {
        DefaultAST paramList = ast.getChild(1);
        for (DefaultAST param : paramList) {
            param.accept(this);
        }
        return CodeInfo.empty();
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
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(PowAST ast) {
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(RealAST ast) {
        float floatValue = Float.parseFloat(ast.getText());
        int intValue = Math.round(floatValue);
        String strValue = String.valueOf(intValue);
        asm.code("LDW R1, #" + strValue, "Load int value " + strValue);
        asm.code("STW R1, -(SP)", "Put it on the stack");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(StrAST ast) {
        String content = ast.getText();
        String label = UniqueReference.forString();
        asm.string(label, content);
        asm.code("LDW R1, #" + label, "charge adresse de la chaîne dans R1");
        asm.code("STW R1, -(SP)", "empile le string");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IntDivAST ast) {
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

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(GoToAST ast) {

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IdentifierAST ast) {
        String name = ast.getText();
        Variable variable = currentSymbolTable.resolve(name, Variable.class);
        int shift = variable.getShift();
        asm.code("LDW R1, (BP)" + shift, "Load value of '" + name + "' into R1");
        asm.code("STW R1, -(SP)", "Push value of '" + name + "' on the stack");
        return CodeInfo.empty();
    }

    private void stackBool(String bool) {
        if (bool.equals("true")) {
            asm.code("LDW R1, #1", "Load value 1 (true)");
        } else {
            asm.code("LDW R1, #0", "Load value 0 (false)");
        }
        asm.code("STW R1, -(SP)", "Put it on the stack");
    }

    public CodeInfo visit(LogicalValueAST ast) {
        // juste pour memo : 1 -> true, 0 -> false
        String name = ast.getText();
        stackBool(name);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(NotAST ast) {
        DefaultAST child = ast.getChild(0);
        child.accept(this);
        String notfalse = UniqueReference.forLabel("notfalse");
        String nottrue = UniqueReference.forLabel("nottrue");
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
        String or0 = UniqueReference.forLabel("or");
        String or1 = UniqueReference.forLabel("or");
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
        String imply0 = UniqueReference.forLabel("imply");
        String imply1 = UniqueReference.forLabel("imply");
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
        String equi0 = UniqueReference.forLabel("equi");
        String equi1 = UniqueReference.forLabel("equi");
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
        String gt0 = UniqueReference.forLabel("gt");
        String gt1 = UniqueReference.forLabel("gt");
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
        String lt0 = UniqueReference.forLabel("lt");
        String lt1 = UniqueReference.forLabel("lt");
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
        String ge0 = UniqueReference.forLabel("ge");
        String ge1 = UniqueReference.forLabel("ge");
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
        String le0 = UniqueReference.forLabel("le");
        String le1 = UniqueReference.forLabel("le");
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
        String eq0 = UniqueReference.forLabel("eq");
        String eq1 = UniqueReference.forLabel("eq");
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
        String neq0 = UniqueReference.forLabel("neq");
        String neq1 = UniqueReference.forLabel("neq");
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
        return CodeInfo.empty();
    }

    public CodeInfo visit(SwitchCallAST ast) {
        DefaultAST index = ast.getChild(1);
        index.accept(this);
        return CodeInfo.empty();
    }
}
