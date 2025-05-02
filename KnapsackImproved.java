import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class KnapsackImproved {

        // define inputs and output the maximum value
        public static void main(String args[])
        {
            int[] profit = null;
            int[] weight = null;
            int maximumWeight = 0;

            try {
                //File file = new File("input1.txt");
                //File file = new File("input2.txt");
                //File file = new File("input3.txt");
                //File file = new File("input4.txt");
                File file = new File("input5.txt");

                Scanner scanner = new Scanner(file);

                if (scanner.hasNextLine()) {
                    profit = parseLineToIntArray(scanner.nextLine());
                }

                if (scanner.hasNextLine()) {
                    weight = parseLineToIntArray(scanner.nextLine());
                }

                if (scanner.hasNextLine()) {
                    maximumWeight = Integer.parseInt(scanner.nextLine());
                }

                scanner.close();

                // Now array1 and array2 hold the integers from each line

            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            int n = profit.length;
            System.out.println(knapSack(maximumWeight, weight, profit, n));
        }

    // this function returns the largest of two integers
    static int max(int a, int b) { return (a > b) ? a : b; }

    // this function calculates and returns the maximum value that can be achieved within the maximum weight
    static int knapSack(int maximumWeight, int wt[], int val[], int n)
    {
        // this function returns 0 if at base case (maximum weight is 0 or 0 items to check through)
        if (n == 0 || maximumWeight == 0)
            return 0;

        // checks if the weight of the item will cause the subset to go over the maximum weight (whether the item fits into the optimal solution)
        if (wt[n - 1] > maximumWeight)
            return knapSack(maximumWeight, wt, val, n - 1);

            // return whether item 'n' is included in the maximum value subset or not
        else
            return max(val[n - 1]
                            + knapSack(maximumWeight - wt[n - 1], wt,
                            val, n - 1),
                    knapSack(maximumWeight, wt, val, n - 1));
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