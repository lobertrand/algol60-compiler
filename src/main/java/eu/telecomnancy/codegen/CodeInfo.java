package eu.telecomnancy.codegen;

public class CodeInfo {
    private static final CodeInfo EMPTY = new CodeInfo();

    private CodeInfo() {}

    public static CodeInfo empty() {
        return EMPTY;
    }
}
