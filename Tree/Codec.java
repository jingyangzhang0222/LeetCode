/*
* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

    20190221
    297
    hard
    O(n)
    O(n)
* */


package leetcode.Tree;

public class Codec {
    public static void main(String[] args) {
        Codec codec = new Codec();
        TreeNode root = new TreeNode(1);
        TreeNode res = codec.deserialize(codec.serialize(root));
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        // use preorder
        sb.append(node == null ? "#" : node.val);
        sb.append(',');
        if (node == null) {
            return;
        }

        buildString(node.left, sb);
        buildString(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        int[] startIndex = new int[1];
        return buildTree(data, startIndex);
    }

    private TreeNode buildTree(String s, int[] startIndex) {
        if (s.charAt(startIndex[0]) == '#') {
            startIndex[0] += 2;
            return null;
        }

        int endIndex = startIndex[0];
        int num = 0;
        boolean positive = true;
        if (s.charAt(startIndex[0]) == '-') {
            positive = false;
            endIndex++;
        }
        while (endIndex < s.length() && Character.isDigit(s.charAt(endIndex))) {
            num = num * 10 + s.charAt(endIndex) - '0';
            startIndex[0] = endIndex++;
        }
        // now startIndex[0]: ths last valid digit
        startIndex[0] += 2;

        TreeNode node = new TreeNode(positive ? num : -num);
        node.left = buildTree(s, startIndex);
        node.right = buildTree(s, startIndex);

        return node;
    }
}
