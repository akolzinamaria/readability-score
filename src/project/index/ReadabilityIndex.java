package project.index;

import project.TextProperties;

abstract public class ReadabilityIndex {

    private final String fullName;

    ReadabilityIndex(String fullName) {
        this.fullName = fullName;
    }

    abstract public double calculateScore(TextProperties properties);

    public int getAge(double score) {
        int iScore = (int) Math.round(score);
        return switch (iScore) {
            case 1 -> 6;
            case 2 -> 7;
            case 3 -> 9;
            case 4 -> 10;
            case 5 -> 11;
            case 6 -> 12;
            case 7 -> 13;
            case 8 -> 14;
            case 9 -> 15;
            case 10 -> 16;
            case 11 -> 17;
            case 12 -> 18;
            default -> 24;
        };
    }

    public String toString(double score, int age) {
        return this.fullName + ": " + score + " (about " + age + " year olds).";
    }
}
