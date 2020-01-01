package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class LabelDecAST extends DefaultAST {
    public LabelDecAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
