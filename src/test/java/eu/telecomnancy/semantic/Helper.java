package eu.telecomnancy.semantic;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.ASTAdaptor;
import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.symbols.SymbolTable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

public class Helper {

    public static class Result {
        public SymbolTable symbolTable;
        public List<SemanticException> exceptions;

        public Result(SymbolTable symbolTable, List<SemanticException> exceptions) {
            this.symbolTable = symbolTable;
            this.exceptions = exceptions;
        }
    }

    public static Result checkSemantics(Object content) throws RecognitionException {
        ANTLRStringStream stream = new ANTLRStringStream(String.valueOf(content));
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

    public static int subTables(SymbolTable symbolTable) {
        int res = 0;
        for (SymbolTable child : symbolTable.getChildren()) {
            res += (1 + subTables(child));
        }
        return res;
    }

    public static Set<Integer> exceptionLines(Collection<SemanticException> exceptions) {
        return exceptions.stream().map(SemanticException::getLine).collect(Collectors.toSet());
    }

    public static class Content {
        private StringBuilder sb;

        public Content() {
            sb = new StringBuilder();
        }

        public Content line(String content) {
            sb.append(content);
            sb.append("\n");
            return this;
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }
}
