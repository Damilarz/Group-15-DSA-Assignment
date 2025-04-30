// Hybrid TSP Algorithm in Java using Nearest Neighbor + Held-Karp

import java.util.*;

public class HybridTSP {
    static final int INF = Integer.MAX_VALUE / 2;
    static int n; // number of cities
    static int[][] dist; // distance matrix
    static int start = 0; // start city (index 0)

    // Run Nearest Neighbor to get upper bound tour length D_NN
    static int[] nearestNeighbor() {
        boolean[] visited = new boolean[n];
        int[] tour = new int[n + 1];
        int current = start;
        visited[current] = true;
        tour[0] = current;
        int totalCost = 0;

        for (int i = 1; i < n; i++) {
            int next = -1;
            int minDist = INF;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && dist[current][j] < minDist) {
                    minDist = dist[current][j];
                    next = j;
                }
            }
            tour[i] = next;
            visited[next] = true;
            totalCost += dist[current][next];
            current = next;
        }
        tour[n] = start; // return to starting city
        totalCost += dist[current][start];

        System.out.println("D_NN (Nearest Neighbor Cost): " + totalCost);
        return tour;
    }

    // Held-Karp Algorithm with pruning using D_NN
    static int heldKarp(int upperBound) {
        int size = 1 << n; // 2^n subsets
        int[][] dp = new int[size][n];

        for (int[] row : dp)
            Arrays.fill(row, INF);

        dp[1 << start][start] = 0; // base case

        for (int mask = 0; mask < size; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] == INF) continue;
                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0 && dist[u][v] < INF) {
                        int nextMask = mask | (1 << v);
                        int newCost = dp[mask][u] + dist[u][v];
                        // Prune paths exceeding upper bound
                        if (newCost < dp[nextMask][v] && newCost < upperBound) {
                            dp[nextMask][v] = newCost;
                        }
                    }
                }
            }
        }

        // Complete tour by returning to start
        int best = INF;
        for (int i = 0; i < n; i++) {
            if (i == start || dp[size - 1][i] == INF) continue;
            int tourCost = dp[size - 1][i] + dist[i][start];
            if (tourCost < best) best = tourCost;
        }

        return best;
    }

    public static void main(String[] args) {
        // Example distance matrix (symmetric)
        dist = new int[][]{
            { 0, 10, 15, 20 },
            { 10, 0, 35, 25 },
            { 15, 35, 0, 30 },
            { 20, 25, 30, 0 }
        };

        n = dist.length;

        // Phase 1: Nearest Neighbor for D_NN
        int[] nnTour = nearestNeighbor();

        int D_NN = 0;
        for (int i = 0; i < n; i++) {
            D_NN += dist[nnTour[i]][nnTour[i + 1]];
        }

        // Phase 2: Held-Karp using D_NN as upper bound
        int D_min = heldKarp(D_NN);

        System.out.println("D_min (Optimal Tour Cost): " + D_min);
    }
}
