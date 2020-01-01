package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class AddAST extends DefaultAST {
    public AddAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
