package eu.telecomnancy.semantic;

public class SemanticException extends RuntimeException {

    private static final long serialVersionUID = -1149673445084903865L;
    
    private int line = -1;

    public SemanticException(String msg) {
        super(msg);
    }
    
    public SemanticException(String msg, int line) {
        super(msg);
        this.line = line;
    }
    
    public void print() {
        if (line != -1) {
            System.out.println("SemanticError (line " + line + "): " + getMessage());
        } else {
            System.out.println("SemanticError : " + getMessage());            
        }
    }
    
}
