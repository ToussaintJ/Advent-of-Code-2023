package adventofcode;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Exercice1 {
    private static final List<String> LIST = new ArrayList<>();
    private static final HashMap<String, Integer> SPELLED_TO_DIGIT_MAP = new HashMap<>();

    static {
        LIST.add("one");
        LIST.add("two");
        LIST.add("three");
        LIST.add("four");
        LIST.add("five");
        LIST.add("six");
        LIST.add("seven");
        LIST.add("eight");
        LIST.add("nine");

        SPELLED_TO_DIGIT_MAP.put("one", 1);
        SPELLED_TO_DIGIT_MAP.put("two", 2);
        SPELLED_TO_DIGIT_MAP.put("three", 3);
        SPELLED_TO_DIGIT_MAP.put("four", 4);
        SPELLED_TO_DIGIT_MAP.put("five", 5);
        SPELLED_TO_DIGIT_MAP.put("six", 6);
        SPELLED_TO_DIGIT_MAP.put("seven", 7);
        SPELLED_TO_DIGIT_MAP.put("eight", 8);
        SPELLED_TO_DIGIT_MAP.put("nine", 9);
    }

    public static void main(String[] args) {
        String filename = "dataset_exercice1.txt";
        Path path = Paths.get("src/main/resources/" + filename);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int score = 0;

            while ((line = br.readLine()) != null) {
                List<Integer> result = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    String current = line.substring(i);
                    List<String> collect = LIST
                            .stream()
                            .filter(current::startsWith)
                            .toList();

                    if (Character.isDigit(current.charAt(0)) || !collect.isEmpty()) {
                        if (Character.isDigit(current.charAt(0))) {
                            result.add(Character.getNumericValue(current.charAt(0)));
                        } else if (!collect.isEmpty()) {
                            result.add(SPELLED_TO_DIGIT_MAP.get(collect.get(0)));
                        }
                    }
                }

                int realResult = Integer.parseInt(String.valueOf(result.get(0)) + result.get(result.size() - 1));
                score += realResult;
            }
            System.out.println("score: " + score);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}