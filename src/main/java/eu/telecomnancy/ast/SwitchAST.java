package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class SwitchAST extends DefaultAST {
    public SwitchAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
