/*
* Balanced tree
*
* Each leaf node: a element in the array
* Each non-leaf node: union of the children's range
*
* Operations:
* build(start, end, vals) -> O(n)
* update(index, value) -> O(logn)
* rangeQuery(start, end) -> O(logn + k), k: number of reported segments
*
*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

    20190324
    307
    medium
    O()
    O(n)
* */
package leetcode.SegmentTree;

public class SegmentTree {
    private SegmentTreeNode root;
    private int[] array;

    public SegmentTree(int[] array) {
        this.array = array;
        this.root = build(0, array.length - 1);
    }

    public SegmentTreeNode build(int start, int end) {
        if (start == end) {
            return new SegmentTreeNode(start, end, array[start]);
        }

        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        SegmentTreeNode leftChild = build(start, start + (end - start) / 2);
        SegmentTreeNode rightChild = build(start + (end - start) / 2 + 1, end);
        node.val += leftChild.val + rightChild.val;
        node.left = leftChild;
        node.right = rightChild;
        return node;
    }

    public void update(SegmentTreeNode node, int index, int val) {
        if (index == node.start && index == node.end) {
            node.val = val;
            return;
        }

        if (index <= node.start + (node.end - node.start) / 2) {
            update(node.left, index, val);
        } else {
            update(node.right, index, val);
        }

        node.val = node.left.val + node.right.val;
    }

    public int rangeQuery(SegmentTreeNode node, int i, int j) {
        if (node.start == i && node.end == j) {
            return node.val;
        }

        // [4, 9], [5, 7]
        // 456, 789
        int mid = node.start + (node.end - node.start) / 2;
        if (j <= mid) {
            return rangeQuery(node.left, i, j);
        } else if (i > mid) {
            return rangeQuery(node.right, i, j);
        }

        return rangeQuery(node.left, i, mid) + rangeQuery(node.right, mid + 1, j);
    }

    public static class SegmentTreeNode {
        int start, end, val;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this. val = val;
        }
    }
}


