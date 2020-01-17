package eu.telecomnancy.symbols;

public class Variable extends Symbol {

    private boolean resultValue;

    public Variable(String idf, Type type) {
        super(idf, type, Kind.VARIABLE);
        resultValue = false;
    }

    public void setResultValue(boolean returnValue) {
        this.resultValue = returnValue;
    }

    public boolean isResultValue() {
        return resultValue;
    }

    @Override
    public String toString() {
        return String.format(
                "Variable: %s %s %s",
                getType(), getIdentifier(), resultValue ? "(proc. result)" : "");
    }
}
