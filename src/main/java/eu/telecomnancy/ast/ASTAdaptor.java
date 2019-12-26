package eu.telecomnancy.ast;

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
                default:
                    break;
            }
        }
        return new DefaultAST(t);
    }
}
