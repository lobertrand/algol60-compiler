package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.tools.ASTTools;

public class CodeGeneratorVisitor implements ASTVisitor<CodeInfo> {

    private SymbolTable currentSymbolTable;
    private int currentTableNumber;
    private Assembly asm;
    private String instruction;

    public CodeGeneratorVisitor(SymbolTable symbolTable, Assembly asm) {
        PredefinedCode.appendAliases(asm);
        PredefinedCode.appendOutstringCode(asm);
        PredefinedCode.appendItoaCode(asm);
        PredefinedCode.appendOutintegerCode(asm);

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
        for (DefaultAST var : ast.getChild(1)) {
            String var_name = var.getText();
            Symbol variable = currentSymbolTable.resolve(var_name);
            asm.code("LDW WR, #0", "");
            asm.code("LDW WR, -(SP)", "");
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
            asm.code("ADQ -2, SP", "décrémente le pointeur de pile SP");
            asm.code("STW R1, (SP)", "sauvegarde le contenu du registre R1 sur la pile");
            // Evaluate the argument value and put it on the stack (in other visit() methods)
            args.accept(this);
        }
        Procedure procedure =
                currentSymbolTable.resolve(ast.getChild(0).getText(), Procedure.class);
        // Call the procedure using the generated procedure label (Procedure::getAsmLabel())
        String label = procedure.getAsmLabel();
        asm.code("JSR @" + label, "appelle la fonction d'adresse outstring:");
        int nbParams = ast.getChild(1).getChildCount();
        if (nbParams != 0) {
            asm.code("LDW WR, #" + nbParams, "WR = taille totale des paramètres");
            asm.code("ADD WR, SP, SP", "abandon paramètres");
        }
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
        String identifier = leftPart.getText();
        Variable variable = currentSymbolTable.resolve(identifier, Variable.class);
        DefaultAST rightPart = ast.getChild(1);
        rightPart.accept(this);

        asm.code("LDW WR, BP", "");
        asm.code(String.format("ADQ %d,WR", variable.getShift()), "");
        asm.code("STW R1,(WR)", "");
        leftPart.accept(this);

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
        String integer = ast.getText();
        asm.comment("INT");
        asm.code(String.format("LDW R1,#%s", integer), "");
        asm.code("STW R1, -(SP)", "empile l'entier contenu dans R1");
        // asm.code();
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
        String content = ast.getText();
        String label = UniqueReference.forString();
        asm.string(label, content);
        asm.code("LDW R1, #" + label, "charge adresse de la chaîne dans R1");
        asm.code("STW R1, -(SP)", "empile le string");
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IntDivAST ast) {
        checkArithmeticOperation(ast);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AddAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        asm.comment("Add");
        leftPart.accept(this);
        rightPart.accept(this);
        asm.code("LDW R1, (SP)+", "Depile");
        asm.code("LDW R2, (SP)+", "Depile");
        asm.code("ADD R1 ,R2,R1", "fait le calcul et le stock dans R1");
        asm.code("STW R1,-(SP)", "");

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
