package eu.telecomnancy.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.antlr.runtime.tree.DOTTreeGenerator;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;

public class IOUtils {

    public static void generateDotTree(Tree tree, String name) {
        DOTTreeGenerator gen = new DOTTreeGenerator();
        StringTemplate st = gen.toDOT(tree);
        String dotTree = st.toString();

        // Write dot tree in a file
        File file = new File("AST.dot");
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(dotTree);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create pdf from dot tree file (if dot command exists)
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("dot", "-Tpdf", "AST.dot", "-o", "AST.pdf");
        try {
            processBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
