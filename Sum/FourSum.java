package leetcode.Sum;

import java.util.*;

public class FourSum {
    public static void main(String[] args) {
        FourSum test = new FourSum();
        int[] arr = new int[]{1, 0, -1, 0, -2, 2};
        test.fourSum(arr, 0);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> sol = new ArrayList<>();
        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                List<Pair> list = map.get(target - nums[i] - nums[j]);
                if (list != null) {
                    for (Pair p : list) {
                        if (p.right < i) {
                            sol.add(Arrays.asList(new Integer[]{nums[p.left], nums[p.right], nums[i], nums[j]}));
                        }
                    }
                }

                List<Pair> listForThis = map.get(nums[i] + nums[j]);
                if (listForThis == null) {
                    List<Pair> newList = new ArrayList<>();
                    newList.add(new Pair(i, j));
                    map.put(nums[i] + nums[j], newList);
                } else {
                    listForThis.add(new Pair(i, j));
                }

                while (j + 1 < nums.length && nums[j + 1] == nums[j]) {
                    j++;
                }
            }
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
        }
        int a = 1;
        return sol;
    }

    class Pair {
        public int left;
        public int right;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
