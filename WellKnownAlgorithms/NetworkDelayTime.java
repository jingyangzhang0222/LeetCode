package leetcode.WellKnownAlgorithms;

import java.util.*;

public class NetworkDelayTime {
    public static void main(String[] args) {
        System.out.println(new NetworkDelayTime().networkDelayTime(new int[][]{{1,2,1}, {2, 3, 2}, {1, 3, 4}}, 3, 1));
    }

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time :times) {
            if (!map.containsKey(time[0])) {
                map.put(time[0], new ArrayList<int[]>());
            }
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }

        int globalMax = 0;
        int[] time = new int[N + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[K] = 0;
        int cnt = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[1] - i2[1]);
        pq.offer(new int[]{K, 0});

        while (!pq.isEmpty()) {
            int[] node_time = pq.poll();
            int node = node_time[0];
            int timeThisNodeIsNotified = node_time[1];
            if (time[node] == Integer.MAX_VALUE) {
                cnt++;
            }
            time[node] = Math.min(time[node], timeThisNodeIsNotified);
            globalMax = time[node];
            if (cnt == N) break;
            for (int[] nextNode_cost : map.getOrDefault(node, new ArrayList<int[]>())) {
                int nextNode = nextNode_cost[0];
                int cost = nextNode_cost[1];
                int timeNextNodeIsNotified = timeThisNodeIsNotified + cost;
                if (timeNextNodeIsNotified < time[nextNode]) {
                    pq.offer(new int[]{nextNode, timeNextNodeIsNotified});
                }
            }
        }

        return cnt == N ? globalMax : -1;
    }
}
