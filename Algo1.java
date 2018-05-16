package leetcode;

public class Algo1 {
    static final int s = 0, t = 1, x = 2, y = 3, z = 4, MAX = Integer.MAX_VALUE;
    static final int[] w = {7, 6, 2, 4, 9, -3, -2, -4, 8, 5};
    static final int[][] edges = {{s, y}, {s, t}, {z, s}, {z, x}, {y, z}, {y, x}, {x, t}, {t, z}, {t, y}, {t, x}};

    public static void main(String[] args) {
        int[] d = {0, MAX, MAX, MAX, MAX};

        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j < edges.length; j++) {
                if (d[edges[j][1]] > d[edges[j][0]] + w[j]) {
                    d[edges[j][1]] = d[edges[j][0]] + w[j];
                }
            }
        }
    }
}