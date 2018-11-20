/*
* Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

    20181116
    604
    easy
    O()
    O(1)
* */
package leetcode.Design;

public class DesignCompressedStringIterator {
    private int index;
    private int count;
    private String s;
    private char c;

    public DesignCompressedStringIterator(String compressedString) {
        s = compressedString;
    }

    public char next() {
        if (!hasNext()) return ' ';
        if (count > 0) {
            count--;
        } else {
            c = s.charAt(index++);
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                count = 10 * count + s.charAt(index) - '0';
                index++;
            }
            count = count == 0 ? 0 : count - 1;
        }
        return c;
    }

    public boolean hasNext() {
        return !(index == s.length() && count == 0);
    }
}
