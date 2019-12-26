package eu.telecomnancy.ast;

import org.antlr.runtime.Token;

public class BlockAST extends DefaultAST {

    public BlockAST(Token t) {
        super(t);
    }

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
