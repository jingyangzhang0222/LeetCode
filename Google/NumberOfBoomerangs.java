/*
* Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

    20180925
    447
    easy
    O(n ^ 2)
    O(n)
* */
package leetcode.Google;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] i : points) {
            for (int[] j : points) {
                if (i == j) {
                    continue;
                }
                int x1 = i[0], y1 = i[1], x2 = j[0], y2 = j[1];
                int d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                int prevCnt = map.getOrDefault(d, 0);
                map.put(d, prevCnt + 1);
                cnt += prevCnt;
            }
            map.clear();
        }
        return 2 * cnt;
    }
}
