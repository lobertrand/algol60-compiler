package eu.telecomnancy.ast;

public interface ASTVisitor<R> {

    R visit(DefaultAST defaultAST);
}
