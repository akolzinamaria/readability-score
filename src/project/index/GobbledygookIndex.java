package project.index;

import project.TextProperties;

public class GobbledygookIndex extends ReadabilityIndex {

    private static final String fullName = "Simple Measure of Gobbledygook";

    public GobbledygookIndex() {
        super(fullName);
    }

    @Override
    public double calculateScore(TextProperties properties) {
        return 1.043 * Math.sqrt((double) properties.getPolysyllablesCount() * 30 / properties.getSentencesCount()) + 3.1291;
    }
}
