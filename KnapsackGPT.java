import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KnapsackGPT {
    public static void main(String[] args) throws FileNotFoundException {
        int[] values = null;
        int[] weights = null;
        int maximumWeight = 0;

        try {
            //File file = new File("input1.txt");
            //File file = new File("input2.txt");
            //File file = new File("input3.txt");
            //File file = new File("input4.txt");
            File file = new File("input5.txt");

            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) {
                values = parseLineToIntArray(scanner.nextLine());
            }

            if (scanner.hasNextLine()) {
                weights = parseLineToIntArray(scanner.nextLine());
            }

            if (scanner.hasNextLine()) {
                maximumWeight = Integer.parseInt(scanner.nextLine());
            }

            scanner.close();

            // Now array1 and array2 hold the integers from the text file

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        int maxValue = knapsack(weights, values, maximumWeight);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or capacity is 0
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The last cell contains the answer
        return dp[n][capacity];
    }

    private static int[] parseLineToIntArray(String line) {
        String[] tokens = line.trim().split("\\s+");
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            result[i] = Integer.parseInt(tokens[i]);
        }
        return result;
    }
}