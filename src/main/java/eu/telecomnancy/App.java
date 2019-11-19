package eu.telecomnancy;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.stringtemplate.*;
import java.io.*;


public class App {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
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
    }

}

