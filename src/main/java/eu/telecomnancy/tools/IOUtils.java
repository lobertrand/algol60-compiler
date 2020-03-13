package eu.telecomnancy.tools;

import static eu.telecomnancy.tools.StringTools.*;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.syntax.InvalidNumberException;
import java.io.*;
import java.util.Optional;
import java.util.Scanner;
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

    public static void printRecognitionException(RecognitionException e, String input) {
        Optional<String> token = Optional.ofNullable(e.token).map(Token::getText);

        String msg;
        try {
            throw e;
        } catch (NoViableAltException ex) {
            msg = token.map(t -> "Unexpected token '" + t + "'").orElse("Unexpected token");
        } catch (FailedPredicateException ex) {
            msg = "Failed predicate at token '" + ex.token.getText() + "'";
        } catch (MissingTokenException ex) {
            if (ex.expecting >= 0) {
                msg = "Missing token " + Algol60Parser.tokenNames[ex.expecting];
            } else {
                msg = "Missing token";
            }
        } catch (MismatchedTokenException ex) {
            msg = token.map(t -> "Mismatched token '" + t + "'").orElse("Mismatch token");
        } catch (InvalidNumberException ex) {
            msg = ex.message;
        } catch (RecognitionException ex) {
            msg =
                    token.map(t -> "Recognition error at token '" + t + "'")
                            .orElse("Recognition error");
        }
        msg += " at line " + e.line;
        logError(msg);
        printInputLine(e.line, e.charPositionInLine, input);
    }

    public static void printSemanticException(SemanticException e, String input) {
        logError(e);
        printInputLine(e.getLine(), e.getColumn(), input, e.getAst().shouldShiftCursorLeft());
    }

    private static void printInputLine(int lineNumber, int colNumber, String text) {
        printInputLine(lineNumber, colNumber, text, false);
    }

    private static void printInputLine(
            int lineNumber, int colNumber, String text, boolean shiftLeft) {
        String[] inputLines = text.split("\n");
        if (lineNumber - 1 >= 0 && lineNumber - 1 < inputLines.length) {
            String line = inputLines[lineNumber - 1].replace('\t', ' ');

            if (shiftLeft) colNumber = getShiftedColNumber(colNumber, line);

            String pointer = repeat(" ", colNumber) + YELLOW + "^" + RESET;
            System.out.println(String.format(" %5d | %s", lineNumber, line));
            System.out.println(String.format(" %5s | %s", "", pointer));
        }
    }

    private static int getShiftedColNumber(int colNumber, String line) {
        int right = colNumber - 1;
        while (right >= 0 && line.charAt(right) == ' ') right--;
        int left = right - 1;
        while (left >= 0 && line.charAt(left) != ' ') left--;
        int middle = left + (right - left + 1) / 2;
        if (left != 0) colNumber = middle;
        return Math.max(colNumber, 0);
    }

    public static void generateDotTree(Tree tree, String name)
            throws IOException, InterruptedException {
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        String dotTree = st.toString();

        // Write dot tree in a file
        writeStringToFile(dotTree, name + ".dot");

        // Create pdf from dot tree file (if dot command exists)
        ProcessBuilder pb = new ProcessBuilder("dot", "-Tpdf", name + ".dot", "-o", name + ".pdf");
        Process p = pb.start();
        p.waitFor();
        p.destroy();
    }

    public static void writeStringToFile(String str, String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(str);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            logError(e);
        }
    }

    public static String loadString(String resourcePath) {
        try {
            InputStream inputStream = IOUtils.class.getResourceAsStream(resourcePath);
            Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
            return scanner.next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
