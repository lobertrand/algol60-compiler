package eu.telecomnancy;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.tools.IOUtils;
import java.io.FileInputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class App {

    public static void main(String[] args) throws Exception {
        // Program arguments
        if (args.length > 1) {
            System.err.println(
                    "Algol60> No more than one argument is expected (an Algol60 filename).");
            System.exit(1);
        }
        ANTLRInputStream input = null;
        if (args.length == 1) {
            try {
                input = new ANTLRInputStream(new FileInputStream(args[0]));
            } catch (Exception e) {
                System.err.format(
                        "Algol60> %s: %s\n", e.getClass().getSimpleName(), e.getMessage());
                System.exit(1);
            }
        } else {
            input = new ANTLRInputStream(System.in);
        }

        // Syntactic analysis
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());

        Algol60Parser.prog_return pr = null;
        try {
            pr = parser.prog();
            System.out.println("Algol60> Syntactic analysis successful");
        } catch (RecognitionException e) {
            System.err.format(
                    "Algol60> %s (line %d) at token \"%s\"\n",
                    e.getClass().getSimpleName(), e.line, e.token.getText());
            System.exit(0);
        }

        // AST generation
        DefaultAST ast = (DefaultAST) pr.getTree();
        try {
            IOUtils.generateDotTree(ast, "AST");
            System.out.println("Algol60> AST.dot and AST.pdf generated");
        } catch (Exception e) {
            System.err.println("Algol60> " + e.getMessage());
        }

        // Symbol table initialization
        SymbolTable symbolTable = new SymbolTable();

        // Semantic analysis
        SemanticAnalysisVisitor semanticAnalysisVisitor = new SemanticAnalysisVisitor(symbolTable);
        ast.accept(semanticAnalysisVisitor);
        System.out.println(symbolTable);

        if (semanticAnalysisVisitor.getExceptions().isEmpty()
                && semanticAnalysisVisitor.getUndeclaredLabels().isEmpty()) {
            System.out.println("Algol60> Semantic analysis successful");
        } else {
            for (SemanticException e : semanticAnalysisVisitor.getExceptions()) {
                System.out.println("Algol60> " + e);
            }
        }
    }
}
