package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;

public class CodeGeneratorVisitor implements ASTVisitor<CodeInfo> {

    private SymbolTable currentSymbolTable;
    private int currentTableNumber;
    private Assembly asm;

    public CodeGeneratorVisitor(SymbolTable symbolTable, Assembly asm) {
        PredefinedCode.appendAliases(asm);

        asm.newline();
        asm.def("ORG", "LOAD_ADRS", "adresse de chargement");
        asm.def("START", "main", "adresse de démarrage");

        asm.comment("Définitions de chaînes de caractère");
        asm.putStringDefinitionsHere();
        asm.string("HELLO", "Hello world!");

        asm.label("main", "Point d'entrée");
        asm.code("LDW R1, #HELLO", "charge adresse de la chaîne n°0 dans R1");
        asm.code("STW R1, -(SP)", "empile paramètre p = STRING0 contenu dans R1 :");
        asm.code("JSR @outstring", "appelle la fonction d'adresse outstring:");
        asm.code("TRP #EXIT_EXC", "EXIT: arrête le programme");

        PredefinedCode.appendOutstringCode(asm);

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
        ast.getChild(0).accept(this);
        // Final operations

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

        DefaultAST block = ast.findFirst(Algol60Parser.BLOCK);
        pushTable(); // Enter symbol table of procedure
        for (DefaultAST t : block) {
            t.accept(this);
        }
        popTable(); // Quit symbol table of procedure

        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(ProcCallAST ast) {
        for (DefaultAST args : ast.getChild(1)) {
            args.accept(this);
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
        DefaultAST leftPart = ast.getChild(0);
        leftPart.getText();
        leftPart.accept(this);
        DefaultAST rightPart = ast.getChild(1);
        rightPart.accept(this);
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
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(IntDivAST ast) {
        checkArithmeticOperation(ast);
        return CodeInfo.empty();
    }

    @Override
    public CodeInfo visit(AddAST ast) {
        checkArithmeticOperation(ast);
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
