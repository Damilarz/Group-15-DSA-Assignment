public class KnapsackNaive{
    public static int largest(int a, int b) { return (a > b) ? a : b; } //this function will return whichever variable is larger (a or b)

    static int knapSack(int maxWeight, int weight[], int value[], int n){
        if (n == 0 || maxWeight == 0) return 0; //this will return the value 0 if the problem is at it's base case (maximum weight of 0 or no items to choose from)

        if (weight[n-1] > maxWeight) return knapSack(maxWeight, weight, value, n-1);

        else return largest(value[n - 1] + knapSack(maxWeight - weight[n - 1],
                            weight, value, n - 1),
                            knapSack(maxWeight, weight, value, n - 1));
    }

    public static void main(String args[]){
        int value[] = new int[] {4, 7, 5}; //this is the values of the items entering into the knapsack function
        int weight[] = new int[] {14, 34, 17};
        int maxWeight = 140;
        int n = value.length;
        System.out.println(knapSack(maxWeight, weight, value, n));
    }
}
