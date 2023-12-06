package structures.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algorhitms.search.Binary;
import util.Util;

public class BST {
    private Node root;

    public BST() {
        this.root = null;
    }

    public static BST create() {
        return new BST();
    }

    public void insert(int value) {
        this.root = this.insertRec(this.root, value);
    }

    private Node insertRec(Node current, int value) {
        if (current == null) {
            current = new Node(value);
            return current;
        }

        if (value < current.value) {
            current.left = insertRec(current.left, value);
        } else if (value > current.value) {
            current.right = insertRec(current.right, value);
        }

        return current;
    }

    public Node search(int value) {
        return this.searchRec(this.root, value);
    }

    private Node searchRec(Node current, int value) {
        if (current == null || current.value == value)
            return current;

        if (value < current.value) {
            return this.searchRec(current.left, value);
        } else {
            return this.searchRec(current.right, value);
        }
    }

    public Node delete(int value) {
        if (this.search(value) == null)
            return null;

        return this.deleteRec(this.root, value);
    }

    private Node deleteRec(Node current, int value) {
        if (current == null)
            return null;

        if (value < current.value)
            current.left = this.deleteRec(current.left, value);
        else if (value > current.value)
            current.right = this.deleteRec(current.right, value);
        else {
            if (current.left == null && current.right == null) {
                return null;
            } else if (current.left == null || current.left == null) {
                if (current.left == null)
                    return current.right;
                else
                    return current.left;
            } else {
                current.value = this.inorderSuccessor(current).value;
                current.right = this.deleteRec(current.right, current.value);
            }
        }

        return current;
    }

    private Node minNode(Node current) {
        Node min = current;

        while (current.left != null) {
            current = (min = current.left);
        }

        return min;
    }

    private Node inorderSuccessor(Node current) {
        return this.minNode(current.right);
    }

    public void print() {
        System.out.println(this.postorder());
    }

    public List<Node> preorder() {
        List<Node> tmp = new ArrayList<>();
        this.preorderRec(this.root, tmp);
        return tmp;
    }

    private void preorderRec(Node current, List<Node> tmp) {
        if (current == null) {
            return;
        }

        tmp.add(current);
        preorderRec(current.left, tmp);
        preorderRec(current.right, tmp);
    }

    public List<Node> inorder() {
        List<Node> tmp = new ArrayList<>();
        this.inorderRec(this.root, tmp);
        return tmp;
    }

    private void inorderRec(Node current, List<Node> tmp) {
        if (current == null) {
            return;
        }

        inorderRec(current.left, tmp);
        tmp.add(current);
        inorderRec(current.right, tmp);
    }

    public List<Node> postorder() {
        List<Node> tmp = new ArrayList<>();
        this.postorderRec(this.root, tmp);
        return tmp;
    }

    private void postorderRec(Node current, List<Node> tmp) {
        if (current == null) {
            return;
        }

        postorderRec(current.left, tmp);
        postorderRec(current.right, tmp);
        tmp.add(current);
    }
}
