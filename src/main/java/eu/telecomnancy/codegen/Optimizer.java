package eu.telecomnancy.codegen;

public class Optimizer {

    public String optimize(String assemblyCode) {
        assemblyCode = suppressRedundantPushPop(assemblyCode);
        assemblyCode = suppressRedundantNewlines(assemblyCode);
        return assemblyCode;
    }

    private String suppressRedundantPushPop(String assemblyCode) {
        String regex = " STW (R\\d+) , -\\(SP\\).*\n LDW \\1 , \\(SP\\).*";
        regex = spacesAreAnyWhitespace(regex);
        return assemblyCode.replaceAll(regex, "");
    }

    private String suppressRedundantNewlines(String assemblyCode) {
        return assemblyCode.replaceAll("\n\n\n+", "\n\n\n");
    }

    private String spacesAreAnyWhitespace(String regex) {
        return regex.replaceAll(" ", "\\\\s*");
    }
}
