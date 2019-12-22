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

                default:
                    break;
            }
        }
        return new DefaultAST(t);
    }
}
