/*
* There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

    20180928
    207
    medium
    O(V * E)
    O(V)
* */
package leetcode.Excellent;

import java.util.ArrayDeque;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int n, int[][] pre) {
        int[] indegree = new int[n];
        for (int[] pair : pre) {
            indegree[pair[0]]++;
        }

        Queue<Integer> canLearn = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                canLearn.offer(i);
            }
        }
        int taken = 0;
        while (!canLearn.isEmpty()) {
            int courseNumber = canLearn.poll();
            taken++;
            for (int[] pair : pre) {
                if (courseNumber == pair[1] && --indegree[pair[0]] == 0) {
                    canLearn.offer(pair[0]);
                }
            }
        }

        return taken == n;
    }
}
