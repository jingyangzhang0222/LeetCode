package leetcode.Greedy;

public class GasStation {
    public static void main(String[] args) {
        System.out.println(new GasStation().canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 1 2 3 4 5 *1
        //  3 4 5 1 2
        // -2  -2  -2  3  3

        //-1 -2 -7 3 8 -14 -2 100 6 -10
        int[] tmp = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            tmp[i] = gas[i] - cost[i];
        }

        int res = -1;
        int borrow = 0;
        int gasoline = 0;
        for (int i = 0; i < gas.length; i++) {
            borrow += tmp[i];
            gasoline += tmp[i];
            if (gasoline >= 0) {
                res = res == -1 ? i : res;
            } else {
                res = -1;
                gasoline = 0;
            }
        }

        return gasoline + borrow >= 0 ? res : -1;
    }
}
