/*
* You're given strings J representing the types of stones that are jewels, and S representing the stones you have.
* Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive,
so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.

    20180618
    771
    easy
    O(m + n)
    O(1)
* */
package leetcode;

public class JewelsAndStones {
    public int numJewelsInStones(String J, String S) {
        boolean[] littleMap = new boolean[26];
        boolean[] bigMap = new boolean[26];
        for (int i = 0; i < J.length(); i++) {
            if (J.charAt(i) - 'a' >= 0 && 'z' - J.charAt(i) <= 25) {
                littleMap[J.charAt(i) - 'a'] = true;
            } else {
                bigMap[J.charAt(i) - 'A'] = true;
            }
        }

        int count = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) - 'a' >= 0 && 'z' - S.charAt(i) <= 25) {
                count += littleMap[S.charAt(i) - 'a'] ? 1 : 0;
            } else {
                count += bigMap[S.charAt(i) - 'A'] ? 1 : 0;
            }
        }
        return count;
    }
}