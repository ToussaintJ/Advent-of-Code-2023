package adventofcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise8 extends AbstractExercise {

    private final List<Node> nodeList;
    private final List<String> datasetAsLines;

    Exercise8() throws IOException {
        datasetAsLines = getDatasetAsLines();
        nodeList = datasetAsLines
                .subList(2, datasetAsLines.size())
                .stream()
                .map(line -> {
                    List<String> possibilities = Arrays.stream(
                                    line.replaceAll("=", "")
                                            .replaceAll(",", "")
                                            .replaceAll("[()]", "")
                                            .split(" ")
                            )
                            .filter(currentLine -> !currentLine.isBlank())
                            .toList();
                    return new Node(possibilities.get(0), possibilities.get(1), possibilities.get(2));
                })
                .toList();
    }

    @Override
    public String doExercisePart1() {
        List<String> commandLine = Arrays.stream(datasetAsLines.get(0).split("")).toList();

        var currentNode = nodeList.get(0);

        int i = 0;
        while (!currentNode.getId().equals("ZZZ")) {
            Node finalCurrentNode = currentNode;
            int finalI = i;
            currentNode = nodeList.stream()
                    .filter(node -> {
                                String nextNodeID = finalCurrentNode.getPaths().get(commandLine.get(finalI % commandLine.size()));
                                return nextNodeID.equals(node.getId());
                            }
                    )
                    .findFirst()
                    .get();
            i++;
        }

        return String.valueOf(i);
    }


    @Override
    public String doExercisePart2() {
        List<String> commandLine = Arrays.stream(datasetAsLines.get(0).split("")).toList();

        var currentNode = nodeList.stream().filter(node -> node.getId().endsWith("A")).collect(Collectors.toList());


        var score = new ArrayList<Integer>();

        for (int j = 0; j < currentNode.size(); j++) {
            int i = 0;
            while (!currentNode.get(j).getId().endsWith("Z")) {
                Node finalCurrentNode = currentNode.get(j);
                int finalI = i;
                currentNode.set(j, nodeList.stream()
                        .filter(node -> {
                                    String nextNodeID = finalCurrentNode.getPaths().get(commandLine.get(finalI % commandLine.size()));
                                    return nextNodeID.equals(node.getId());
                                }
                        )
                        .findFirst()
                        .get());
                i++;
            }
            score.add(i);
            System.out.println(currentNode.get(j).getId() + " has arrived with " + score.get(j));
        }


        return String.valueOf(calculPPCM(score));
    }

    public long calculPPCM(List<Integer> scores) {
        long x, y, z;

        x = scores.get(0);
        z = 1;
        for (int i = 1; i < scores.size(); i++) {
            y = (long) scores.get(i);
            z = ppcm(x, y);
            x = z;
        }
        return z;
    }

    public long ppcm(long nb1, long nb2) {
        long produit, reste, ppcm;

        produit = nb1 * nb2;
        reste = nb1 % nb2;
        while (reste != 0) {
            nb1 = nb2;
            nb2 = reste;
            reste = nb1 % nb2;
        }
        ppcm = produit / nb2;
        return ppcm;
    }

    class Node {
        private String id;
        private HashMap<String, String> paths = new HashMap<>();

        public Node(String id, String left, String right) {
            this.id = id;
            paths.put("L", left);
            paths.put("R", right);
        }

        public String getId() {
            return id;
        }

        public HashMap<String, String> getPaths() {
            return paths;
        }
    }
}
