public class knapsackNaive {

    static int maxValue = 0;

    // generate all the available subsets
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

    public static void main(String[] args) {
        // define the items available and maximum weight
        int[] values = {14, 87, 34};
        int[] weights = {9, 23, 17};
        int W = 50;

        knapsack(0, weights, values, W, 0, 0);

        System.out.println("Maximum value in knapsack = " + maxValue);
    }
}