/*
* Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

Note:

All letters in hexadecimal (a-f) must be in lowercase.
The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
The given number is guaranteed to fit within the range of a 32-bit signed integer.
You must not use any method provided by the library which converts/formats the number to hex directly.
Example 1:

Input:
26

Output:
"1a"
Example 2:

Input:
-1

Output:
"ffffffff"

    20181002
    405
    easy
    O(1)
    O(1)
* */
package leetcode.Bit;

public class ConvertANumberToHexadecimal {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        int tmp = 0;
        int base = 8;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            tmp += bit == 1 ? base : 0;
            if (i % 4 == 0) {
                char dig = tmp >= 10 ? (char)(tmp - 10 + 'a') : (char)(tmp + '0');
                if (!(sb.length() == 0 && dig == '0')) {
                    sb.append(dig);
                }
                base = 8;
                tmp = 0;
            } else {
                base /= 2;
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public String toHexAns(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result;
            num = (num >>> 4);
        }
        return result;
    }
}
