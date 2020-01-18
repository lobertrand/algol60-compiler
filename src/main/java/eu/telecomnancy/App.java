package eu.telecomnancy;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.tools.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class App {

    public static void main(String[] args) {
        // Program arguments
        if (args.length > 1) {
            IOUtils.logError("No more than one argument is expected (an Algol60 filename).");
            IOUtils.exit();
        }
        ANTLRInputStream input = null;
        if (args.length == 1) {
            try {
                input = new ANTLRInputStream(new FileInputStream(args[0]));
            } catch (Exception e) {
                IOUtils.logError(e.getClass().getSimpleName() + ": " + e.getMessage());
                IOUtils.exit();
            }
        } else {
            try {
                input = new ANTLRInputStream(System.in);
            } catch (IOException e) {
                IOUtils.logError(e.getMessage());
                IOUtils.exit();
            }
        }

        // Syntactic analysis
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

        if (parser.hasExceptions()) {
            for (RecognitionException e : parser.getExceptions()) {
                IOUtils.printRecognitionException(e, input);
            }
            IOUtils.exit();
        } else {
            IOUtils.log("Syntactic analysis successful");
        }

        // AST generation
        DefaultAST ast = pr.getTree();
        try {
            IOUtils.generateDotTree(ast, "AST");
            IOUtils.log("AST.dot and AST.pdf generated");
        } catch (IOException e) {
            IOUtils.logError(e.getMessage());
        }

        // Symbol table initialization
        SymbolTable symbolTable = new SymbolTable();

        // Semantic analysis
        SemanticAnalysisVisitor semanticAnalysisVisitor = new SemanticAnalysisVisitor(symbolTable);
        ast.accept(semanticAnalysisVisitor);
        IOUtils.print(symbolTable);

        if (semanticAnalysisVisitor.getExceptions().isEmpty()) {
            IOUtils.log("Semantic analysis successful");
        } else {
            for (SemanticException e : semanticAnalysisVisitor.getExceptions()) {
                IOUtils.printSemanticException(e, input);
            }
            IOUtils.exit();
        }
    }
}
