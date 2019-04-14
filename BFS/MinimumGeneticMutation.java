/*
* A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.


Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1


Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2


Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3


    20190414
    433
    medium
    O(n ^ 2)
    O(n ^ 2)
* */
package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MinimumGeneticMutation {
    public static void main(String[] args) {
        System.out.println(new MinimumGeneticMutation().minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
    }

    public int minMutation(String start, String end, String[] bank) {
        int n = bank.length;
        List[] edges = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; i++) {
            if (diff(start, bank[i]) == 1) {
                edges[n].add(i);
            }
            if (diff(bank[i], end) == 0) {
                endIndex = i;
            }
            for (int j = i + 1; j < n; j++) {
                if (diff(bank[i], bank[j]) == 1) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }
        if (endIndex == -1) return -1;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        q.offer(n);
        visited[n] = true;
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int index = q.poll();
                if (index == endIndex) {
                    return step;
                }
                for (Integer next : (List<Integer>) edges[index]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int diff(String s1, String s2) {
        int diffCount = 0;
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (++diffCount > 1) {
                    return 2;
                }
            }
        }
        return diffCount;
    }
}
