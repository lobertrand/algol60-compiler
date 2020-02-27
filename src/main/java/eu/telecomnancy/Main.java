package eu.telecomnancy;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.codegen.Assembly;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.IOListener;
import eu.telecomnancy.tools.IOUtils;
import java.io.*;
import org.antlr.runtime.*;
import projetIUP.Lanceur;

public class Main {

    public static void main(String[] args) {
        CharStream input = parseArguments(args);

        // Lexical and syntactic analysis
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        Algol60Parser.prog_return pr = null;

        try {
            pr = parser.prog();
        } catch (Exception e) {
            e.printStackTrace();
            IOUtils.exit();
        }
        reportLexicalExceptions(lexer, input);
        reportSyntacticExceptions(parser, input);
        if (lexer.hasExceptions() || parser.hasExceptions()) IOUtils.exit();

        // Semantic analysis
        SymbolTable symbolTable = new SymbolTable();
        PredefinedSymbols.get().forEach(symbolTable::define);
        SemanticAnalysisVisitor semanticAnalysisVisitor = new SemanticAnalysisVisitor(symbolTable);
        pr.getTree().accept(semanticAnalysisVisitor);
        IOUtils.print(symbolTable);
        reportSemanticExceptions(semanticAnalysisVisitor, input);
        if (semanticAnalysisVisitor.hasExceptions()) IOUtils.exit();

        Assembly asm = Assembly.exampleWrite("HELLO  ");
        assembleAndExecute(asm.toString());
    }

    private static void assembleAndExecute(String asm) {
        IOUtils.writeStringToFile(asm, "program.asm");

        IOListener.listen(() -> Lanceur.main(new String[] {"-ass", "program.asm"}))
                .ifError(IOUtils::logError)
                .ifError(IOUtils::exit)
                .ifNoError(() -> IOUtils.log("Assembly code compiled successfully"));

        IOUtils.log("Executing program...");
        Lanceur.main(new String[] {"-batch", "program.iup"});
    }

    private static void reportSemanticExceptions(
            SemanticAnalysisVisitor semanticAnalysisVisitor, CharStream input) {
        if (semanticAnalysisVisitor.hasExceptions()) {
            for (SemanticException e : semanticAnalysisVisitor.getExceptions()) {
                IOUtils.printSemanticException(e, input.toString());
            }
            int n = semanticAnalysisVisitor.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d semantic exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Semantic analysis successful");
        }
    }

    private static void reportSyntacticExceptions(Algol60Parser parser, CharStream input) {
        if (parser.hasExceptions()) {
            for (RecognitionException e : parser.getExceptions()) {
                IOUtils.printRecognitionException(e, input.toString());
            }
            int n = parser.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d syntactic exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Syntactic analysis successful");
        }
    }

    private static void reportLexicalExceptions(Algol60Lexer lexer, CharStream input) {
        if (lexer.hasExceptions()) {
            for (RecognitionException e : lexer.getExceptions()) {
                IOUtils.printRecognitionException(e, input.toString());
            }
            int n = lexer.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d lexical exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Lexical analysis successful");
        }
    }

    private static CharStream parseArguments(String[] args) {
        try {
            if (args.length == 0) {
                IOUtils.logError("Possible arguments:");
                IOUtils.logError("    <Path to Algol60 file>");
                IOUtils.logError("    <begin> <integer> <a> <;> ... <end>");
                IOUtils.exit();
            } else if (args.length == 1) {
                if (!new File(args[0]).exists()) {
                    IOUtils.logError("File '" + args[0] + "' doesn't exit");
                    IOUtils.exit();
                }
                return new ANTLRInputStream(new FileInputStream(args[0]));
            } else {
                return new ANTLRStringStream(String.join(" ", args));
            }
        } catch (Exception e) {
            IOUtils.logError(e);
            IOUtils.exit();
        }
        return null;
    }
}
