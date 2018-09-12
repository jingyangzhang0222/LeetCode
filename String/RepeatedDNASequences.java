/*
* All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

Example:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"

Output: ["AAAAACCCCC", "CCCCCAAAAA"]

    20180909
    187
    medium
    O(n)
    O(n)
* */
package leetcode.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequences {
    public static void main(String[] args) {
        RepeatedDNASequences test = new RepeatedDNASequences();
        System.out.println(test.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> sol = new ArrayList<>();
        Map<Long, Integer> map = new HashMap<>();
        long code = 0;
        final int DENOMINATOR = 1000000000;
        //                      1324123422
        for (int i = 0; i < s.length(); i++) {
            int dig = 0;
            switch (s.charAt(i)) {
                case 'A': dig = 1;
                    break;
                case 'C': dig = 2;
                    break;
                case 'T': dig = 3;
                    break;
                case 'G': dig = 4;
                    break;
            }
            if (code / DENOMINATOR == 0) {
                code = 10 * code + dig;
            } else {
                code = 10 * (code % DENOMINATOR) + dig;
            }
            if (!map.containsKey(code)) {
                map.put(code, 0);
            }
            map.put(code, map.get(code) + 1);
            if (map.get(code) == 2) {
                sol.add(decode(code));
            }
        }
        return sol;
    }

    private String decode(long code) {
        StringBuilder sb = new StringBuilder();
        long DENOMINATOR = 1000000000;
        while (DENOMINATOR >= 1) {
            int dig = (int)(code / DENOMINATOR);
            switch (dig) {
                case 1: sb.append('A');
                    break;
                case 2: sb.append('C');
                    break;
                case 3: sb.append('T');
                    break;
                case 4: sb.append('G');
                    break;
            }

            code %= DENOMINATOR;
            DENOMINATOR /= 10;
        }

        return sb.toString();
    }
}
