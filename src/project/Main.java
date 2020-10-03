package project;

import project.index.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static final Map<String, ReadabilityIndex[]> indexes;
    private static final AutomatedReadabilityIndex automatedReadabilityIndex = new AutomatedReadabilityIndex();
    private static final FleschKincaidIndex fleschKincaidIndex = new FleschKincaidIndex();
    private static final GobbledygookIndex gobbledygookIndex = new GobbledygookIndex();
    private static final ColemanLiauIndex colemanLiauIndex = new ColemanLiauIndex();

    static {
        indexes = new HashMap<>();
        indexes.put("ARI", new ReadabilityIndex[]{automatedReadabilityIndex});
        indexes.put("FK", new ReadabilityIndex[]{fleschKincaidIndex});
        indexes.put("SMOG", new ReadabilityIndex[]{gobbledygookIndex});
        indexes.put("CL", new ReadabilityIndex[]{colemanLiauIndex});
        indexes.put("all", new ReadabilityIndex[]{automatedReadabilityIndex, fleschKincaidIndex, gobbledygookIndex, colemanLiauIndex});
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Required 1 argument: path to file");
            return;
        }

        String filePath = args[0];
        String inputText;

        try {
            inputText = getStringFromFile(filePath);
        } catch (IOException e) {
            System.out.println("Error while reading file");
            return;
        }

        TextProperties textProperties = new TextProperties(inputText);
        System.out.println(textProperties);

        String indexName = processInput();
        process(indexes.get(indexName), textProperties);

    }

    private static String getStringFromFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    private static String processInput() {
        Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
            input = sc.nextLine();
        } while (!indexes.containsKey(input));

        System.out.println();
        return input;
    }

    private static void process(ReadabilityIndex[] readabilityIndices, TextProperties textProperties) {
        int[] ages = new int[readabilityIndices.length];

        for (int i = 0; i < readabilityIndices.length; i++) {
            ReadabilityIndex readabilityIndex = readabilityIndices[i];
            double score = readabilityIndex.calculateScore(textProperties);
            int age = readabilityIndex.getAge(score);
            ages[i] = age;
            System.out.println(readabilityIndex.toString(score, age));
        }

        OptionalDouble avgAge = IntStream.of(ages).average();
        if (avgAge.isPresent()) {
            System.out.println();
            System.out.println("This text should be understood in average by " + avgAge.getAsDouble() + " year olds.");
        }
    }
}
