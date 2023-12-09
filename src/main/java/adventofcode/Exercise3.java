package adventofcode;

import java.io.IOException;
import java.util.*;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Exercise3 extends AbstractExercise {
    Exercise3() throws IOException {
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

    @Override
    public String doExercisePart2() {
        List<Integer> result = new ArrayList<>();

        var matrix = createMatrix();


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

                        boolean isSameValue =
                                value1.get(0) == value2.get(0) &&
                                        value1.get(1) == value2.get(1) &&
                                        value1.get(2) == value2.get(2) &&
                                        value1.get(3) == value2.get(3);

                        if (!isSameValue) {
                            result.add(value1.get(2) * value2.get(2));
                        }
                    }
                }
            }
        }
        return String.valueOf(result.stream().reduce(0, Integer::sum));
    }

    private List<List<Character>> createMatrix() {
        return getDatasetAsLines()
                .stream()
                .map(line -> line.chars().mapToObj(character -> (char) character).collect(toList()))
                .collect(toList());
    }
}