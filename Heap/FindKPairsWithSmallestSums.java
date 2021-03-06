/*
* You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence:
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence:
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from th

    20181018
    373
    medium
    O(klogk)
    O(k)
* */
package leetcode.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> sol = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return sol;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p1.val - p2.val);
        pq.offer(new Pair(0, 0, nums1[0] + nums2[0]));

        for (int i = 0; i < k; i++) {
            if (pq.isEmpty()) {
                break;
            }
            Pair pair = pq.poll();
            sol.add(new int[]{nums1[pair.idx1], nums2[pair.idx2]});
            if (pair.idx1 + 1 < nums1.length) {
                pq.offer(new Pair(pair.idx1 + 1, pair.idx2, nums1[pair.idx1 + 1] + nums2[pair.idx2]));
            }
            if (pair.idx1 == 0 && pair.idx2 + 1 < nums2.length) {
                pq.offer(new Pair(pair.idx1, pair.idx2 + 1, nums1[pair.idx1] + nums2[pair.idx2 + 1]));
            }
        }
        return sol;
    }

    class Pair{
        int idx1;
        int idx2;
        int val;
        Pair(int x, int y, int val) {
            idx1 = x;
            idx2 = y;
            this.val = val;
        }
    }
}
