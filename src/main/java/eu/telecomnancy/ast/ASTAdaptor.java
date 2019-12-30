package eu.telecomnancy.ast;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeAdaptor;

public class ASTAdaptor extends CommonTreeAdaptor {

    @Override
    public Object create(Token t) {
        if (t != null) {
            switch (t.getType()) {
                case Algol60Parser.ROOT:
                    return new RootAST(t);
                case Algol60Parser.BLOCK:
                    return new BlockAST(t);
                case Algol60Parser.VAR_DEC:
                    return new VarDecAST(t);
                case Algol60Parser.PROC_DEC:
                    return new ProcDecAST(t);
                case Algol60Parser.PROC_CALL:
                    return new ProcCallAST(t);
                case Algol60Parser.IF_STATEMENT:
                    return new IfStatementAST(t);
                case Algol60Parser.FOR_CLAUSE:
                    return new ForClauseAST(t);
                case Algol60Parser.WHILE_CLAUSE:
                    return new WhileClauseAST(t);
                case Algol60Parser.ASSIGNMENT:
                    return new AssignmentAST(t);
                case Algol60Parser.ARRAY_DEC:
                    return new ArrayDecAST(t);
                case Algol60Parser.ARRAY_ASSIGNMENT:
                    return new AssignmentAST(t);
                case Algol60Parser.MULT:
                    return new MultAST(t);
                case Algol60Parser.DIV:
                    return new DivAST(t);
                default:
                    break;
            }
        }
        return new DefaultAST(t);
    }
}
