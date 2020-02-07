package eu.telecomnancy.ast;

import eu.telecomnancy.Algol60Parser;
import java.util.Iterator;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

public class DefaultAST extends CommonTree implements Iterable<DefaultAST> {

    private boolean shiftCursorLeft = false;

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
                return getChild(index++);
            }
        };
    }

    public DefaultAST shiftCursorLeft() {
        shiftCursorLeft = true;
        return this;
    }

    public boolean shouldShiftCursorLeft() {
        return shiftCursorLeft;
    }

    @Override
    public DefaultAST getChild(int i) {
        return (DefaultAST) super.getChild(i);
    }

    public DefaultAST findFirst(int type) {
        for (DefaultAST child : this) {
            if (child.getType() == type) return child;
        }
        return null;
    }

    public boolean containsReturnStatement(String procName) {
        if (isReturnStatement(procName)) return true;
        switch (getType()) {
            case Algol60Parser.IF_STATEMENT:
                DefaultAST thenDef = findFirst(Algol60Parser.THEN_DEF);
                DefaultAST elseDef = findFirst(Algol60Parser.ELSE_DEF);
                if (elseDef == null) return false;
                return thenDef.containsReturnStatement(procName)
                        && elseDef.containsReturnStatement(procName);
            case Algol60Parser.WHILE_CLAUSE:
            case Algol60Parser.FOR_CLAUSE:
            case Algol60Parser.PROC_DEC:
                return false;
            default:
                for (DefaultAST child : this)
                    if (child.containsReturnStatement(procName)) return true;
                return false;
        }
    }

    private boolean isReturnStatement(String procName) {
        if (getType() == Algol60Parser.ASSIGNMENT) {
            if (isIdfWithName(getChild(0), procName)) {
                return !isIdfWithName(getChild(1), procName)
                        && !isProcCallWithName(getChild(1), procName);
            }
        }
        return false;
    }

    private static boolean isIdfWithName(DefaultAST ast, String name) {
        return ast.getType() == Algol60Parser.IDENTIFIER && ast.getText().equals(name);
    }

    private static boolean isProcCallWithName(DefaultAST ast, String name) {
        return ast.getType() == Algol60Parser.PROC_CALL && ast.getChild(0).getText().equals(name);
    }
}
