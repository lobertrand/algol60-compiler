package eu.telecomnancy.symbols;

import eu.telecomnancy.ast.DefaultAST;
import java.util.ArrayList;
import java.util.List;

public class Switch extends Symbol {
    private List<String> names;
    private List<DefaultAST> labelTrees;

    public Switch(String idf) {
        super(idf, Type.VOID, Kind.SWITCH);
        this.names = new ArrayList<>();
        this.labelTrees = new ArrayList<>();
    }

    @Override
    public String toString() {
        String values = String.join(", ", names);
        return String.format("Switch: %s [%s]", getIdentifier(), values);
    }

    public void addLabelName(String string, DefaultAST ast) {
        this.names.add(string);
        this.labelTrees.add(ast);
    }

    public int getSize() {
        return names.size();
    }

    public List<String> getNames() {
        return names;
    }

    public List<DefaultAST> getLabelTrees() {
        return labelTrees;
    }
}
