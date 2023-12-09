package adventofcode;

import java.io.IOException;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        int exerciceNumber = 3;

        AbstractExercise exercice = switch (exerciceNumber) {
            case 1 -> new Exercise1();
//            case 2 -> new Exercice2();
            case 3 -> new Exercise3();
//            case 4 -> new Exercice4();
//            case 5 -> new Exercice5();
            case 6 -> new Exercise6();
            default -> throw new IllegalStateException();
        };

        System.out.println(exercice.doExercisePart2());

    }
}
