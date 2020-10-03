package project;

public class TextProperties {
    private final int sentencesCount;
    private final int wordsCount;
    private final int charactersCount;
    private final int syllablesCount;
    private int polysyllablesCount;
    private final String text;

    public TextProperties(String text) {
        this.text = text;

        String[] sentences = text.split("[.?!](\\s)*");
        sentencesCount = sentences.length;
        charactersCount = text.replaceAll("\\s+", "").length();

        int wordsCnt = 0, syllablesCnt = 0;

        for (String sentence : sentences) {
            String[] words = sentence.split("\\s+");
            wordsCnt += words.length;
            int syllables = countSyllables(words);
            syllablesCnt += syllables;
        }
        wordsCount = wordsCnt;
        syllablesCount = syllablesCnt;
    }

    private int countSyllables(String[] words) {
        int totalSyllablesCount = 0;
        for (String word : words) {
            word = word.replaceAll("[eE]\\b", "");
            int syllables = 0;
            boolean isPreviousVowel = false;

            for (char ch : word.toCharArray()) {
                if (isVowel(ch) && !isPreviousVowel) {
                    syllables++;
                }
                isPreviousVowel = isVowel(ch);
            }
            totalSyllablesCount += syllables > 0 ? syllables : 1;
            if (syllables > 2) polysyllablesCount++;
        }
        return totalSyllablesCount;
    }

    private static boolean isVowel(char ch) {
        return "aeiouyAEIOUY".indexOf(ch) >= 0;
    }

    public int getSentencesCount() {
        return sentencesCount;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public int getCharactersCount() {
        return charactersCount;
    }

    public int getSyllablesCount() {
        return syllablesCount;
    }

    public int getPolysyllablesCount() {
        return polysyllablesCount;
    }

    @Override
    public String toString() {
        return "The text is:" + "\n" +
                text + "\n" +
                "Words: " + wordsCount + "\n" +
                "Sentences: " + sentencesCount + "\n" +
                "Characters: " + charactersCount + "\n" +
                "Syllables: " + syllablesCount + "\n" +
                "Polysyllables: " + polysyllablesCount + "\n";
    }
}
