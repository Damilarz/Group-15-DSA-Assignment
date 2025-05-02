import java.util.*;

public class maxCutFinalSolution {
    public static void main(String[] args) {
        int [][] graph = { // Define a graph
                {1, 2},       // Vertex 0 is connected to 1 and 2
                {0, 3},       // Vertex 1 is connected to 0 and 3
                {0, 3, 4},    // Vertex 2 is connected to 0, 3, and 4
                {1, 2},       // Vertex 3 is connected to 1 and 2
                {2}           // Vertex 4 is connected to 2
        };

        ArrayList<Integer> nodes = new ArrayList<Integer>();
        for (int i = 0; i < graph.length; i++) {
            nodes.add(i);
        }

        // Declare variables for algorithm result

        Set<Integer>[] maxCutResult = maxCutFinal(graph, nodes);
        Set<Integer> returnedSetA = maxCutResult[0];
        Set<Integer> returnedSetB = maxCutResult[1];

        // Count value of max-cut
        int returnedCuts = CutValue(graph, returnedSetA, returnedSetB);
        System.out.println("Cuts:" + returnedCuts);
        System.out.println("Set A:" + returnedSetA);
        System.out.println("Set B:" + returnedSetB);
    }

    // Max-cut algorithm
    public static Set<Integer>[] maxCutFinal(int [][] graph, ArrayList<Integer> nodes) {
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int maxCuts = -1; // Holds the highest cut value found
        Set<Integer> maxSetA = new HashSet<>();
        Set<Integer> maxSetB = new HashSet<>();

        int n = nodes.size();
        int partitions = (1 << n); // Number of possible partitions of the graph

        // Assign nodes to each subset
        for (int mask = 1; mask < partitions - 1; mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    setA.add(nodes.get(i));
                } else {
                    setB.add(nodes.get(i));
                }
            }

            if (setA.contains(0)) {
                // Count value of the cut
                int Cuts = CutValue(graph, setA, setB);
                // Determine if an improved cut was found
                if (Cuts > maxCuts) {
                    maxCuts = Cuts;
                    maxSetA = new HashSet<>(setA);
                    maxSetB = new HashSet<>(setB);
                }
                System.out.println(setA);
                System.out.println(setB);
                System.out.println(Cuts);

                // Clear variables for next iteration
                setA.clear();
                setB.clear();

            }
        }


        return new Set[]{maxSetA, maxSetB};

    }

    // Method used to find the value of the cut
    public static int CutValue (int[][] graph, Set<Integer> setA, Set<Integer> setB) {
        int Cuts = 0;
        for (int v : setA) {
            for (int nei : graph[v]) {
                if (setB.contains(nei)) {
                    Cuts++;
                }
            }
        }
        return Cuts;
    }
}
