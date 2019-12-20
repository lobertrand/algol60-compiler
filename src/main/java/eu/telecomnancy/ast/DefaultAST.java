package eu.telecomnancy.ast;

public class DefaultAST {

    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
