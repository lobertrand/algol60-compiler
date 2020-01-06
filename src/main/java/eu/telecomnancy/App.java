package eu.telecomnancy;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.tools.IOUtils;
import java.io.FileInputStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

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
            System.exit(1);
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
        if (semanticAnalysisVisitor.getExceptions().isEmpty()) {
            System.out.println("Algol60> Semantic analysis successful");
        } else {
            for (Exception e : semanticAnalysisVisitor.getExceptions()) {
                System.err.println(e);
            }
        }

        System.out.println(symbolTable);

        // depthFirstSearch(ast, "");
    }

    public static void depthFirstSearch(Tree tree, String space) {
        System.out.println(space + tree.toString() + ": " + tree.getClass().getSimpleName());
        for (int i = 0; i < tree.getChildCount(); i++) {
            depthFirstSearch(tree.getChild(i), space + "  ");
        }
    }
}
