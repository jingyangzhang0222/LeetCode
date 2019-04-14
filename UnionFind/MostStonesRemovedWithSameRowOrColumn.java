/*
* On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000

    20190310
    947
    medium
    O(N∗α(N)), where α is the Inverse-Ackermann function.
    O(n)
* */
package leetcode.UnionFind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn {
    public int removeStones(int[][] stones) {
        // idea: find the number of trees
        // if two points are ont the same colomn or same row, connect them
        // rule: on the same row, a point can only connect to nearest point, which  at last forms a "level"
        // between levels: only 1 edge
        // ______
        //    |
        // ___|_________
        //          |
        //      ___|_____________
        // "connected acyclic graph" -> "tree"
        // inside a tree, we assume the numnber of points is n, start from leaves, we can always make n - 1 moves
        // task: find number of the trees
        // algo: union find
        Map<int[] , Integer> map = new HashMap<>();
        Map<Integer, int[]> rowRoots = new HashMap<>();
        Map<Integer, int[]> colRoots = new HashMap<>();
        int[] parents = new int[stones.length];
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
            parents[i] = i;
        }

        for (int[] stone : stones) {
            if (!rowRoots.containsKey(stone[0])) {
                rowRoots.put(stone[0], stone);
            } else {
                union(parents, map.get(stone), map.get(rowRoots.get(stone[0])));
            }
            if (!colRoots.containsKey(stone[1])) {
                colRoots.put(stone[1], stone);
            } else {
                union(parents, map.get(stone), map.get(colRoots.get(stone[1])));
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            set.add(find(parents, map.get(stone)));
        }
        return stones.length - set.size();
    }

    private void union(int[] parents, int s1, int s2) {
        int parent1 = find(parents, s1);
        int parent2 = find(parents, s2);
        if (parent1 != parent2) {
            parents[parent1] = parent2;
        }
    }

    private int find(int[] parents, int s) {
        if (parents[s] != s) {
            parents[s] = find(parents, parents[s]);
        }
        return parents[s];
    }
}
