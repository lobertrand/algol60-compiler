package eu.telecomnancy.ast;

import java.util.Iterator;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class DefaultAST extends CommonTree implements Iterable<DefaultAST> {

    public DefaultAST(Token t) {
        super(t);
    }

    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }

    @Override
    public Iterator<DefaultAST> iterator() {
        return new Iterator<DefaultAST>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < getChildCount();
            }

            @Override
            public DefaultAST next() {
                return (DefaultAST) getChild(index++);
            }
        };
    }

    public DefaultAST getChildAST(int i) {
        Tree tree = getChild(i);
        if (tree == null) return null;
        return (DefaultAST) tree;
    }
}
