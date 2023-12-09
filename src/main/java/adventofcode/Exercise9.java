package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Exercise9 extends AbstractExercise {

    Exercise9() throws IOException {
    }

    @Override
    public String doExercisePart1() {
        int score = 0;
        List<List<Integer>> dataCollected = getDatasetAsLines()
                .stream()
                .map(x -> Arrays.stream(x.split(" ")))
                .map(x -> x.filter(y -> !y.isBlank())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        for (int i = 0; i < dataCollected.size(); i++) {
            List<List<Integer>> values = new ArrayList<>();
            values.add(dataCollected.get(i));
            int j = 0;
            while (values.get(j).stream().anyMatch(x -> x != 0)) {
                values.add(new ArrayList<>());
                for (int k = 0; k < values.get(j).size() - 1; k++) {
                    values.get(j+1).add(values.get(j).get(k + 1) - values.get(j).get(k));
                }
                j++;
            }

            for (int k = 0; k < values.size(); k++) {
                score += values.get(k).get(values.get(k).size() - 1);
            }
        }

        return String.valueOf(score);
    }

    @Override
    public String doExercisePart2() {
        int score = 0;
        List<List<Integer>> dataCollected = getDatasetAsLines()
                .stream()
                .map(x -> Arrays.stream(x.split(" ")))
                .map(x -> x.filter(y -> !y.isBlank())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        for (int i = 0; i < dataCollected.size(); i++) {
            System.out.println("*************************");
            List<List<Integer>> values = new ArrayList<>();
            values.add(dataCollected.get(i));
            int j = 0;
            while (values.get(j).stream().anyMatch(x -> x != 0)) {
                values.add(new ArrayList<>());
                for (int k = 0; k < values.get(j).size() - 1; k++) {
                    values.get(j + 1).add(values.get(j).get(k + 1) - values.get(j).get(k));
                }
                j++;
            }

            for (int k = values.size() - 1; k > 0; k--) {
                int element = values.get(k - 1).get(0) - values.get(k).get(0);
                values.get(k - 1).add(0, element);
            }

                List<Integer> value = values.get(0);
                System.out.println(value);
                score += value.get(0);


        }

        return String.valueOf(score);    }
}