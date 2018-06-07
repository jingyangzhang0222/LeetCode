package leetcode;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    public static void main(String[] args) {
        MaximumSumOfThreeNonOverlappingSubarrays test = new MaximumSumOfThreeNonOverlappingSubarrays();
        int[] res = test.maxSumOfThreeSubarrays(new int[1], 4);
        for (int num : res) {
            System.out.println(num);
        }
    }
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = prefix[i - 1] + nums[i];
            }
        }
        /*
        int[] newNums = new int[nums.length - k + 1];
        for (int i = 0; i < newNums.length; i++) {
            newNums[i] = prefix[i + k - 1] - prefix[i] + nums[i];
        }
        */
        int[] newNums = new int[] {5,3,1,4,8,1,2,3,7,6,9,5};
        int[] one = new int[k];
        int[] two = new int[k];
        int[] three = new int[k];

        int firstIndex = 0;
        int[] secondIndexes = new int[2];
        int[] thirdIndexes = new int[3];

        int globalMax = Integer.MIN_VALUE;

        for (int i = 0; i < newNums.length; i++) {
            int index = i % k;
            int prevIndex = index == 0 ? k - 1 : index - 1;

            if (i >= 2 * k) {
                if (two[index] + newNums[i] > three[prevIndex]) {
                    three[index] = two[index] + newNums[i];
                    thirdIndexes[2] = i;
                    thirdIndexes[1] = secondIndexes[1];
                    thirdIndexes[0] = secondIndexes[0];
                    globalMax = three[index];
                } else {
                    three[index] = three[prevIndex];
                }
            }

            if (i >= k) {
                if (one[index] + newNums[i] > two[prevIndex]) {
                    two[index] = one[index] + newNums[i];
                    secondIndexes[1] = i;
                    secondIndexes[0] = firstIndex;
                } else {
                    two[index] = two[prevIndex];
                }
            }

            if (newNums[i] > one[prevIndex]) {
                one[index] = newNums[i];
                firstIndex = i;
            } else {
                one[index] = one[prevIndex];
            }
        }
        return thirdIndexes;
    }
}
