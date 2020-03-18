package eu.telecomnancy;

public class Content {
    private StringBuilder sb;

    public Content() {
        sb = new StringBuilder();
    }

    public Content line(String content) {
        sb.append(content);
        sb.append("\n");
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
