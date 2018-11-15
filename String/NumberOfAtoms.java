/*
* Given a chemical formula (given as a string), return the count of each atom.

An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.

Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.

A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.

Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input:
formula = "H2O"
Output: "H2O"
Explanation:
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input:
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation:
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input:
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation:
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.

    20181114
    726
    hard
    O(n)
    O(n)
* */
package leetcode.String;

import java.util.*;

public class NumberOfAtoms {
    public static void main(String[] args) {
        System.out.println(new NumberOfAtoms().countOfAtoms("K4(ON(SO3)2)2"));
    }

    public String countOfAtoms(String F) {
        Map<String, Integer> map = (Map)dfs(F.toCharArray(), 0)[1];
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (String atom : list) {
            sb.append(atom);
            int fre = map.get(atom);
            sb.append(fre == 1 ? "" : fre);
        }
        return sb.toString();
    }

    private Object[] dfs(char[] f, int i) {
        Object[] res = new Object[2];
        if (i >= f.length) {
            res[0] = Integer.valueOf(i);
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        res[1] = map;
        while (i < f.length) {
            if (f[i] == ')') {
                res[0] = Integer.valueOf(i + 1);
                return res;
            }

            if (Character.isLetter(f[i])) {
                String atom = String.valueOf(f[i]);
                if (i + 1 < f.length && Character.isLowerCase(f[i + 1])) {
                    atom += f[++i];
                }
                int count = 0;
                i++;
                while (i < f.length && Character.isDigit(f[i])) {
                    count = 10 * count + f[i] - '0';
                    i++;
                }
                count = count == 0 ? 1 : count;
                map.put(atom, map.getOrDefault(atom, 0) + count);
            } else if (f[i] == '(') {
                Object[] recursionoRes = dfs(f, i + 1);
                i = (Integer) recursionoRes[0];
                Map<String, Integer> subMap = (Map) recursionoRes[1];

                int count = 0;
                while (i < f.length && Character.isDigit(f[i])) {
                    count = 10 * count + f[i] - '0';
                    i++;
                }
                count = count == 0 ? 1 : count;
                for (String atom : subMap.keySet()) {
                    int subCount = subMap.get(atom);
                    map.put(atom, map.getOrDefault(atom, 0) + subCount * count);
                }
            }
        }

        res[0] = i;
        return res;
    }
}
