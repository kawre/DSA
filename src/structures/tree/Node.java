package structures.tree;

public class Node {
    public int value;
    public Node left;
    public Node right;

    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }

    Node(Node node) {
        this.value = node.value;
        right = node.right;
        left = node.left;
    }

    Node() {
        right = null;
        left = null;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
