package project.index;

import project.TextProperties;

public class FleschKincaidIndex extends ReadabilityIndex {

    private static final String fullName = "Flesch–Kincaid readability tests";

    public FleschKincaidIndex() {
        super(fullName);
    }

    @Override
    public double calculateScore(TextProperties properties) {
        return 0.39 * properties.getWordsCount() / properties.getSentencesCount() +
                11.8 * properties.getSyllablesCount() / properties.getWordsCount() - 15.59;
    }
}
