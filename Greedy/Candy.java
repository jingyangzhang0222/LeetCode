/*
* There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Example 1:

Input: [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
             The third child gets 1 candy because it satisfies the above two conditions.

    20181108
    135
    hard
    O(max(n, range))
    O(n)
* */
package leetcode.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Candy {
    public int candy(int[] ratings) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ratings.length; i++) {
            int ori = ratings[i];
            int start = i;
            while (i + 1 < ratings.length && ratings[i + 1] == ori) {
                i++;
            }
            min = Math.min(min, ori);
            max = Math.max(max, ori);
            if (!map.containsKey(ori)) {
                map.put(ori, new ArrayList<>());
            }
            map.get(ori).add(new int[]{start, i});
        }

        int sum = 0;
        int[] helper = new int[ratings.length];
        for (int i = min; i <= max; i++) {
            if (!map.containsKey(i)) {
                continue;
            }
            for (int[] b : map.get(i)) {
                int left = b[0], right = b[1];
                if (left == right) {
                    int leftNei = left == 0 ? 0 : helper[left - 1];
                    int rightNei = right == helper.length - 1 ? 0 : helper[right + 1];
                    helper[left] = Math.max(leftNei, rightNei) + 1;
                    sum += helper[left];
                } else {
                    helper[left] = left == 0 ? 1 : helper[left - 1] + 1;
                    helper[right] = right == helper.length - 1 ? 1 : helper[right + 1] + 1;
                    sum += helper[left];
                    sum += helper[right];
                    sum += right - left - 1;
                }
            }
        }
        return sum;
    }
}
