package project.index;

import project.TextProperties;

public class AutomatedReadabilityIndex extends ReadabilityIndex {

    private static final String fullName = "Automated Readability Index";

    public AutomatedReadabilityIndex() {
        super(fullName);
    }

    @Override
    public double calculateScore(TextProperties properties) {
        return 4.71 * properties.getCharactersCount() / properties.getWordsCount() +
                0.5 * properties.getWordsCount() / properties.getSentencesCount() - 21.43;
    }
}
