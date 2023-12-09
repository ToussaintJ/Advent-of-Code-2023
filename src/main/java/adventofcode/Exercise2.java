package adventofcode;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class Exercise2 extends AbstractExercise {

    Exercise2() throws IOException {
    }

    @Override
    public String doExercisePart2() {
        return String.valueOf(getDatasetAsLines().stream().map(line -> {
            Map<String, Integer> gameSet = new HashMap<>();
            String gameOutputs = line.split(":")[1];
            List<String[]> gameSubSets = Arrays.stream(gameOutputs.split(";"))
                    .map(gameSubSetAsString -> gameSubSetAsString.split(","))
                    .toList();
            gameSubSets.forEach(subSet -> {
                Arrays.stream(subSet)
                        .forEach(colorWithNumber -> {
                            String[] splitColorFromNumber = colorWithNumber.split(" ");
                            if (gameSet.containsKey(splitColorFromNumber[2].trim())) {
                                gameSet.put(
                                        splitColorFromNumber[2].trim(),
                                        max(gameSet.get(splitColorFromNumber[2].trim()), parseInt(splitColorFromNumber[1].trim()))
                                );
                            } else {
                                gameSet.put(splitColorFromNumber[2].trim(), parseInt(splitColorFromNumber[1].trim()));
                            }
                        });
            });
            return gameSet.get("red") * gameSet.get("blue") * gameSet.get("green");
        }).reduce(0, Integer::sum));
    }
}