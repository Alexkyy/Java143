package BST.LLRBT;

/**
 *
 * @author alexyang
 */
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * Student name: Sijin Yang
 *
 * Objectives:  Complete following functions
 * preOrder - in the textbook
 * inOrder - in the textbook
 * postOrder - in the textbook
 * size() - return the size of the tree
 * clone() - perform a deep copy
 * reverse() - reverse the binary search tree.
 * equals() - compare two binary trees
 * Write an equals function that compares the two binary search trees.
 * Return true if they are identical. Otherwise, return false
 * @param <T>
 */
public class Bst<T extends Comparable<T>> {

    public static void main(String[] args) {
        Bst<Integer> bst = new Bst<>();
        bst.add(9);
        bst.add(6);
        bst.add(14);
        bst.add(7);
        bst.add(11);
        bst.add(19);

        if (bst.contains(45)) {
            System.out.println("FAIL the contains test expect false");
            return;
        }

        if (!bst.contains(7)) {
            System.out.println("FAIL the contains test expect to have value 7");
            return;
        }

        if (!bst.min().equals(6)) {
            System.out.println("FAIL the min test expect to equals 6 but got " + bst.min());
            return;
        }

        if (!bst.max().equals(19)) {
            System.out.println("FAIL the max test expect to equals 19 but got " + bst.max());
            return;
        }

        Queue<Integer> inorder = bst.inOrder();
        List<Integer> test =Arrays.asList(6,7,9,11,14,19);
        if (!inorder.toString().equals(test.toString())) {
            System.out.println("FAIL the inorder test expect " + test);
            return;
        }

        Queue<Integer> preorder = bst.preOrder();
        List<Integer> test2 =Arrays.asList(9,6,7,14,11,19);
        if (!preorder.toString().equals(test2.toString())) {
            System.out.println("FAIL the postorder test expect " + test2 + " but got " + preorder);
            return;
        }

        Queue<Integer> postorder = bst.postOrder();
        List<Integer> test3 =Arrays.asList(7, 6, 11, 19, 14, 9);
        if (!postorder.toString().equals(test3.toString())) {
            System.out.println("FAIL the postorder test expect " + test3 + " but got " + postorder);
            return;
        }
        
        Bst<Integer> cloneVersion = bst.clone();

        bst.delete(7);
        if (bst.contains(7)) {
            System.out.println("FAIL the delete test expect DO NOT have value 7");
            return;
        }

        bst.delete(9);
        if (bst.contains(9)) {
            System.out.println("FAIL the delete test expect DO NOT have value 9");
            return;
        }

        if (bst.size() != 4) {
            System.out.println("FAIL the delete test expect to have size is 4 but got " + bst.size());
            return;
        }

        bst.delete(44);
        if (bst.contains(44)) {
            System.out.println("FAIL the delete test expect DO NOT have value 44");
            return;
        }

        if (!cloneVersion.contains(9)) {
            System.out.println("FAIL the clone test expect DO have value 9");
            return;
        }

        if (cloneVersion.size() != 6) {
            System.out.println("FAIL the clone test expect DO have the size is 6 " + cloneVersion.size());
            return;
        }

        Bst<Integer> orig = cloneVersion.reverse().reverse();
        if (!orig.equals(cloneVersion)) {
            System.out.println("FAIL the reseve test expect to equals the orig");
            System.out.println(cloneVersion.preOrder());
            System.out.println(orig.preOrder());
            return;
        }
        System.out.println("Pass all tests");
    }
    private Node<T> root;


    public Bst<T> clone() {
        Bst<T> copy = new Bst<>();
        copy.root = clone(this.root);
        return copy;
    }

    private Node<T> clone(Node<T> node) {
        if (node == null) return null;
        Node<T> newNode = new Node<>(node.value);
        newNode.left = clone(node.left);
        newNode.right = clone(node.right);
        newNode.size = 1 + size(newNode.left) + size(newNode.right);
        return newNode;
    }

    public Bst<T> reverse() {
        Bst<T> reversed = new Bst<>();
        reversed.root = reverse(root);
        return reversed;
    }

    private Node<T> reverse(Node<T> node) {
        if (node == null) return null;
        Node<T> newNode = new Node<>(node.value);
        newNode.left = reverse(node.right);
        newNode.right = reverse(node.left);
        newNode.size = 1 + size(newNode.left) + size(newNode.right);
        return newNode;
    }

    public boolean equals(Bst<T> right) {
        return equals(this.root, right.root);
    }

    private boolean equals(Node<T> node1, Node<T> node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        if (!node1.value.equals(node2.value)) return false;
        return equals(node1.left, node2.left) && equals(node1.right, node2.right);
    }

    public int size() { return root.size; }

    private int size(Node node) {
        if (null == node) return 0;
        return node.size;
    }
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(Node<T> node, T value) {
        if (null == node) return false;
        if (node.value.compareTo(value) == 0) return true;
        if (node.value.compareTo(value) > 0) return contains(node.left, value);
        return contains(node.right, value);
    }

    public void add(T value) {
        if (null == value) throw new IllegalArgumentException("value cannot be NULL");
        if (contains(value)) return;
        root = add(root, value);
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) {
            node = new Node(value);
        }
        else if (node.value.compareTo(value) > 0) node.left = add(node.left, value);
        else node.right = add(node.right, value);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }


    public T min() {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        return min(root).value;
    }

    private Node<T> min(Node<T> node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Node<T> deleteMin() {
        if (null == root) throw new IllegalArgumentException("The tree is empty");
        return deleteMin(root);
    }

    private Node<T> deleteMin(Node<T> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public T max() {
        if (root == null) throw new IllegalArgumentException("Tree is empty");
        return max(root).value;
    }

    private Node<T> max(Node<T> node) {
        if (node.right == null) return node;
        return max(node.right);
    }


    public void delete(T value) {
        if (!contains(value)) return;
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) return node;
        if (node.value.compareTo(value) > 0) node.left = delete(node.left, value);
        else if (node.value.compareTo(value) < 0) node.right = delete(node.right, value);
        else {
            if (node.right == null) return node.left;
            Node old = node;
            node = min(old.right);
            node.right = deleteMin(old.right);
            node.left = old.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Queue<T> inOrder() {
        final Queue<T> result = new ArrayDeque<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<T> node, Queue<T> queue) {
        if (node != null) {
            inOrder(node.left, queue);
            queue.add(node.value);
            inOrder(node.right, queue);
        }
    }


    public Queue<T> preOrder() {
        final Queue<T> result = new ArrayDeque<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(Node<T> node, Queue<T> queue) {
        if (null == node) return;
        queue.add(node.value);
        preOrder(node.left, queue);
        preOrder(node.right, queue);
    }

    public Queue<T> postOrder() {
        final Queue<T> result = new ArrayDeque<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(Node<T> node, Queue<T> queue) {
        if (null == node) return;
        postOrder(node.left, queue);
        postOrder(node.right, queue);
        queue.add(node.value);
    }


    class Node<T> {
        T value;
        Node left;
        Node right;

        int size;
        public Node(T value) {
            this.value = value;
            this.size = 1;
        }
    }
}
