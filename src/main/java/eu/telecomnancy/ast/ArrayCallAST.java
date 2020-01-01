package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class ArrayCallAST extends DefaultAST {

    public ArrayCallAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
