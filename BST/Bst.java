package BST;

//Author Sijin Yang
import java.util.ArrayList;
import java.util.List;

public class Bst {
    BstNode root;

    class BstNode {
        Integer key;
        BstNode left;
        BstNode right;
        int count;

        public BstNode(Integer key) {
            this.key = key;
            this.count = 1; 
        }
    }
    public int size(){ return size(root); }
        
    private int size(BstNode node){
            if(null==node) return 0;
            return node.count;
        }    
        
    public boolean search(Integer searchKey) {
        return search(searchKey, root);
    }

    private boolean search(Integer searchKey, BstNode node) {
        if (node == null) return false;
        if (node.key.equals(searchKey)) return true;
        else if (node.key > searchKey) {
            return search(searchKey, node.left);
        } else {
            return search(searchKey, node.right);
        }
    }

    public BstNode insert(Integer key) {
        root = insert(key, root);
        return root;
    }

    private BstNode insert(Integer key, BstNode node) {
        if (node == null) return new BstNode(key);
        if (node.key < key) {
            node.right = insert(key, node.right);
        } else if (node.key > key) {
            node.left = insert(key, node.left);
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public List<Integer> inOrder() {
        final List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(BstNode node, List<Integer> queue) {
        if (null == node) return;
        inOrder(node.left, queue);
        queue.add(node.key);
        inOrder(node.right, queue);
    }

    public List<Integer> preOrder() {
        final List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(BstNode node, List<Integer> queue) {
        if (null == node) return;
        queue.add(node.key);
        preOrder(node.left, queue);
        preOrder(node.right, queue);
    }

    public List<Integer> postOrder() {
        final List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(BstNode node, List<Integer> queue) {
        if (null == node) return;
        postOrder(node.left, queue);
        postOrder(node.right, queue);
        queue.add(node.key);
    }

    public Integer max() {
        if (null == root) return null;
        return max(root).key;
    }

    private BstNode max(BstNode node) {
        if (null == node.right) return node;
        return max(node.right);
    }

    public Integer min() {
        if (null == root) return null;
        return min(root).key;
    }

    private BstNode min(BstNode node) {
        if (null == node.left) return node;
        return min(node.left);
    }

    public Integer floor(Integer key) {
        BstNode result = floor(key, root);
        if (null == result) return null;
        return result.key;
    }

    private BstNode floor(Integer key, BstNode node) {
        if (null == node) return null;
        if (key < node.key) return floor(key, node.left);
        else {
            BstNode tmp = floor(key, node.right);
            if (null != tmp) return tmp;
            return node;
        }
    }
    
    public Integer ceiling(Integer key) {
        BstNode result = ceiling(key, root);
        if (null == result) return null;
        return result.key;
    }

    private BstNode ceiling(Integer key, BstNode node) {
       if (null == node) return null;
        if (key > node.key) return ceiling(key, node.right);
        else {
            BstNode tmp = ceiling(key, node.left);
            if (null != tmp) return tmp;
            return node;
        }
    }
    
    public int rank(int searchKey){
        return rank(searchKey, root);
    }
    
    private int rank(int searchKey, BstNode node){
        if(node == null) return 0;
        if(searchKey > node.key) return 1 + size(node.left) + rank(searchKey, node.right);
        if(searchKey < node.key) return rank(searchKey, node.left);
        else return size(node.left);
        
    }
    
    public void delMin(){
        if(null == root) return;
        delMin(root);
    }
    
    private BstNode delMin(BstNode node){
        if(node.left == null) return node.right;
        node.left = delMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
        
    }
    
    public void delete(int searchKey){
        root = delete(searchKey, root);
    }
    
    private BstNode delete(int searchKey, BstNode node){
        if(null == node) return null;
        if(searchKey < node.key)  node.left = delete(searchKey, node.left);
        else if(searchKey > node.key)  node.right = delete(searchKey, node.right);
        else{
            if(node.right == null) return node.left;
            
            BstNode tmp = node;
            
            node = min(tmp.right);
            
            node.right = delMin(tmp.right);
            node.left = tmp.left;
        }
        
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }
}