package leetcode.BuildingBlock;

import leetcode.Tree.TreeNode;

public class ConvertBinaryTreeToDoublyLinkedList {
    public TreeNode toDoubleLinkedList(TreeNode root) {
        TreeNode[] head = new TreeNode[1];
        convert(root, head, new TreeNode[1]);
        return head[0];
    }
    private void convert(TreeNode root, TreeNode[] head, TreeNode[] prev) {
        // base case
        if (root == null) {
            return;
        }
        if (root.left == null && head[0] == null) {
            head[0] = root;
        }

        convert(root.left, head, prev);
        if (prev[0] != null) prev[0].right = root;
        root.left = prev[0];
        prev[0] = root;
        convert(root.right, head, prev);
    }
}
