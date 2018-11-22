/*
* A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

    20181120
    218
    hard
    O(n ^ 2)
    O(n)
* */
package leetcode.Excellent;

import java.util.*;

public class TheSkylineProblem {
    public static void main(String[] args) {
        int[][] buildings = new int[][]{{0, 5, 7}, {5, 10, 7}, {5, 10, 12}, {10, 15, 7}, {15, 20, 7}, {15, 20, 12}, {20, 25, 7}};
        for (int[] line : new TheSkylineProblem().getSkyline(buildings)) {
            System.out.print(line[0] + " ");
            System.out.print(line[1]);
            System.out.println();
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        BuildingLine[] buildingLines = new BuildingLine[buildings.length * 2];
        Map<Integer, BuildingLine> map = new HashMap<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            buildingLines[2 * i] = new BuildingLine(i, b[0], b[2], true);
            buildingLines[2 * i + 1] = new BuildingLine(i, b[1], b[2], false);
            map.put(i, buildingLines[2 * i]);
        }

        Arrays.sort(buildingLines);
        PriorityQueue<BuildingLine> pq = new PriorityQueue<>(new MyComparator());
        List<int[]> res = new ArrayList<>();

        for (BuildingLine line : buildingLines) {
            int prevMax = pq.isEmpty() ? 0 : pq.peek().height;
            if (line.isStart) {
                pq.offer(line);
                if (pq.peek().height > prevMax) {
                    res.add(new int[]{line.x, line.height});
                }
            } else {
                pq.remove(map.get(line.id));
                int curMax = pq.isEmpty() ? 0 : pq.peek().height;
                if (curMax < prevMax) {
                    res.add(new int[]{line.x, curMax});
                }
            }
        }

        return res;
    }

    static class MyComparator implements Comparator<BuildingLine> {
        @Override
        public int compare(BuildingLine l1, BuildingLine l2) {
            return l2.height - l1.height;
        }
    }

    static class BuildingLine implements Comparable<BuildingLine> {
        int id;
        int x;
        int height;
        boolean isStart;

        BuildingLine(int id, int x, int height, boolean isStart) {
            this.x = x;
            this.height = height;
            this.isStart = isStart;
            this.id = id;
        }

        @Override
        public int compareTo(BuildingLine l2) {
            if (this.x != l2.x) {
                return this.x - l2.x;
            }

            if (!this.isStart && !l2.isStart) {
                return this.height - l2.height;
            } else if (this.isStart && !l2.isStart) {
                return -1;
            } else if (!this.isStart && l2.isStart) {
                return 1;
            } else {
                return l2.height - this.height;
            }
        }
    }
}
