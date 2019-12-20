package eu.telecomnancy.ast;

public class RootAST extends DefaultAST {

    @Override
    public <R> R accept(ASTVisitor<R> v) {
        return v.visit(this);
    }
    
}
