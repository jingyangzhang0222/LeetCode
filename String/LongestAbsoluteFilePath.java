/*
* Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.

Note:
The name of a file contains at least a . and an extension.
The name of a directory or sub-directory will not contain a ..
Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.

    20180911
    388
    medium
    O(n)
    O(level)
* */
package leetcode.String;

import java.util.ArrayList;
import java.util.List;

public class LongestAbsoluteFilePath {
    public static void main(String[] args) {
        LongestAbsoluteFilePath test = new LongestAbsoluteFilePath();
        System.out.println(test.lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

    public int lengthLongestPath(String input) {
        int len = 0;
        boolean isFile = false;
        int index = 0;
        List<Integer> list = new ArrayList<>();
        int globalMax = 0;

        for (int i = 0; i <= input.length(); i++) {
            char c = i < input.length() ? input.charAt(i) : '\n';
            if (c == '\n') {
                if (index >= list.size()) {
                    list.add(len);
                } else {
                    list.set(index, len);
                }

                if (isFile) {
                    int length = 0;
                    for (int j = 0; j <= index; j++) {
                        length += list.get(j);
                    }
                    globalMax = Math.max(globalMax, length + index);
                }
                index = 0;
                len = 0;
                isFile = false;
            } else if (c == '\t') {
                index++;
            } else if (c == '.') {
                isFile = true;
                len++;
            } else {
                len++;
            }
        }
        return globalMax;
    }
}
