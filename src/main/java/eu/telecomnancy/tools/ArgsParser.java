package eu.telecomnancy.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CharStream;
import picocli.CommandLine.*;

@Command(
        name = "java -jar algol60_compiler.jar",
        headerHeading = "%nUsage:%n",
        synopsisHeading = "%n",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n")
public class ArgsParser implements Callable<ArgsParser.Options> {

    @Parameters(index = "0", description = "name or path of the algol60 file to compile")
    private String fileName;

    @Option(
            names = {"-o", "--output"},
            paramLabel = "output_name",
            description = "name or path of the output file")
    private String outputName;

    @Option(
            names = {"-a", "--print-ast"},
            description = "print AST in the console")
    private boolean printAst;

    @Option(
            names = {"-p", "--pdf-ast"},
            description = "generate a .dot and a .pdf file of the AST")
    private boolean pdfAst;

    @Option(
            names = {"-s", "--print-st"},
            description = "print Symbol Table in the console")
    private boolean printSymbolTable;

    @Option(
            names = {"-q", "--quiet"},
            description =
                    "don't log anything except errors and what's being requested to be printed by other options")
    private boolean quiet;

    @Option(
            names = {"-r", "--run-iup"},
            description = "generate an .iup file and run it")
    private boolean runProgram;

    @Option(
            names = {"-n", "--no-optimization"},
            description = "disable assembly code optimization")
    private boolean noCodeOptimization;

    @Override
    public Options call() {
        Options result = new Options();
        try {
            result.charStream = new ANTLRInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            IOUtils.logError("No such file: " + fileName);
            IOUtils.exit();
        } catch (IOException e) {
            IOUtils.logError(e.getClass().getSimpleName() + ": " + e.getMessage());
            IOUtils.exit();
        }
        result.fileName = fileName;
        result.outputName = outputName;
        result.pdfAst = pdfAst;
        result.printAst = printAst;
        result.printSymbolTable = printSymbolTable;
        result.quiet = quiet;
        result.runProgram = runProgram;
        result.optimizeCode = !noCodeOptimization;
        return result;
    }

    public static class Options {
        private CharStream charStream;
        private String fileName;
        private String outputName;
        private boolean printAst;
        private boolean pdfAst;
        private boolean printSymbolTable;
        private boolean quiet;
        private boolean runProgram;
        private boolean optimizeCode;

        public CharStream getCharStream() {
            return charStream;
        }

        public String getFileName() {
            return fileName;
        }

        public String getOutputName() {
            return outputName;
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

        public boolean shouldRunProgram() {
            return runProgram;
        }

        public boolean shouldOptimizeCode() {
            return optimizeCode;
        }
    }
}
