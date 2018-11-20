/*
* 536. Construct Binary Tree from String
Medium
197
53


You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   /
  3   1 5
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".

    20181120
    536
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        if ("".equals(s)) {
            return null;
        }

        TreeNode dummyRoot = new TreeNode(0);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offerLast(dummyRoot);
        int index = -1;
        while (index <= s.length()) {
            char c;
            if (index == -1) {
                c = '(';
            } else if (index == s.length()) {
                c = ')';
            } else {
                c = s.charAt(index);
            }

            if (c == '(') {
                index++;
                boolean negative = false;
                if (s.charAt(index) == '-') {
                    negative = true;
                    index++;
                }
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = 10 * num + s.charAt(index) - '0';
                    index++;
                }
                stack.offerLast(new TreeNode(negative ? -num : num));
            } else {
                TreeNode child = stack.pollLast();
                if (stack.peekLast().left == null) {
                    stack.peekLast().left = child;
                } else {
                    stack.peekLast().right = child;
                }
                index++;
            }
        }

        return dummyRoot.left;
    }
}
