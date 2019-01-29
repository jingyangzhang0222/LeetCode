package leetcode.Greedy;

public class SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        System.out.println(new SplitArrayIntoConsecutiveSubsequences().isPossible(new int[]{1,2,3,3,4,5}));
    }

    public boolean isPossible(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int s = -1;
        Count curCount = null, prevCount = null;
        for (int f = 0; f < nums.length; f++) {
            if (f < nums.length - 1 && nums[f] == nums[f + 1]) {
                continue;
            }
            int count = f - s;
            if (curCount == null) {
                curCount = new Count(count, 0, 0);
                s = f;
                continue;
            }

            int prevNum = nums[s], curNum = nums[f];
            s = f;
            prevCount = curCount;

            if (curNum != prevNum + 1) {
                if (prevCount.sin != 0 || prevCount.dou != 0) {
                    return false;
                } else {
                    prevCount = new Count(count, 0, 0);
                }
            }

            // nums[f] == nums[s] + 1
            if (count < prevCount.sin + prevCount.dou) {
                return false;
            }
            int curDou = prevCount.sin;
            count -= (prevCount.sin + prevCount.dou);
            int curMul = prevCount.dou + Math.min(prevCount.mul, count);
            int curSin = Math.max(0, count - prevCount.mul);

            curCount = new Count(curSin, curDou, curMul);
        }

        return curCount.sin == 0 && curCount.dou == 0;
    }

    class Count{
        int sin;
        int dou;
        int mul;

        Count(int sin, int dou, int mul) {
            this.sin = sin;
            this.dou = dou;
            this.mul = mul;
        }
    }
}
