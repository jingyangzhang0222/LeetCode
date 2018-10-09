/*
* Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

    20181005
    572
    easy
    O(n^2)
    O(n)
* */
package leetcode.Tree;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String ts = stringfyTree(t);
        boolean[] found = new boolean[1];
        find(s, ts, found);
        return found[0];
    }

    private String find(TreeNode root, String target, boolean[] found) {
        if (root == null) {
            return "n";
        }
        if (found[0]) {
            return "";
        }

        String left = find(root.left, target, found);
        String right = find(root.right, target, found);

        String encoded = left + "," + right + "," + String.valueOf(root.val);
        if (encoded.equals(target)) {
            found[0] = true;
            return "";
        }
        return encoded;
    }

    private String stringfyTree(TreeNode root) {
        if (root == null) {
            return "n";
        }

        String leftPostOrder = stringfyTree(root.left);
        String rightPostOrder = stringfyTree(root.right);

        return leftPostOrder + "," + rightPostOrder + "," +String.valueOf(root.val);
    }
}
