package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;

public class CodeGeneratorVisitor implements ASTVisitor<CodeInfo> {

    private SymbolTable currentSymbolTable;
    private int currentTableNumber;
    private Assembly asm;
    private String instruction;

    public CodeGeneratorVisitor(SymbolTable symbolTable, Assembly asm) {
        PredefinedCode.appendAliases(asm);
        PredefinedCode.appendOutstringCode(asm);

        asm.newline();
        asm.def("ORG", "LOAD_ADRS", "adresse de chargement");
        asm.def("START", "main", "adresse de démarrage");

        asm.comment("Définitions de chaînes de caractère");
        asm.putStringDefinitionsHere();

        this.currentSymbolTable = symbolTable;
        this.currentTableNumber = 0;
        this.asm = asm;
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
        asm.label("main", "Point d'entrée");

        // DEBUGGING CODE (REMOVE LATER)
        // // in visit(StrAST)
        // asm.string("HELLO", "Hello world!");
        // // In visit(ProcCallAST)
        // asm.code("LDW R1, #HELLO", "charge adresse de la chaîne n°0 dans R1");
        // asm.code("STW R1, -(SP)", "empile paramètre p = STRING0 contenu dans R1 :");
        // asm.code("JSR @outstring_", "appelle la fonction d'adresse outstring:");
        // asm.code("JSR @factorial_", "appelle la fonction d'adresse outstring:");
        // END OF DEBUGGING CODE

        // Main block instructions
        ast.getChild(0).accept(this);

        // Final operations
        asm.code("TRP #EXIT_EXC", "EXIT: arrête le programme");

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(BlockAST ast) {
        pushTable();
        for (DefaultAST t : ast) {
            t.accept(this);
        }
        popTable();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(VarDecAST ast) {
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcDecAST ast) {
        asm.beginProcedureDeclaration();

        DefaultAST procHeading = ast.findFirst(Algol60Parser.PROC_HEADING);
        String procName = procHeading.getChild(0).getText();
        Procedure procedure = currentSymbolTable.resolve(procName, Procedure.class);
        String label = procedure.getAsmLabel();
        int shift = procedure.getShift();

        // TODO: Finish the declaration of procedure
        asm.label(label, "declaration de la fonction");
        asm.code("STW BP, -(SP)", "empile le contenu du registre BP");
        asm.code("LDW BP, SP", "charge contenu SP ds BP");
        if (procedure.getType() != Type.VOID) {
            asm.code("STW R0, (BP)" + shift, "code du calcul de l'expression, résultat dans R0");
            asm.code("LDW SP, BP ", "bandon infos locales");
            asm.code("LDW BP, (SP)+ ", "charge BP avec ancien BP");
            asm.code("RTS ", "retour au programme appelant");
        }
        asm.code("LDW SP, BP ", "bandon infos locales");
        asm.code("LDW BP, (SP)+ ", "charge BP avec ancien BP");
        asm.code("RTS ", "retour au programme appelant");

        DefaultAST block = ast.findFirst(Algol60Parser.BLOCK);
        pushTable(); // Enter symbol table of procedure
        for (DefaultAST t : block) {
            t.accept(this);
        }
        popTable(); // Quit symbol table of procedure

        asm.endProcedureDeclaration();
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcCallAST ast) {
        // TODO: For each argument passed to the procedure call
        for (DefaultAST args : ast.getChild(1)) {
            // Evaluate the argument value and put it in R1 (in other visit() methods)
            // The simplest one to implement is probably visit(IdentifierAST)
            args.accept(this);
            // Push the argument value to the stack
            // asm.code("STW R1, -(SP)", "empile paramètre contenu dans R1 :");
        }
        // Call the procedure using the generated procedure label (Label::getAsmLabel())
        // asm.code("JSR @outstring_", "appelle la fonction d'adresse outstring:");

        // Example :
        // asm.code("LDW R1, #HELLO", "charge adresse de la chaîne n°0 dans R1");
        // asm.code("STW R1, -(SP)", "empile paramètre p = STRING0 contenu dans R1 :");
        // asm.code("JSR @outstring_", "appelle la fonction d'adresse outstring:");
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
        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        condition.accept(this);
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        thenDef.accept(this);
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);
        if (elseDef != null) {
            elseDef.accept(this);
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ForClauseAST ast) {
        DefaultAST init = ast.findFirst(Algol60Parser.INIT);
        init.accept(this);

        DefaultAST whileClause = ast.findFirst(Algol60Parser.WHILE);
        if (whileClause != null) {
            whileClause.getChild(0).accept(this);
        }

        DefaultAST step = ast.findFirst(Algol60Parser.STEP);
        if (step != null) {
            step.getChild(0).accept(this);
        }

        DefaultAST until = ast.findFirst(Algol60Parser.UNTIL);
        if (until != null) {
            until.getChild(0).accept(this);
        }

        DefaultAST action = ast.findFirst(Algol60Parser.DO).getChild(0);

        action.accept(this);

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
        asm.comment("Assignment");
        DefaultAST leftPart = ast.getChild(0);
        Symbol s = currentSymbolTable.resolve(leftPart.getText());
        leftPart.accept(this);
        DefaultAST rightPart = ast.getChild(1);
        rightPart.accept(this);
        switch (rightPart.getType()) {
            case (Algol60Parser.INT):
                asm.code(
                        String.format("LDW R1, #%s", rightPart.getText()),
                        "R1 = VALEUR DE RIGHTPART");
                asm.code(
                        String.format("STW R1, (BP)-%d", s.getShift()),
                        "affecte variable LeftPart de déplacement Shift avec contenu de R1");
            case (Algol60Parser.STR):
            default:
                break;
        }
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ArrayDecAST ast) {
        DefaultAST boundList = ast.getChild(2);

        for (DefaultAST bound : boundList) {
            DefaultAST first = bound.getChild(0);
            DefaultAST last = bound.getChild(1);
            first.accept(this);
            last.accept(this);
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
        checkArithmeticOperation(ast);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(DivAST ast) {
        checkArithmeticOperation(ast);
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
        // TODO: Put the int value into R1
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(Pow10AST ast) {
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(PowAST ast) {
        checkArithmeticOperation(ast);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(RealAST ast) {
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(StrAST ast) {
        // TODO: register the string value into "asm" variable using the UniqueReference class
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IntDivAST ast) {
        checkArithmeticOperation(ast);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AddAST ast) {
        String leftPart = ast.getChild(0).toString();
        Symbol leftPartSymbol = currentSymbolTable.resolve(leftPart);
        String rightPart = ast.getChild(1).toString();
        Symbol rightPartSymbol = currentSymbolTable.resolve(rightPart);
        checkArithmeticOperation(ast);
        asm.label("", "Add");
        asm.code(
                String.format("LDW R1, (BP)%d", leftPartSymbol.getShift()),
                "charge le paramètre leftpart de déplacement Shift dans R1 :");
        asm.code("LDW WR, BP ", "WR = BP");
        asm.code(
                String.format("ADQ %d, WR ", leftPartSymbol.getShift()),
                "WR pointe sur paramètre leftpart");
        asm.code("LDW R1, (WR) ", "R1 = LeftPart");
        asm.code(
                String.format("LDW R2, (BP)%d", rightPartSymbol.getShift()),
                "charge le paramètre RIGHTpart de déplacement Shift dans R2 :");
        asm.code("LDW WR, BP ", "WR = BP");
        asm.code(
                String.format("ADQ %d, WR ", rightPartSymbol.getShift()),
                "WR pointe sur paramètre rightpart");
        asm.code("LDW R2, (WR) ", "R2 = LeftPart");
        asm.code("ADD R1 ,R2,R1", "fait le calcul et le stock dans R1");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(MinusAST ast) {
        checkArithmeticOperation(ast);
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
        // TODO: Find the variable with a "resolve" in the current symbol table
        // Get its shift and compute the address where the value of the variable is stored
        // (Take into account whether the variable is local or non-local)
        // Put the value of the variable into R1
        return CodeInfo.empty();
    }

    public CodeInfo visit(LogicalValueAST ast) {
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(NotAST ast) {
        DefaultAST child = ast.getChild(0);
        child.accept(this);
        return CodeInfo.empty();
    }

    public CodeInfo visit(AndAST ast) {
        checkLogicalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(OrAST ast) {
        checkLogicalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(ImplyAST ast) {
        checkLogicalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(EquivalentAST ast) {
        checkLogicalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(GreaterThanAST ast) {
        checkRelationalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(LessThanAST ast) {
        checkRelationalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(GreaterEqualAST ast) {
        checkRelationalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(LessEqualAST ast) {
        checkRelationalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(EqualAST ast) {
        checkRelationalOperation(ast);
        return CodeInfo.empty();
    }

    public CodeInfo visit(NotEqualAST ast) {
        checkRelationalOperation(ast);
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

    private void checkLogicalOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        leftPart.accept(this);
        rightPart.accept(this);
    }

    private void checkRelationalOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        leftPart.accept(this);
        rightPart.accept(this);
    }

    private void checkArithmeticOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        leftPart.accept(this);
        rightPart.accept(this);
    }
}
