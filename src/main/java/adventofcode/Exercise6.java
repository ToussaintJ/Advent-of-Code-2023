package adventofcode;

import java.io.IOException;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

public class Exercise6 extends AbstractExercise {
    Exercise6() throws IOException {
    }

    private static long extractValue(String line) {
        return parseLong(
                stream(line.split(":")[1].split(" "))
                        .filter(value -> !value.isBlank())
                        .map(String::trim)
                        .collect(joining()));
    }

    @Override
    public String doExercisePart2() {
        long times = extractValue(this.getDatasetAsLines().get(0));
        long distances = extractValue(this.getDatasetAsLines().get(1));

        int intScore = 0;
        for (int k = 0; k < times; k++) {
            if (distances < (times - k) * k) {
                intScore++;
            }
        }
        return String.valueOf(intScore);
    }
}