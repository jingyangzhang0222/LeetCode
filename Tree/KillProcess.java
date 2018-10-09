/*
* Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:
Input:
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation:
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.
Note:
The given kill id is guaranteed to be one of the given PIDs.
n >= 1.

    20181005
    582
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.*;

public class KillProcess {
    public List<Integer> killProcess(List<Integer> pids, List<Integer> ppids, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Iterator<Integer> pi = pids.iterator();
        Iterator<Integer> ppi = ppids.iterator();
        while (pi.hasNext()) {
            int pid = pi.next();
            int ppid = ppi.next();
            if (!map.containsKey(ppid)) {
                map.put(ppid, new ArrayList<>());
            }
            map.get(ppid).add(pid);
        }
        List<Integer> res = new ArrayList<>();
        dfs(map, res, kill);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> map, List<Integer> res, int ppid) {
        res.add(ppid);
        for (int pid : map.getOrDefault(ppid, new ArrayList<>())) {
            dfs(map, res, pid);
        }
    }
}
