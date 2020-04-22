package eu.telecomnancy.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import picocli.CommandLine.*;

public class ArgsParser implements Callable<ArgsParser.Options> {

    @Parameters(index = "0", description = "Algol60 file to parse and compile to an .iup file.")
    private String file;

    @Option(
            names = {"-a", "--print-ast"},
            description = "Print AST in the console.")
    private boolean printAst;

    @Option(
            names = {"-p", "--pdf-ast"},
            description = "Generate a .dot and a .pdf file of the AST.")
    private boolean pdfAst;

    @Option(
            names = {"-s", "--print-st"},
            description = "Print Symbol Table in the console.")
    private boolean printSymbolTable;

    @Option(
            names = {"-q", "--quiet"},
            description = "Don't log anything except errors and what's requested by other options.")
    private boolean quiet;

    @Option(
            names = {"-r", "--run-iup"},
            description = "Run generated .iup file immediately after compilation.")
    private boolean runIup;

    @Option(
            names = {"-n", "--no-optimization"},
            description = "Disable assembly code optimization.")
    private boolean noCodeOptimization;

    @Override
    public Options call() {
        Options result = new Options();
        try {
            result.charStream = new ANTLRInputStream(new FileInputStream(file));
        } catch (IOException e) {
            IOUtils.logError(e);
            IOUtils.exit();
        }
        result.pdfAst = pdfAst;
        result.printAst = printAst;
        result.printSymbolTable = printSymbolTable;
        result.quiet = quiet;
        result.runIup = runIup;
        result.optimizeCode = !noCodeOptimization;
        return result;
    }

    public static class Options {
        private CharStream charStream;
        private boolean printAst;
        private boolean pdfAst;
        private boolean printSymbolTable;
        private boolean quiet;
        private boolean runIup;
        private boolean optimizeCode;

        public CharStream getCharStream() {
            return charStream;
        }

        public boolean shouldPrintAst() {
            return printAst;
        }

        public boolean shouldGeneratePdfAst() {
            return pdfAst;
        }

        public boolean shouldPrintSymbolTable() {
            return printSymbolTable;
        }

        public boolean shouldBeQuiet() {
            return quiet;
        }

        public boolean shouldRunIup() {
            return runIup;
        }

        public boolean shouldOptimizeCode() {
            return optimizeCode;
        }
    }
}
