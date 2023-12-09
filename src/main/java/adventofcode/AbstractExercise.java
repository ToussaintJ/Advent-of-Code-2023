package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractExercise {
    private final List<String> datasetAsLines;

    AbstractExercise() throws IOException {
        this.datasetAsLines = this.readFiles();
    }

    public abstract String doExercisePart1();
    public abstract String doExercisePart2();

    public List<String> readFiles() throws IOException {
        Path path = Paths.get("src/main/resources/dataset_" + this.getClass().getSimpleName().toLowerCase() + ".txt");
        return Files.readAllLines(path);
    }

    public List<String> getDatasetAsLines() {
        return datasetAsLines;
    }
}
