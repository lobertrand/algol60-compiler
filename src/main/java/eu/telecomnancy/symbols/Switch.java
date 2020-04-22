package eu.telecomnancy.symbols;

import eu.telecomnancy.ast.DefaultAST;
import eu.telecomnancy.codegen.UniqueReference;
import java.util.ArrayList;
import java.util.List;

public class Switch extends Symbol {
    private List<String> names;
    private List<DefaultAST> labelTrees;
    private String beginAsmLabel;
    private String endAsmLabel;
    private List<Label> labels;

    public Switch(String idf) {
        super(idf, Type.VOID, Kind.SWITCH);
        this.names = new ArrayList<>();
        this.labelTrees = new ArrayList<>();
        this.labels = new ArrayList<>();
    }

    public String getBeginAsmLabel() {
        return beginAsmLabel;
    }

    public String getEndAsmLabel() {
        return endAsmLabel;
    }

    public Switch withAsmLabel(UniqueReference uniqueReference) {
        this.beginAsmLabel = uniqueReference.forLabel("BEGIN_" + getIdentifier());
        this.endAsmLabel = uniqueReference.forLabel("END_" + getIdentifier());
        return this;
    }

    @Override
    public String toString() {
        String values = String.join(", ", names);
        return String.format("Switch: %s [%s]", getIdentifier(), values);
    }

    public int getLabelNumber() {
        return labels.size();
    }

    public void addLabelName(String string, DefaultAST ast) {
        this.names.add(string);
        this.labelTrees.add(ast);
    }

    public void addLabel(Label l) {
        this.labels.add(l);
    }

    public List<Label> getLabels() {
        return labels;
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
