import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class maxCutNaiveSolution {
    public static void main(String[] args) {
        int [][] nodes = {
                {1, 2},       // Vertex 0 is connected to 1 and 2
                {0, 3},       // Vertex 1 is connected to 0 and 3
                {0, 3, 4},    // Vertex 2 is connected to 0, 3, and 4
                {1, 2},       // Vertex 3 is connected to 1 and 2
                {2}           // Vertex 4 is connected to 2
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