package eu.telecomnancy;

<<<<<<< HEAD
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;
import java.io.*;

||||||| merged common ancestors
import org.antlr.runtime.*;
=======
import eu.telecomnancy.tools.IOUtils;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;
>>>>>>> 9503d38890590f51cb1b4cd4de082649c2e9d36f

public class App {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
<<<<<<< HEAD
        Algol60Parser parser = new Algol60Parser(tokens);
        Algol60Parser.prog_return pr = parser.prog();

        // Get the tree in dot tree format
        CommonTree tree = (CommonTree) pr.getTree();
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        String dotTree = st.toString();

        // Write dot tree in a file
        File file = new File("AST.dot");
        file.createNewFile();
        FileWriter writer = new FileWriter(file); 
        writer.write(dotTree); 
        writer.flush();
        writer.close();

        // Create pdf from dot tree file (if dot command exists)
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("dot", "-Tpdf", "AST.dot", "-o", "AST.pdf");
        try {
            processBuilder.start();
        } catch (Exception e) {}
||||||| merged common ancestors
        ExprParser parser = new ExprParser(tokens);
        parser.prog();
=======
        Algol60Parser parser = new Algol60Parser(tokens);
        Algol60Parser.prog_return pr = parser.prog();

        Tree tree = pr.getTree();
        IOUtils.generateDotTree(tree, "AST");

        parcours(tree, "");
>>>>>>> 9503d38890590f51cb1b4cd4de082649c2e9d36f
    }

    public static void parcours(Tree tree, String space) {
        System.out.println(space + tree.toString() + " : " + tree.getLine());
        for (int i = 0; i < tree.getChildCount(); i++) {
            parcours(tree.getChild(i), space + "  ");
        }
    }
}

