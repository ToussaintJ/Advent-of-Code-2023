package adventofcode;

import java.io.IOException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Exercise1 extends AbstractExercise {
    private static final HashMap<String, Integer> SPELLED_TO_DIGIT_MAP = new HashMap<>();

    Exercise1() throws IOException {
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

    @Override
    public String doExercisePart2() {
        return String.valueOf(this.getDatasetAsLines()
                .stream()
                .map(line -> {
                    List<Integer> result = new ArrayList<>();
                    for (int i = 0; i < line.length(); i++) {
                        String current = line.substring(i);
                        List<String> collect = SPELLED_TO_DIGIT_MAP
                                .keySet()
                                .stream()
                                .filter(current::startsWith)
                                .toList();

                        if (Character.isDigit(current.charAt(0))) {
                            result.add(Character.getNumericValue(current.charAt(0)));
                        } else if (!collect.isEmpty()) {
                            result.add(SPELLED_TO_DIGIT_MAP.get(collect.get(0)));
                        }
                    }

                    return parseInt(String.valueOf(result.get(0)) + result.get(result.size() - 1));
                })
                .reduce(0, Integer::sum));

    }
}