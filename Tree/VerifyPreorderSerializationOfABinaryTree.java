package leetcode.Tree;

public class VerifyPreorderSerializationOfABinaryTree {
    // some rules:
    // 1: any non-left node must have two children
    // 2: to be a valid subtree, any node should receive "true" from both children
    // 3: for the whole tree, after the function call, the index must be equal to length (out of boundary by one index)
    private int index;

    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        index = 0;
        return verify(nodes) && index == nodes.length;
    }

    private boolean verify(String[] nodes) {
        // bad case, too many nodes
        if (index >= nodes.length) {
            return false;
        }
        // base case
        // a null node
        if (nodes[index].equals("#")) {
            index++;
            return true;
        }

        // non-leaf
        // verify in pre-order
        index++;
        return verify(nodes) && verify(nodes);
    }
}
