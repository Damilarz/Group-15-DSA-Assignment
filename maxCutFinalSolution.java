import java.util.*;

public class maxCutFinalSolution {
    public static void main(String[] args) {
        int [][] graph = { // Define a graph
                /*{1, 3, 5},
                {0, 2, 4},
                {1, 4, 5},
                {0},
                {1, 2},
                {0, 2}*/

                {1, 2, 3},       // 0
                {0, 4, 5},       // 1
                {0, 6},          // 2
                {0, 7, 8},       // 3
                {1, 9, 10},      // 4
                {1, 11},         // 5
                {2, 12, 13},     // 6
                {3, 14},         // 7
                {3, 15, 16},     // 8
                {4, 17},         // 9
                {4, 18, 19},     // 10
                {5, 20},         // 11
                {6, 21, 22},     // 12
                {6, 23},         // 13
                {7, 24, 25},     // 14
                {8, 26},         // 15
                {8, 27, 28},     // 16
                {9, 29},         // 17
                {10, 30},        // 18
                {10, 31},        // 19
                {11, 32, 33},    // 20
                {12, 34},        // 21
                {12, 35},        // 22
                {13, 36},        // 23
                {14, 37},        // 24
                {14, 38},        // 25
                {15, 39},        // 26
                {16, 40},        // 27
                {16, 41},        // 28
                {17, 42},        // 29
                {18, 43},        // 30
                {19, 44},        // 31
                {20, 45},        // 32
                {20, 46},        // 33
                {21, 47},        // 34
                {22, 48},        // 35
                {23, 49},        // 36
                {24},            // 37
                {25},            // 38
                {26},            // 39
                {27},            // 40
                {28},            // 41
                {29},            // 42
                {30},            // 43
                {31},            // 44
                {32},            // 45
                {33},            // 46
                {34},            // 47
                {35},            // 48
                {36}             // 49
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
