package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class DivAST extends DefaultAST {
    public DivAST(Token t) {
        super(t);
    }
    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}

