/*
* Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.



Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true


Note:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='

    20190306
    990
    medium
    O()
    O()
* */
package leetcode.Graph;

import java.util.HashSet;
import java.util.*;

public class SatisfiabilityOfEqualityEquations {
    public static void main(String[] args) {
        System.out.println(new SatisfiabilityOfEqualityEquations().equationsPossible(new String[]{"f==a","a==b","f!=e","a==c","b==e","c==f"}));
    }
    public boolean equationsPossible(String[] equations) {
        int[] parents = new int[26];
        for (int i = 0; i < 26; i++) {
            parents[i] = i;
        }
        for (String e : equations) {
            if (e.charAt(1) == '=') {
                int c1 = e.charAt(0) - 'a';
                int c2 = e.charAt(3) - 'a';
                union(parents, c1, c2);
            }
        }

        for (String e : equations) {
            if (e.charAt(1) != '=' && find(parents, e.charAt(0) - 'a') == find(parents, e.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

    private void union(int[] parents, int c1, int c2) {
        int c1Parent = find(parents, c1);
        int c2Parent = find(parents, c2);
        parents[c1] = c2Parent;
    }

    private int find(int[] parents, int c) {
        if (parents[c] == c) {
            return c;
        }
        parents[c] = find(parents, parents[c]);
        return parents[c];
    }
}
