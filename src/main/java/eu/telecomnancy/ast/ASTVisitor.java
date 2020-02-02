package eu.telecomnancy.ast;

public interface ASTVisitor<R> {

    R visit(DefaultAST ast);

    R visit(RootAST ast);

    R visit(BlockAST ast);

    R visit(VarDecAST ast);

    R visit(ProcDecAST ast);

    R visit(ProcCallAST ast);

    R visit(IfStatementAST ast);

    R visit(ForClauseAST ast);

    R visit(WhileClauseAST ast);

    R visit(AssignmentAST ast);

    R visit(ArrayDecAST ast);

    R visit(ArrayAssignmentAST ast);

    R visit(MultAST ast);

    R visit(DivAST ast);

    R visit(ArrayCallAST ast);

    R visit(IntAST ast);

    R visit(Pow10AST ast);

    R visit(PowAST ast);

    R visit(RealAST ast);

    R visit(StrAST ast);

    R visit(IntDivAST ast);

    R visit(AddAST ast);

    R visit(MinusAST ast);

    R visit(LabelDecAST ast);

    R visit(GoToAST ast);

    R visit(LogicalValueAST ast);

    R visit(AndAST ast);

    R visit(OrAST ast);

    R visit(ImplyAST ast);

    R visit(EquivalentAST ast);

    R visit(GreaterThanAST ast);

    R visit(LessThanAST ast);

    R visit(GreaterEqualAST ast);

    R visit(LessEqualAST ast);

    R visit(EqualAST ast);

    R visit(NotEqualAST ast);

    R visit(IdentifierAST ast);
}
