package eu.telecomnancy.ast;

import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
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
                case Algol60Parser.INIT:
                    return new InitAST(t);
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
                case Algol60Parser.LABEL_DEC:
                    return new LabelDecAST(t);
                case Algol60Parser.GOTO:
                    return new GoToAST(t);
                case Algol60Parser.LOGICAL_VALUE:
                    return new LogicalValueAST(t);
                case Algol60Parser.NOT:
                    return new NotAST(t);
                case Algol60Parser.AND:
                    return new AndAST(t);
                case Algol60Parser.OR:
                    return new OrAST(t);
                case Algol60Parser.IMPLY:
                    return new ImplyAST(t);
                case Algol60Parser.EQUIVALENT:
                    return new EquivalentAST(t);
                case Algol60Parser.GREATER_THAN:
                    return new GreaterThanAST(t);
                case Algol60Parser.LESS_THAN:
                    return new LessThanAST(t);
                case Algol60Parser.GREATER_EQUAL:
                    return new GreaterEqualAST(t);
                case Algol60Parser.LESS_EQUAL:
                    return new LessEqualAST(t);
                case Algol60Parser.EQUAL:
                    return new EqualAST(t);
                case Algol60Parser.NOT_EQUAL:
                    return new NotEqualAST(t);
                case Algol60Parser.IDENTIFIER:
                    return new IdentifierAST(t);
                default:
                    break;
            }
        }
        return new DefaultAST(t);
    }

    public Object dupNode(Object t) {
        if (t == null) return null;
        return create(((DefaultAST) t).token);
    }

    public Object errorNode(TokenStream input, Token start, Token stop, RecognitionException e) {
        return new ErrorAST(input, start, stop, e);
    }
}
