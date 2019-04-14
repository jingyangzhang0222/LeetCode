package leetcode.NonGraphDFS;

public class MatchsticksToSquare {
    public static void main(String[] args) {
        System.out.println(new MatchsticksToSquare().makesquare(new int[]{4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2}));
    }

    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) {
            return false;
        }
        return dfs(nums, 4, 0, 0, sum / 4);
    }

    private boolean dfs(int[] nums, int edges, int length, int index, int edgeLength) {
        if (edges == 0) {
            return index == nums.length;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            if (length + nums[index] <= edgeLength) {
                int newLength = length + nums[index] == edgeLength ? 0 : length + nums[index];
                int newEdges = length + nums[index] == edgeLength ? edges - 1 : edges;
                if (dfs(nums, newEdges, newLength, index + 1, edgeLength)) {
                    return true;
                }
            }
            swap(nums, i, index);
        }

        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
