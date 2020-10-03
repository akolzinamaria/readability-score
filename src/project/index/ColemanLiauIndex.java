package project.index;

import project.TextProperties;

public class ColemanLiauIndex extends ReadabilityIndex {

    private static final String fullName = "Coleman–Liau index";

    public ColemanLiauIndex() {
        super(fullName);
    }

    @Override
    public double calculateScore(TextProperties properties) {
        double avgCharacters = (double) properties.getCharactersCount() * 100 / properties.getWordsCount();
        double avgSentences = (double) properties.getSentencesCount() * 100 / properties.getWordsCount();
        return 0.0588 * avgCharacters - 0.296 * avgSentences - 15.8;
    }
}
