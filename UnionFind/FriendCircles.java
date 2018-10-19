/*
* There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.

    20181011
    547
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.UnionFind;

import java.util.ArrayDeque;
import java.util.Queue;

public class FriendCircles {
    public int findCircleNumUnionFind(int[][] M) {
        UnionFindSet ufs = new UnionFindSet(M.length, true);
        int cnt = M.length;
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    int leaderOfI = ufs.find(i);
                    int leaderOfJ = ufs.find(j);
                    if (leaderOfI != leaderOfJ) {
                        ufs.union(i, j);
                        cnt--;
                    }
                }
            }
        }
        return cnt;
    }

    public int findCircleNumDFS(int[][] M) {
        int cnt = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[i][i] == 1) {
                cnt++;
                dfs(M, i);
            }
        }
        return cnt;
    }

    private void dfs(int[][] M, int i) {
        if (M[i][i] == 0) {
            return;
        }
        M[i][i] = 0;
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1) {
                M[i][j] = 0;
                M[j][i] = 0;
                dfs(M, j);
            }
        }
    }

    public int findCircleNumBFS(int[][] M) {
        int cnt = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[i][i] == 1) {
                cnt++;
                Queue<Integer> q = new ArrayDeque<>();
                q.offer(i);
                M[i][i] = 0;
                while (!q.isEmpty()) {
                    int num = q.poll();
                    for (int j = 0; j < M.length; j++) {
                        if (M[num][j] == 1) {
                            q.offer(j);
                            M[num][j] = 0;
                            M[j][num] = 0;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
