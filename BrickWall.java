package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BrickWall {
    public static void main(String[] args) {
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(2);
        row1.add(1);

        List<Integer> row2 = new ArrayList<>();
        row2.add(3);
        row2.add(1);
        row2.add(2);

        List<Integer> row3 = new ArrayList<>();
        row3.add(1);
        row3.add(3);
        row3.add(2);

        List<Integer> row4 = new ArrayList<>();
        row4.add(2);
        row4.add(4);

        List<Integer> row5 = new ArrayList<>();
        row5.add(3);
        row5.add(1);
        row5.add(2);

        List<Integer> row6 = new ArrayList<>();
        row6.add(1);
        row6.add(3);
        row6.add(1);
        row6.add(1);

        List<List<Integer>> wall = new ArrayList<>();
        wall.add(row1);
        wall.add(row2);
        wall.add(row3);
        wall.add(row4);
        wall.add(row5);
        wall.add(row6);
        System.out.println(new BrickWall().leastBricks(wall));
    }
    public int leastBricks(List<List<Integer>> wall) {
        int size = 0;
        for (int brick : wall.get(0)) {
            size += brick;
        }
        int[] map = new int[size + 1];
        for (List<Integer> row : wall) {
            int point = 0;
            for (int brick : row) {
                int prevPoint = point;
                point = prevPoint + brick;
                for (int i = prevPoint + 1; i < point; i++) {
                    map[i]++;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < map.length; i++) {
            min = Math.min(min, map[i]);
        }

        return min;
    }
}
