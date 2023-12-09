package adventofcode;

import java.io.IOException;
import java.util.List;

public class ExerciseRunner {
    public static void main(String[] args) {
        List.of(8)
                .forEach(exerciceNumber -> {
                    try {
                        AbstractExercise exercise = switch (exerciceNumber) {
                            case 1 -> new Exercise1();
                            case 2 -> new Exercise2();
                            case 3 -> new Exercise3();
                            case 6 -> new Exercise6();
                            case 8 -> new Exercise8();
                            case 9 -> new Exercise9();
                            default -> throw new IllegalStateException("Unexpected value: " + exerciceNumber);
                        };
                        System.out.println("******************* " + exercise.getClass().getSimpleName() + " ********************");
                        System.out.println("Part 1: " + exercise.doExercisePart1());
                        System.out.println("Part 2: " + exercise.doExercisePart2());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
