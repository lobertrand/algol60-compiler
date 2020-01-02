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
                case Algol60Parser.WHILE_CLAUSE:
                    return new WhileClauseAST(t);
                case Algol60Parser.ASSIGNMENT:
                    return new AssignmentAST(t);
                case Algol60Parser.ARRAY_DEC:
                    return new ArrayDecAST(t);
                case Algol60Parser.ARRAY_ASSIGNMENT:
                    return new ArrayAssignmentAST(t);
                case Algol60Parser.MULT:
                    return new MultAST(t);
                case Algol60Parser.DIV:
                    return new DivAST(t);
                case Algol60Parser.ARRAY_CALL:
                    return new ArrayCallAST(t);
                case Algol60Parser.INT:
                    return new IntAST(t);
                case Algol60Parser.POW_10:
                    return new Pow10AST(t);
                case Algol60Parser.POW:
                    return new PowAST(t);
                case Algol60Parser.REAL:
                    return new RealAST(t);
                case Algol60Parser.STR:
                    return new StrAST(t);
                case Algol60Parser.INT_DIV:
                    return new IntDivAST(t);
                case Algol60Parser.ADD:
                    return new AddAST(t);
                case Algol60Parser.MINUS:
                    return new MinusAST(t);
                case Algol60Parser.TERM:
                    return new TermAST(t);
                case Algol60Parser.LABEL_DEC:
                    return new LabelDecAST(t);
                case Algol60Parser.GOTO:
                    return new GoToAST(t);
                default:
                    break;
            }
        }
        return new DefaultAST(t);
    }
}
