public class KnapsackImproved {
    class Knapsack {

        // this function returns the largest of two integers
        static int max(int a, int b) { return (a > b) ? a : b; }

        // this function calculates and returns the maximum value that can be achieved within the maximum weight
        static int knapSack(int W, int wt[], int val[], int n)
        {
            // this function returns 0 if at base case (maximum weight is 0 or 0 items to check through)
            if (n == 0 || W == 0)
                return 0;

            // checks if the weight of the item will cause the subset to go over the maximum weight
            if (wt[n - 1] > W)
                return knapSack(W, wt, val, n - 1);

                // return whether item 'n' is included in the maximum value subset or not
            else
                return max(val[n - 1]
                                + knapSack(W - wt[n - 1], wt,
                                val, n - 1),
                        knapSack(W, wt, val, n - 1));
        }

        // define inputs and output the maximum value
        public static void main(String args[])
        {
            int profit[] = new int[] { 60, 100, 120 };
            int weight[] = new int[] { 10, 20, 30 };
            int W = 50;
            int n = profit.length;
            System.out.println(knapSack(W, weight, profit, n));
        }
    } }