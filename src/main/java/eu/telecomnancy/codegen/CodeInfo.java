package eu.telecomnancy.codegen;

public class CodeInfo {

    private int value;

    private CodeInfo() {
        this.value = 0;
    }

    public void setValue(String s) {
        value = Integer.parseInt(s);
    }

    public int getValue() {
        return this.value;
    }

    public static CodeInfo empty() {
        return new CodeInfo();
    }
}
