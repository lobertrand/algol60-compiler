package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class NotEqualAST extends DefaultAST {

    public NotEqualAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
