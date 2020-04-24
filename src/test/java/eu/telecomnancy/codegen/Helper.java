package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.IOUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class Helper {

    public static final String TEMP_PROGRAM_NAME = "build/tmp/test_program";

    public static class Result {
        public String output;
    }

    public static Result parse(Object source) throws Exception {
        String code = String.valueOf(source);
        CharStream input = new ANTLRStringStream(code);

        // Lexical and syntactic analysis
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        Algol60Parser.prog_return pr = parser.prog();

        if (lexer.hasExceptions()) {
            RecognitionException e = lexer.getExceptions().get(0);
            IOUtils.printRecognitionException(e, input.toString());
            throw e;
        }
        if (parser.hasExceptions()) {
            RecognitionException e = parser.getExceptions().get(0);
            IOUtils.printRecognitionException(e, input.toString());
            throw e;
        }

        // Semantic analysis
        DefaultAST ast = pr.getTree();
        SymbolTable symbolTable = new SymbolTable();
        UniqueReference uniqueReference = new UniqueReference();
        PredefinedSymbols.create(uniqueReference).forEach(symbolTable::define);
        SemanticAnalysisVisitor semanticAnalysisVisitor =
                new SemanticAnalysisVisitor(symbolTable, uniqueReference);
        ast.accept(semanticAnalysisVisitor);

        if (semanticAnalysisVisitor.hasExceptions()) {
            SemanticException e = semanticAnalysisVisitor.getExceptions().get(0);
            IOUtils.printSemanticException(e, input.toString());
            throw e;
        }

        Assembly asm = new Assembly();
        CodeGeneratorVisitor codeGenerator =
                new CodeGeneratorVisitor(symbolTable, asm, uniqueReference);
        codeGenerator.setInput(String.valueOf(input));
        ast.accept(codeGenerator);

        String asmCode = asm.toString();
        asmCode = new Optimizer().optimize(asmCode);
        IOUtils.writeStringToFile(asmCode, TEMP_PROGRAM_NAME + ".asm");
        String[] compileCmd = {
            "java", "-jar", "lib/microPIUPK.jar", "-ass", TEMP_PROGRAM_NAME + ".asm"
        };
        CommandResult compileRes = exec(compileCmd);
        if (compileRes.hadError) {
            System.err.println(compileRes.output);
            System.err.println(compileRes.error);
            throw new IllegalStateException();
        }

        String[] execCmd = {
            "java", "-jar", "lib/microPIUPK.jar", "-batch", TEMP_PROGRAM_NAME + ".iup"
        };
        CommandResult execRes = exec(execCmd);
        if (execRes.hadError) {
            System.err.println(execRes.output);
            System.err.println(execRes.error);
            throw new IllegalStateException();
        }

        Result result = new Result();
        result.output = trimOutput(execRes.output);
        return result;
    }

    private static String trimOutput(String output) {
        return output.replaceFirst("(?s)Simulation terminée --.*", "").trim();
    }

    private static CommandResult exec(String... cmd) {
        CommandResult res = new CommandResult();
        try {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            final Process p = pb.start();
            p.waitFor();
            res.output = inputStreamToString(p.getInputStream());
            res.error = inputStreamToString(p.getErrorStream());
            res.hadError = p.exitValue() != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) sb.append(line).append("\n");
        return sb.toString();
    }

    private static class CommandResult {
        String output;
        String error;
        boolean hadError;
    }
}
