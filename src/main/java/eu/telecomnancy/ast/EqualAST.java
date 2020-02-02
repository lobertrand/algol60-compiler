package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class EqualAST extends DefaultAST {

    public EqualAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
