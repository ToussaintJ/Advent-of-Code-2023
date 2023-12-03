package adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Exercice3 {
    public static void main(String[] args) {
        String filename = "dataset_exercice3.txt";
        Path path = Paths.get("src/main/resources/" + filename);

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            List<List<Character>> matrix = new ArrayList<>();
            List<Integer> result = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                matrix.add(
                        line.chars()
                                .mapToObj(c -> (char) c)
                                .collect(toList())
                );
            }

            int matrixColumn = matrix.get(0).size();
            int matrixLine = matrix.size();

            for (int i = 0; i < matrixLine; i++) {
                for (int j = 0; j < matrixColumn; j++) {
                    if (matrix.get(i).get(j) == '*') {
                        Integer indexLineFirstNumber = null;
                        Integer indexColumnFirstNumber = null;

                        Integer indexLineSecondNumber = null;
                        Integer indexColumnSecondNumber = null;

                        for (int k = -1; k < 2; k++) {
                            for (int l = -1; l < 2; l++) {
                                try {
                                    if (isDigit(matrix.get(i + k).get(j + l))) {
                                        if (indexLineFirstNumber == null) {
                                            indexLineFirstNumber = i + k;
                                            indexColumnFirstNumber = j + l;
                                        } else {
                                            indexLineSecondNumber = i + k;
                                            indexColumnSecondNumber = j + l;
                                        }
                                    }
                                } catch (IndexOutOfBoundsException e) {
                                    //ignore
                                }
                            }
                        }

                        if (indexLineFirstNumber != null && indexLineSecondNumber != null) {
                            List<Integer> value1 = extractNumberFromMatrix(matrix, indexLineFirstNumber, indexColumnFirstNumber);
                            List<Integer> value2 = extractNumberFromMatrix(matrix, indexLineSecondNumber, indexColumnSecondNumber);

                            boolean isSameValue = Objects.equals(value1.get(0), value2.get(0)) &&
                                    Objects.equals(value1.get(1), value2.get(1)) &&
                                    Objects.equals(value1.get(2), value2.get(2)) &&
                                    Objects.equals(value1.get(3), value2.get(3));

                            if (isSameValue) {
                                // DEBUG
                                System.out.println("WRONG -- Value 1: " + value1.get(2) + " Value 2: " + value2.get(2) + " Ratio : " + value1.get(2) * value2.get(2));
                                System.out.println("WRONG -- STAR IS AT [" + (i + 1) + ":" + (j + 1) + "]");
                            } else {
                                System.out.println("OK -- Value 1: " + value1.get(2) + " Value 2: " + value2.get(2) + " Ratio : " + value1.get(2) * value2.get(2));
                                result.add(value1.get(2) * value2.get(2));
                            }
                        }
                    }
                }
            }
            System.out.println(result.stream().reduce(0, Integer::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> extractNumberFromMatrix(List<List<Character>> matrix, Integer indexLine, Integer indexColumn) {
        int k = 0;
        boolean atStart = false;
        while (!atStart) {
            try {
                if (isDigit(matrix.get(indexLine).get(indexColumn + k))) {
                    k--;
                } else {
                    atStart = true;
                }
            } catch (IndexOutOfBoundsException e) {
                atStart = true;
            }
        }

        int l = 0;
        boolean atEnd = false;
        while (!atEnd) {
            try {
                if (isDigit(matrix.get(indexLine).get(indexColumn + l))) {
                    l++;
                } else {
                    atEnd = true;
                }
            } catch (IndexOutOfBoundsException e) {
                atEnd = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        matrix.get(indexLine).subList(indexColumn + k + 1, indexColumn + l).forEach(sb::append);
        return asList(indexColumn + k + 1, indexColumn + l, Integer.parseInt(sb.toString()), indexLine);
    }
}