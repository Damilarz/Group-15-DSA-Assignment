import javax.print.DocFlavor;
import java.util.*;

public class maxCutNaiveSolution {
    public static void main(String[] args) {
        int [][] nodes = {
                {1, 3, 5},
                {0, 2, 4},
                {1, 4, 5},
                {0},
                {1, 2},
                {0, 2}
        };
        Integer returnedCuts = 0;
        Set<Integer>[] maxCutResult = maxCutNaive(nodes);
        Set<Integer> returnedSetA = maxCutResult[0];
        Set<Integer> returnedSetB = maxCutResult[1];

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

    public static Set<Integer>[] maxCutNaive(int [][] nodes) {
        int edge = nodes.length;
        Set<Integer> setA = new HashSet<>();
        Set<Integer> setB = new HashSet<>();
        int Cuts = 0;
        int maxCuts = -1;
        Set<Integer> maxSetA = new HashSet<>();
        Set<Integer> maxSetB = new HashSet<>();
        Random random = new Random();


        for (int n = 0; n < 50; n++) {
            for (int i = 0; i < edge; i++) {
                if ((int)((Math.random())*100) % 2 == 0) {
                    setA.add(i);
                } else {
                    setB.add(i);
                }
            }
            for (int v : setA) {
                for (int nei : nodes[v]) {
                    if (setB.contains(nei)) {
                        Cuts++;
                    }
                }
            }
            if (Cuts > maxCuts) {
                maxCuts = Cuts;
                maxSetA = new HashSet<>(setA);
                maxSetB = new HashSet<>(setB);
            }
            System.out.println(maxSetA);
            System.out.println(maxSetB);
            System.out.println(maxCuts);
            setA.clear();
            setB.clear();
            Cuts = 0;
        }
        return new Set[]{maxSetA, maxSetB};

    }

}
