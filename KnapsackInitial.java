import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class KnapsackInitial {
    static int maxValue = 0;

    public static void main(String[] args) {
        // define the items available and maximum weight
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

            // Now array1 and array2 hold the integers from each line

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        knapsack(0, weights, values, maximumWeight, 0, 0);

        System.out.println("Maximum value in knapsack = " + maxValue);
    }

    //generate all available subsets
    static void knapsack(int index, int[] weights, int[] values, int W, int currentWeight, int currentValue) {
        // if the weight of the current inspected subset is within the maximum weight,
        // check if the value of the subset is greater than the current largest value subset
        if (currentWeight <= W && currentValue > maxValue) {
            maxValue = currentValue;
        }

        // return if all the items have been checked
        if (index == weights.length)
            return;

        // include the current item
        knapsack(index + 1, weights, values, W,
                currentWeight + weights[index],
                currentValue + values[index]);

        // exclude the current item
        knapsack(index + 1, weights, values, W,
                currentWeight,
                currentValue);
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