package eu.telecomnancy.tools;

import static eu.telecomnancy.tools.StringTools.*;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.semantic.SemanticException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;

public class IOUtils {

    public static void log(Object o) {
        System.out.println(CYAN + "Algol60> " + RESET + o);
    }

    public static void logError(Object o) {
        System.out.println(YELLOW + "Algol60> " + RESET + o);
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void printRecognitionException(RecognitionException e, ANTLRInputStream input) {
        String msg = "";
        try {
            throw e;
        } catch (NoViableAltException ex) {
            if (ex.token != null) {
                msg = "Unexpected token '" + ex.token.getText() + "'";
            } else {
                msg = "Unexpected token";
            }
        } catch (FailedPredicateException ex) {
            msg = "Failed predicate at token '" + ex.token.getText() + "'";
        } catch (MissingTokenException ex) {
            if (ex.expecting >= 0) {
                msg = "Missing token " + Algol60Parser.tokenNames[ex.expecting];
            } else {
                msg = "Missing token";
            }
        } catch (MismatchedTokenException ex) {
            msg = "Mismatched token '" + ex.token.getText() + "'";
        } catch (RecognitionException ex) {
            msg = "Recognition error at token '" + ex.token.getText() + "'";
        }
        msg += " at line " + e.line;
        logError(msg);
        printInputLine(e.line, e.charPositionInLine, input.toString());
    }

    public static void printSemanticException(SemanticException e, ANTLRInputStream input) {
        logError(e);
        printInputLine(e.getLine(), e.getColumn(), input.toString());
    }

    private static void printInputLine(int lineNumber, int colNumber, String text) {
        String[] inputLines = text.split("\n");
        if (lineNumber - 1 >= 0 && lineNumber - 1 < inputLines.length) {
            String line = inputLines[lineNumber - 1].replace('\t', ' ');
            String pointer = repeat(" ", colNumber) + YELLOW + "^" + RESET;
            System.out.println(String.format(" %5d | %s", lineNumber, line));
            System.out.println(String.format(" %5s | %s", "", pointer));
        }
    }

    public static void generateDotTree(Tree tree, String name) throws IOException {
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        String dotTree = st.toString();

        // Write dot tree in a file
        File file = new File(name + ".dot");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(dotTree);
        writer.flush();
        writer.close();

        // Create pdf from dot tree file (if dot command exists)
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("dot", "-Tpdf", name + ".dot", "-o", name + ".pdf");
        processBuilder.start();
    }
}
