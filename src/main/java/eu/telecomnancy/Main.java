package eu.telecomnancy;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class Main {

    public static void main(String[] args) {
        ANTLRInputStream input = parseArguments(args);

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

        try {
            IOUtils.generateDotTree(pr.getTree(), "AST");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Semantic analysis
        SymbolTable symbolTable = new SymbolTable();
        PredefinedSymbols.get().forEach(symbolTable::define);
        SemanticAnalysisVisitor semanticAnalysisVisitor = new SemanticAnalysisVisitor(symbolTable);
        pr.getTree().accept(semanticAnalysisVisitor);
        IOUtils.print(symbolTable);
        reportSemanticExceptions(semanticAnalysisVisitor, input);

        // ASTTools.print(pr.getTree());
    }

    private static void reportSemanticExceptions(
            SemanticAnalysisVisitor semanticAnalysisVisitor, ANTLRInputStream input) {
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

    private static void reportSyntacticExceptions(Algol60Parser parser, ANTLRInputStream input) {
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

    private static void reportLexicalExceptions(Algol60Lexer lexer, ANTLRInputStream input) {
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

    private static ANTLRInputStream parseArguments(String[] args) {
        if (args.length > 1) {
            IOUtils.logError("Only one argument is expected (an Algol60 filename).");
            IOUtils.exit();
        }
        try {
            return args.length == 1
                    ? new ANTLRInputStream(new FileInputStream(args[0]))
                    : new ANTLRInputStream(System.in);
        } catch (Exception e) {
            IOUtils.logError(e.getClass().getSimpleName() + ": " + e.getMessage());
            IOUtils.exit();
        }
        return null;
    }
}
