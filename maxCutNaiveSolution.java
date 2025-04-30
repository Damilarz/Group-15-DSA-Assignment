import java.util.*;

public class maxCutNaiveSolution {
    public static void main(String[] args) {
        int [][] nodes = { // Define a graph
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
        // Declare variables for algorithm result
        Integer returnedCuts = 0;
        Set<Integer>[] maxCutResult = maxCutNaive(nodes);
        Set<Integer> returnedSetA = maxCutResult[0];
        Set<Integer> returnedSetB = maxCutResult[1];

        // Count value of max-cut
        for (int v : returnedSetA) {
            for (int nei : nodes[v]) {
                if (returnedSetB.contains(nei)) {
                    returnedCuts++;
                }
            }
        }
        System.out.println("Cuts:" + returnedCuts);
        System.out.println("Set A:" + returnedSetA);
        System.out.println("Set B:" + returnedSetB);
    }

    // Max-cut algorithm
    public static Set<Integer>[] maxCutNaive(int [][] nodes) {
        int edge = nodes.length;
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int Cuts = 0; // Holds the current value of cuts
        int maxCuts = -1; // Holds the highest cut value found
        Set<Integer> maxSetA = new HashSet<>();
        Set<Integer> maxSetB = new HashSet<>();
        Random random = new Random();

        // Assign nodes to each subset
        for (int n = 0; n < 50; n++) {
            for (int i = 0; i < edge; i++) {
                if ((int)((Math.random())*100) % 2 == 0) {
                    setA.add(i);
                } else {
                    setB.add(i);
                }
            }
            // Count value of the cut
            for (int v : setA) {
                for (int nei : nodes[v]) {
                    if (setB.contains(nei)) {
                        Cuts++;
                    }
                }
            }
            // Determine if an improved cut was found
            if (Cuts > maxCuts) {
                maxCuts = Cuts;
                maxSetA = new HashSet<>(setA);
                maxSetB = new HashSet<>(setB);
            }
            System.out.println(maxSetA);
            System.out.println(maxSetB);
            System.out.println(maxCuts);

            // Clear variables for next iteration
            setA.clear();
            setB.clear();
            Cuts = 0;
        }
        return new Set[]{maxSetA, maxSetB};

    }

}