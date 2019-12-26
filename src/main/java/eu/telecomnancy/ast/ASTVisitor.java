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
}
