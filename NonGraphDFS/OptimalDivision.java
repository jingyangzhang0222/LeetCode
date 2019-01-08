package leetcode.NonGraphDFS;

public class OptimalDivision {
    public static void main(String[] args) {
        System.out.println(new OptimalDivision().optimalDivision(new int[]{5, 88, 35}));
    }
    public String optimalDivision(int[] nums) {
        return helper(nums, 0, nums.length - 1).maxExp;
    }

    private Result helper(int[] nums, int left, int right) {
        if (left == right) {
            return new Result(String.valueOf(nums[left]), (double)nums[left],
                    String.valueOf(nums[left]), (double)nums[left]);
        }
        if (left == right - 1) {
            String exp = nums[left] + "/" + nums[right];
            double val = (double)nums[left] / (double)nums[right];
            return new Result(exp, val, exp, val);
        }

        String maxExp = null;
        double maxVal = (double)2;
        String minExp = null;
        double minVal = (double)1000;

        // 10 / 10 / 10
        //    0    1    2
        for (int i = left; i <= right - 1; i++) {
            Result leftRes = helper(nums, left, i);
            Result rightRes = helper(nums, i + 1, right);

            if (leftRes.maxVal / rightRes.minVal > maxVal) {
                maxVal = leftRes.maxVal / rightRes.minVal;
                if (i + 1 < right) {
                    rightRes.minExp = '(' + rightRes.minExp + ')';
                }
                maxExp = leftRes.maxExp + '/' + rightRes.minExp;
            }

            if (leftRes.minVal / rightRes.maxVal < minVal) {
                minVal = leftRes.minVal / rightRes.maxVal;
                if (i + 1 < right) {
                    rightRes.maxExp = '(' + rightRes.maxExp + ')';
                }
                minExp = leftRes.minExp + '/' + rightRes.maxExp;
            }
        }

        return new Result(maxExp, maxVal, minExp, minVal);
    }

    class Result{
        String maxExp;
        double maxVal;
        String minExp;
        double minVal;

        Result(String maxExp, double maxVal, String minExp, double minVal) {
            this.maxExp = maxExp;
            this.maxVal = maxVal;
            this.minExp = minExp;
            this.minVal = minVal;
        }
    }
}
