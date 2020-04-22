package eu.telecomnancy;

import static eu.telecomnancy.tools.ArgsParser.*;
import static picocli.CommandLine.*;

import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.codegen.Assembly;
import eu.telecomnancy.codegen.CodeGeneratorVisitor;
import eu.telecomnancy.codegen.Optimizer;
import eu.telecomnancy.codegen.UniqueReference;
import eu.telecomnancy.semantic.SemanticAnalysisVisitor;
import eu.telecomnancy.semantic.SemanticException;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.ASTTools;
import eu.telecomnancy.tools.ArgsParser;
import eu.telecomnancy.tools.IOListener;
import eu.telecomnancy.tools.IOUtils;
import org.antlr.runtime.*;
import picocli.CommandLine;
import picocli.CommandLine.Help.Ansi.Style;
import projetIUP.Lanceur;

public class Main {

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new ArgsParser());
        cmd.setColorScheme(createColorScheme());
        if (cmd.execute(args) != 0) {
            return;
        }
        Options options = cmd.getExecutionResult();
        IOUtils.setQuiet(options.shouldBeQuiet());

        CharStream input = options.getCharStream();

        // Lexical and syntactic analysis
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
        DefaultAST ast = pr.getTree();
        if (options.shouldPrintAst()) {
            ASTTools.print(ast);
        }
        if (options.shouldGeneratePdfAst()) {
            IOUtils.generateDotTree(ast, "ast");
        }
        reportLexicalExceptions(lexer, input);
        reportSyntacticExceptions(parser, input);
        if (lexer.hasExceptions() || parser.hasExceptions()) IOUtils.exit();

        // Semantic analysis
        SymbolTable symbolTable = new SymbolTable();
        UniqueReference uniqueReference = new UniqueReference();
        PredefinedSymbols.create(uniqueReference).forEach(symbolTable::define);
        SemanticAnalysisVisitor semanticAnalysisVisitor =
                new SemanticAnalysisVisitor(symbolTable, uniqueReference);
        ast.accept(semanticAnalysisVisitor);
        if (options.shouldPrintSymbolTable()) {
            IOUtils.print(symbolTable);
        }
        reportSemanticExceptions(semanticAnalysisVisitor, input);
        if (semanticAnalysisVisitor.hasExceptions()) IOUtils.exit();

        Assembly asm = new Assembly();
        CodeGeneratorVisitor codeGenerator =
                new CodeGeneratorVisitor(symbolTable, asm, uniqueReference);
        codeGenerator.setInput(String.valueOf(input));
        ast.accept(codeGenerator);
        IOUtils.log("Generated assembly code");

        String code;
        if (options.shouldOptimizeCode()) {
            Optimizer optimizer = new Optimizer();
            code = optimizer.optimize(asm.toString());
            IOUtils.log("Applied code optimizations");
        } else {
            code = asm.toString();
        }
        assembleAndExecute(code, options);
    }

    private static void assembleAndExecute(String asm, Options options) {
        IOUtils.writeStringToFile(asm, "program.asm");

        IOListener.listen(() -> Lanceur.main(new String[] {"-ass", "program.asm"}))
                .ifError(IOUtils::logError)
                .ifError(IOUtils::exit)
                .ifNoError(() -> IOUtils.log("Assembly code compiled successfully"));

        if (options.shouldRunIup()) {
            IOUtils.log("Executing program...");
            Lanceur.main(new String[] {"-batch", "program.iup"});
        }
    }

    private static void reportSemanticExceptions(
            SemanticAnalysisVisitor semanticAnalysisVisitor, CharStream input) {
        if (semanticAnalysisVisitor.hasExceptions()) {
            for (SemanticException e : semanticAnalysisVisitor.getExceptions()) {
                IOUtils.printSemanticException(e, input.toString());
            }
            int n = semanticAnalysisVisitor.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d semantic exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Semantic analysis successful");
        }
    }

    private static void reportSyntacticExceptions(Algol60Parser parser, CharStream input) {
        if (parser.hasExceptions()) {
            for (RecognitionException e : parser.getExceptions()) {
                IOUtils.printRecognitionException(e, input.toString());
            }
            int n = parser.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d syntactic exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Syntactic analysis successful");
        }
    }

    private static void reportLexicalExceptions(Algol60Lexer lexer, CharStream input) {
        if (lexer.hasExceptions()) {
            for (RecognitionException e : lexer.getExceptions()) {
                IOUtils.printRecognitionException(e, input.toString());
            }
            int n = lexer.getExceptions().size();
            IOUtils.logError(
                    String.format("Terminated with %d lexical exception%s", n, n > 1 ? "s" : ""));
        } else {
            IOUtils.log("Lexical analysis successful");
        }
    }

    private static Help.ColorScheme createColorScheme() {
        return new Help.ColorScheme.Builder()
                .commands(Style.bold)
                .options(Style.fg_cyan)
                .parameters(Style.fg_yellow)
                .build();
    }
}
