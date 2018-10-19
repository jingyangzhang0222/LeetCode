package leetcode.Google;

import java.util.Arrays;
import java.util.Comparator;

public class QueueReconstructionByHeight {
    public static void main(String[] args) {
        int[][] res = new QueueReconstructionByHeight().reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        for (int[] row : res) {
            if (row == null) {
                System.out.println("null");
                continue;
            }
            for (int num : row) {
                System.out.print(num + ",");
            }
            System.out.println();
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new MyComparator());

        int[][] res = new int[people.length][];

        int prevHeight = -1;
        int heightCount = 0;
        for (int[] pair : people) {
            int height = pair[0];
            if (height == prevHeight) {
                heightCount++;
            } else {
                heightCount = 0;
            }
            int relativeIndex = pair[1] - heightCount;
            int cur = 0;

            while (relativeIndex >= 0) {
                if (res[cur] != null) {
                    cur++;
                    continue;
                }
                if (relativeIndex == 0) {
                    res[cur] = pair;
                    break;
                }

                if (res[cur] == null) {
                    relativeIndex--;
                }
                cur++;
            }
            prevHeight = height;
        }
        return res;
    }

    private static class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1[0] != i2[0]) {
                return i1[0] < i2[0] ? -1 : 1;
            } else if (i1[1] != i2[1]) {
                return i1[1] < i2[1] ? -1 : 1;
            }
            return 0;
        }
    }
}
