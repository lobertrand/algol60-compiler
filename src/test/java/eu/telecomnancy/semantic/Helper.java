package eu.telecomnancy.semantic;

import static org.junit.Assert.assertEquals;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.symbols.PredefinedSymbols;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.tools.IOUtils;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Assert;

public class Helper {

    public static class Result {
        public SymbolTable symbolTable;
        public List<SemanticException> exceptions;
        public DefaultAST ast;
        public String text;

        public boolean exceptionAt(int line) {
            for (SemanticException e : exceptions) if (e.getLine() == line) return true;
            return false;
        }

        public void printExceptions() {
            exceptions.forEach(e -> IOUtils.printSemanticException(e, String.valueOf(text)));
        }
    }

    public static Result checkSemantics(Object content) throws RecognitionException {
        String text = String.valueOf(content);
        ANTLRStringStream stream = new ANTLRStringStream(text);
        Algol60Lexer lexer = new Algol60Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        DefaultAST ast = parser.prog().getTree();

        if (lexer.hasExceptions()) {
            RecognitionException e = lexer.getExceptions().get(0);
            IOUtils.printRecognitionException(e, stream.toString());
            throw e;
        }
        if (parser.hasExceptions()) {
            RecognitionException e = parser.getExceptions().get(0);
            IOUtils.printRecognitionException(e, stream.toString());
            throw e;
        }

        SymbolTable symbolTable = new SymbolTable();
        PredefinedSymbols.get().forEach(symbolTable::define);
        SemanticAnalysisVisitor visitor = new SemanticAnalysisVisitor(symbolTable);
        ast.accept(visitor);
        Result result = new Result();
        result.symbolTable = symbolTable;
        result.exceptions = visitor.getExceptions();
        result.ast = ast;
        result.text = text;
        return result;
    }

    public static int subTables(SymbolTable symbolTable) {
        int res = 0;
        for (SymbolTable child : symbolTable.getChildren()) {
            res += (1 + subTables(child));
        }
        return res;
    }

    public static Set<Integer> getLines(Collection<SemanticException> exceptions) {
        return exceptions.stream().map(SemanticException::getLine).collect(Collectors.toSet());
    }

    public static void assertExceptionQuantity(int n, Result result) {
        if (n != result.exceptions.size()) result.printExceptions();
        assertEquals("There should be " + n + " exceptions", n, result.exceptions.size());
    }

    public static void assertExceptionAtLine(int line, Class<?> expectedClass, Result result) {
        String lineOfCode = result.text.split("\n")[line - 1].trim();
        String msg = "There should be an exception at line " + line + ": '" + lineOfCode + "'";
        Assert.assertTrue(msg, result.exceptionAt(line));
        boolean present =
                result.exceptions.stream()
                        .filter(e -> e.getLine() == line)
                        .anyMatch(e -> expectedClass.isAssignableFrom(e.getClass()));
        if (!present) result.printExceptions();
        String msg2 = "Exception should be of type " + expectedClass.getSimpleName();
        Assert.assertTrue(msg2, present);
    }
}
