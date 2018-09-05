package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> sol = new ArrayList<>();
        dfs(nums, 0, sol, new ArrayList<Integer>());
        return sol;
    }
    private void dfs(int[] nums, int index, List<List<Integer>> sol, List<Integer> subsol) {
        sol.add(new ArrayList(subsol));
        if (index == nums.length) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            subsol.add(nums[i]);
            dfs(nums, i + 1, sol, subsol);
            subsol.remove(subsol.size() - 1);

            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                i++;
            }
        }
    }
}
