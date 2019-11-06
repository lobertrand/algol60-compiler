import java.io.*;
import org.antlr.runtime.*;
import org.antlr.runtime.debug.DebugEventSocketProxy;

import eu.telecomnancy.*;


public class __Test__ {

    public static void main(String args[]) throws Exception {
        Algol60Lexer lex = new Algol60Lexer(new ANTLRFileStream("/home/etudiants/zhou69u/Documents/2A/Code/PCL/Compil/bertra182u/src/main/antlr/eu/telecomnancy/output/__Test___input.txt", "UTF8"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        Algol60Parser g = new Algol60Parser(tokens, 49100, null);
        try {
            g.prog();
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}