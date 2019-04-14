/*
* Compare two version numbers version1 and version2.
If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.

The . character does not represent a decimal point and is used to separate number sequences.

For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.



    20190319
    165
    medium
    O(n)
    O(n)
* */
package leetcode.String;

public class CompareVersionNumbers {
    public static void main(String[] args) {
        System.out.println(new CompareVersionNumbers().compareVersion("0.1.1.1.1.1", "1.1.2.312.3"));
    }

    public int compareVersion(String version1, String version2) {
        char[] ve1 = version1.toCharArray(), ve2 = version2.toCharArray();
        for (int i = 0; i < ve1.length; i++) {
            if (ve1[i] == '.') ve1[i] = ' ';
        }
        for (int i = 0; i < ve2.length; i++) {
            if (ve2[i] == '.') ve2[i] = ' ';
        }
        String[] v1 = new String(ve1).split(" "), v2 = new String(ve2).split(" ");
        int index1 = 0, index2 = 0;
        while (index1 < v1.length || index2 < v2.length) {
            int num1 = getNumber(v1, index1++), num2 = getNumber(v2, index2++);
            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        return 0;
    }

    private int getNumber(String[] v, int index) {
        if (index >= v.length) {
            return 0;
        }
        String numString = v[index];
        int res = 0;
        boolean leadingZeros = true;
        for (int i = 0; i < numString.length(); i++) {
            if (numString.charAt(i) == '0' && leadingZeros) {
                continue;
            }
            leadingZeros = false;
            res = 10 * res + numString.charAt(i) - '0';
        }
        return res;
    }
}
