package eu.telecomnancy.semantic;

import static org.junit.Assert.*;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.symbols.Symbol;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import java.util.List;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class SemanticAnalysisVisitorTest {

    private static class Result {
        public SymbolTable symbolTable;
        public List<SemanticException> exceptions;

        public Result(SymbolTable symbolTable, List<SemanticException> exceptions) {
            this.symbolTable = symbolTable;
            this.exceptions = exceptions;
        }
    }

    private Result checkSemantics(String content) throws RecognitionException {
        ANTLRStringStream stream = new ANTLRStringStream(content);
        Algol60Lexer lexer = new Algol60Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        DefaultAST ast = (DefaultAST) parser.prog().getTree();
        SymbolTable symbolTable = new SymbolTable();
        SemanticAnalysisVisitor visitor = new SemanticAnalysisVisitor(symbolTable);
        ast.accept(visitor);
        return new Result(symbolTable, visitor.getExceptions());
    }

    private int subTables(SymbolTable symbolTable) {
        int res = 0;
        for (SymbolTable child : symbolTable.getChildren()) {
            res += (1 + subTables(child));
        }
        return res;
    }

    @Test
    public void testVarDec_simple() throws RecognitionException {
        String content = "begin integer a end";

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 1 sub table", 1, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
    }

    @Test
    public void testVarDec_innerBlock() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  integer a;\n";
        content += "  begin real a, b end\n";
        content += "end\n";

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
        assertTrue("a should be declared in second scope", second.isDeclaredInScope("a"));
        assertTrue("b should be declared in second scope", second.isDeclaredInScope("b"));
    }

    @Test
    public void testVarDec_types() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  integer a;\n";
        content += "  begin real a; string b end\n";
        content += "end\n";

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
        assertTrue("a should be declared in second scope", second.isDeclaredInScope("a"));
        assertTrue("b should be declared in second scope", second.isDeclaredInScope("b"));

        Symbol a1 = first.resolve("a");
        Symbol a2 = second.resolve("a");
        Symbol b = second.resolve("b");
        assertEquals("a in first scope should be an integer", a1.getType(), Type.INTEGER);
        assertEquals("a in second scope should be a real", a2.getType(), Type.REAL);
        assertEquals("b should be a string", b.getType(), Type.STRING);
    }
}
