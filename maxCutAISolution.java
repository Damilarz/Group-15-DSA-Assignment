import java.util.*;

public class maxCutAISolution {
    public static void main(String[] args) {
        // Example graph represented as an adjacency list
        // Each index represents a node, and the array contains its neighbors
        int[][] graph = {
                {1, 2},    // Node 0 connected to Nodes 1 and 2
                {0, 2, 3}, // Node 1 connected to Nodes 0, 2, and 3
                {0, 1, 3}, // Node 2 connected to Nodes 0, 1, and 3
                {1, 2}     // Node 3 connected to Nodes 1 and 2
        };

        // Call the greedyMaxCut function to compute the partition
        Set<Integer>[] maxCut = greedyMaxCut(graph);

        // Output the two sets representing the cut
        System.out.println("Set A: " + maxCut[0]);
        System.out.println("Set B: " + maxCut[1]);
    }

    public static Set<Integer>[] greedyMaxCut(int[][] graph) {
        int n = graph.length; // Number of vertices in the graph
        Set<Integer> setA = new HashSet<>(); // First partition set
        Set<Integer> setB = new HashSet<>(); // Second partition set

        // Step 1: Randomly assign nodes to one of the two sets
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            if (rand.nextBoolean()) {
                setA.add(i);
            } else {
                setB.add(i);
            }
        }

        boolean improved = true; // Flag to check if improvement is possible

        // Step 2: Iteratively improve the cut by moving nodes
        while (improved) {
            improved = false; // Assume no improvement initially

            for (int v = 0; v < n; v++) {
                int edgesInA = 0, edgesInB = 0;

                // Count how many of the node's neighbors are in each set
                for (int neighbor : graph[v]) {
                    if (setA.contains(neighbor)) edgesInA++;
                    if (setB.contains(neighbor)) edgesInB++;
                }

                // Step 3: Move the vertex if it improves the cut
                if (setA.contains(v) && edgesInA > edgesInB) {
                    // Move v from Set A to Set B
                    setA.remove(v);
                    setB.add(v);
                    improved = true;
                } else if (setB.contains(v) && edgesInB > edgesInA) {
                    // Move v from Set B to Set A
                    setB.remove(v);
                    setA.add(v);
                    improved = true;
                }
            }
        }

        // Step 4: Return the two sets as the final partition
        return new Set[]{setA, setB};
    }
}
