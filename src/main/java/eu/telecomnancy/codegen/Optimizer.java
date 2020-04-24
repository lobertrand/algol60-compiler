package eu.telecomnancy.codegen;

public class Optimizer {

    public String optimize(String assemblyCode) {
        return suppressRedundantPushPop(assemblyCode);
    }

    private String suppressRedundantPushPop(String assemblyCode) {
        String regex = " STW (R\\d+) , -\\(SP\\).*\n LDW \\1 , \\(SP\\)\\+.*";
        regex = spacesAreAnyWhitespace(regex);
        return assemblyCode.replaceAll(regex, "");
    }

    private String spacesAreAnyWhitespace(String regex) {
        return regex.replaceAll(" ", "\\\\s*");
    }
}
