/*
* A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.


Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]


Note:

The initial given tree is complete and contains between 1 and 1000 nodes.
CBTInserter.insert is called at most 10000 times per test case.
Every value of a given or inserted node is between 0 and 5000.


    20181127
    919
    medium
    O(1)
    O(n)
* */
package leetcode.Design;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class CompleteBinaryTreeInserter {
    private final TreeNode root;
    private int index;
    private Deque<TreeNode> dq;

    public CompleteBinaryTreeInserter(TreeNode root) {
        this.root = root;
        dq = new ArrayDeque<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left == null || cur.right == null) {
                dq.offerLast(cur);
            }
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode node = new TreeNode(v);
        TreeNode parent = dq.peekFirst();
        if (parent.left == null) {
            parent.left = node;
        } else {
            parent.right = node;
            dq.pollFirst();
        }
        dq.offerLast(node);
        return parent.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
