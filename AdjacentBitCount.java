package leetcode;

public class AdjacentBitCount {

    public int getAdjBCStringCountBruteForce(int n, int k) {
        int[] sol = new int[1];
        helper(n, k, 1, 0, 233, sol);
        return sol[0];
    }
    private void helper(int n, int k, int index, int oneCount, int prev, int[] count) {
        //base case
        if (index > n) {
            if (oneCount == k) count[0]++;
            return;
        }

        //0 at this index
        if (index == 1) {
            helper(n, k, index + 1, oneCount, 0, count);
            helper(n, k, index + 1, oneCount, 1, count);
        } else {
            if (prev == 1) {
                //take 0
                helper(n, k, index + 1, oneCount, 0, count);
                //take 1
                helper(n, k ,index + 1, oneCount + 1, 1, count);
            } else {//prev = 0
                //take 0
                helper(n, k, index + 1, oneCount, 0, count);
                helper(n, k, index + 1, oneCount, 1, count);
            }
        }
    }
}