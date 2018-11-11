/*
* Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.

    20181110
    421
    medium
    O(n)
    O(n)
* */
package leetcode.Trie;

public class MaximumXOROfTwoNumbersInAnArray {
    private TrieNode root;

    public int findMaximumXOR(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        root = new TrieNode();
        int globalMax = 0;
        for (int num : nums) {
            TrieNode cur = root;
            // build
            for (int offset = 31; offset >= 0; offset--) {
                int bit = (num >> offset) & 1;
                if (cur.children[bit] == null) {
                    cur.children[bit] = new TrieNode();
                }
                cur = cur.children[bit];
            }

            int localMax = 0;
            cur = root;
            for (int offset = 31; offset >= 0; offset--) {
                int bit = (num >> offset) & 1;
                int matchedBit = bit == 1 ? 0 : 1;

                if (cur.children[matchedBit] != null) {
                    cur = cur.children[matchedBit];
                    localMax |= 1 << offset;
                } else {
                    cur = cur.children[bit];
                }
            }

            globalMax = Math.max(globalMax, localMax);
        }

        return globalMax;
    }

    class TrieNode {
        TrieNode[] children;

        TrieNode() {
            children = new TrieNode[2];
        }
    }
}
