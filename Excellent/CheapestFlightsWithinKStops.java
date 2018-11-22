/*
* There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.

    20181120
    787
    medium
    O(E + nlogn)
    O(n)
* */
package leetcode.Excellent;

import java.util.*;

public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(3, new int[][]{{0, 1, 1},
                                                                                                 {0, 2, 5},
                                                                                                 {1, 2, 1},
                                                                                                 {2, 3, 1}}, 0, 2, 0));

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            if (!graph.containsKey(flight[0])) {
                graph.put(flight[0], new ArrayList<>());
            }

            graph.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[2] - a2[2]);
        pq.offer(new int[]{src, 0, 0});

        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            if (tmp[0] == dst) {
                return tmp[2];
            }

            // bound of flights number reached
            if (tmp[1] == K + 1) continue;
            // no flight from this stop
            if (!graph.containsKey(tmp[0])) continue;

            for (int[] destAndCost : graph.get(tmp[0])) {
                int nextStop = destAndCost[0];
                int nextCost = destAndCost[1];
                pq.offer(new int[]{nextStop, tmp[1] + 1, tmp[2] + nextCost});
            }
        }

        return -1;
    }
}
