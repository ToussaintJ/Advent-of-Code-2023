package adventofcode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;

public class Exercice2 {

    public static void main(String[] args) {
        String filename = "dataset_exercice2.txt";
        Path path = Paths.get("src/main/resources/" + filename);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<Integer> score = new ArrayList<>();

            while ((line = br.readLine()) != null) {
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

                System.out.println(line);
                score.add(gameSet.get("red") * gameSet.get("blue") * gameSet.get("green"));
            }

            System.out.println("Score: " + score.stream().reduce(0, Integer::sum));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}