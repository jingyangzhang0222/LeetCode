/*
* Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

    20181108
    399
    medium
    O(q.length * (V + E))
    O(V + E)
* */
package leetcode.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String node0 = equations[i][0];
            String node1 = equations[i][1];
            if (!map.containsKey(node0)) {
                map.put(node0, new HashMap<>());
            }
            if (values[i] == 0.0) {
                map.get(node0).put(node0, null);
                continue;
            }
            map.get(node0).put(node1, values[i]);

            if (!map.containsKey(node1)) {
                map.put(node1, new HashMap<>());
            }
            map.get(node1).put(node0, 1.0 / values[i]);
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String begin = queries[i][0], end = queries[i][1];
            if (!map.containsKey(begin) || !map.containsKey(end)) {
                res[i] = -1.0;
            } else {
                if (map.get(begin) == null) {
                    res[i] = 0.0;
                } else {
                    Set<String> set = new HashSet<>();
                    set.add(begin);
                    double[] ans = new double[1];
                    dfs(map, set, begin, end, 1.0, ans);
                    res[i] = ans[0] == 0.0 ? -1.0 : ans[0];
                }
            }
        }
        return res;
    }

    private void dfs(Map<String, Map<String, Double>> map, Set<String> set, String prev, String end, double num, double[] ans) {
        if (ans[0] != 0.0) {
            return;
        }
        if (prev.equals(end)) {
            ans[0] = num;
            return;
        }

        for (String var : map.get(prev).keySet()) {
            if (set.add(var)) {
                dfs(map, set, var, end, num * map.get(prev).get(var), ans);
                set.remove(var);
            }
        }
    }
}
