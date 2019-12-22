package eu.telecomnancy.ast;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class DefaultAST extends CommonTree {

    public DefaultAST(Token t) {
        super(t);
    }

    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
}
