package eu.telecomnancy.codegen;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.IOListener;
import eu.telecomnancy.tools.IOUtils;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import projetIUP.Lanceur;

public class Helper {

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
        PredefinedSymbols.get().forEach(symbolTable::define);
        SemanticAnalysisVisitor semanticAnalysisVisitor = new SemanticAnalysisVisitor(symbolTable);
        ast.accept(semanticAnalysisVisitor);

        if (semanticAnalysisVisitor.hasExceptions()) {
            SemanticException e = semanticAnalysisVisitor.getExceptions().get(0);
            IOUtils.printSemanticException(e, input.toString());
            throw e;
        }

        Assembly asm = new Assembly();
        CodeGeneratorVisitor codeGenerator = new CodeGeneratorVisitor(symbolTable, asm);
        codeGenerator.setInput(String.valueOf(input));
        ast.accept(codeGenerator);

        IOUtils.writeStringToFile(asm.toString(), "build/tmp/test_program.asm");

        String[] compileArgs = {"-ass", "build/tmp/test_program.asm"};
        IOListener.listen(() -> Lanceur.main(compileArgs))
                .ifError(IOUtils::logError)
                .ifError(IOUtils::exit);

        String[] execArgs = {"-batch", "build/tmp/test_program.iup"};
        String output = IOListener.listen(() -> Lanceur.main(execArgs)).getOutput();

        Result result = new Result();
        result.output = trimOutput(output);
        return result;
    }

    private static String trimOutput(String output) {
        return output.replaceFirst("(?s)Simulation terminée --.*", "").trim();
    }
}
