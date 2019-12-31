package eu.telecomnancy.ast;

public interface ASTVisitor<R> {

    R visit(DefaultAST ast);

    R visit(RootAST ast);

    R visit(BlockAST ast);

    R visit(VarDecAST ast);

    R visit(ProcDecAST ast);

    R visit(ProcCallAST ast);

    R visit(IfStatementAST ast);

    R visit(ForClauseAST ast);

    R visit(WhileClauseAST ast);

    R visit(AssignmentAST ast);

    R visit(ArrayDecAST ast);

    R visit(ArrayAssignmentAST ast);

    R visit(MultAST ast);

    R visit(DivAST ast);

    R visit(ArrayCallAST ast);

    R visit(IntAST ast);

    R visit(Pow10AST ast);

    R visit(PowAST ast);

    R visit(RealAST ast);

    R visit(StrAST ast);
}
