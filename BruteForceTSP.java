import java.util.*;

public class BruteForceTSP {
    // Distance matrix: distanceMatrix[i][j] represents the distance from city i to city j
    private static int[][] distanceMatrix;
    private static int numCities;

    // To store the best route (shortest distance)
    private static List<Integer> bestRoute;

    // Track the minimum total distance found
    private static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Example distance matrix for 4 cities (symmetric TSP)
        distanceMatrix = new int[][] {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        numCities = distanceMatrix.length;

        // Create a list of cities to visit (excluding the starting city, which is city 0)
        List<Integer> cities = new ArrayList<>();
        for (int i = 1; i < numCities; i++) {
            cities.add(i);
        }

        // Generate all permutations of the cities list and evaluate each route
        permute(cities, 0);

        // Output the result with visual breakdown
        System.out.println("Minimum tour distance: " + minDistance + "\n");

        // Print the best route
        System.out.print("Optimal route: ");
        for (int i = 0; i < bestRoute.size(); i++) {
            System.out.print("City " + bestRoute.get(i));
            if (i < bestRoute.size() - 1) System.out.print(" → ");
        }

        // Show distances between cities step by step
        System.out.println("\n\nDistance breakdown:");
        int total = 0;
        for (int i = 0; i < bestRoute.size() - 1; i++) {
            int from = bestRoute.get(i);
            int to = bestRoute.get(i + 1);
            int d = distanceMatrix[from][to];
            total += d;
            System.out.println("From City " + from + " to City " + to + " = " + d + " units");
        }

        System.out.println("\nVerified total distance: " + total + " units");
    }

    // Generate permutations of the cities list using backtracking
    private static void permute(List<Integer> cities, int start) {
        if (start == cities.size()) {
            // A complete route is generated; now evaluate it
            evaluateRoute(cities);
            return;
        }

        for (int i = start; i < cities.size(); i++) {
            Collections.swap(cities, start, i);         // Swap to fix one city
            permute(cities, start + 1);                 // Recurse for the rest
            Collections.swap(cities, start, i);         // Backtrack
        }
    }

    // Calculate the total distance of a given route and update if it's the best so far
    private static void evaluateRoute(List<Integer> route) {
        int totalDistance = 0;
        int currentCity = 0; // Always start from City 0

        // Add distance from starting city to the first city in the permutation
        totalDistance += distanceMatrix[currentCity][route.get(0)];

        // Add distances for the full path through the route
        for (int i = 0; i < route.size() - 1; i++) {
            totalDistance += distanceMatrix[route.get(i)][route.get(i + 1)];
        }

        // Add distance from the last city back to the starting city
        totalDistance += distanceMatrix[route.get(route.size() - 1)][currentCity];

        // If this route is the best so far, save it
        if (totalDistance < minDistance) {
            minDistance = totalDistance;
            bestRoute = new ArrayList<>();
            bestRoute.add(0);                // Starting city
            bestRoute.addAll(route);         // Cities in the current route
            bestRoute.add(0);                // Return to starting city
        }
    }
}
