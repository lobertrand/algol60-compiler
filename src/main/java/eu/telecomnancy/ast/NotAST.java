package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class NotAST extends DefaultAST {
    public NotAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
