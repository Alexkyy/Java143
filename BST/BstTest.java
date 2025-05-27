package BST;

//Author Sijin yang
public class BstTest {
    public static void main(String[] args) {
        Bst tree = new Bst();

        System.out.println(tree.inOrder());
        System.out.println(tree.preOrder());
        System.out.println(tree.postOrder());
        System.out.println(null == tree.max());
        System.out.println(null == tree.min());
        System.out.println(tree.floor(19) == null);
        System.out.println(tree.floor(18) == null);

        System.out.println(tree.size() == 0);
        System.out.println(tree.rank(45) == 0);

        tree.delMin();
        tree.delete(45);

        tree.insert(19);
        tree.insert(10);
        tree.insert(45);
        tree.insert(5);
        tree.insert(16);

        System.out.println(tree.size() == 5);

        System.out.println(false == tree.search(8));
        System.out.println(true == tree.search(45));
        System.out.println(true == tree.search(10));
        System.out.println(tree.inOrder());
        System.out.println(tree.preOrder());
        System.out.println(tree.postOrder());
        System.out.println(45 == tree.max());
        System.out.println(5 == tree.min());
        System.out.println(tree.floor(19) == 19);
        System.out.println(tree.floor(18) == 16);

        System.out.println(tree.ceiling(19) == 19);
        System.out.println(tree.ceiling(20) == 45);

        System.out.println(tree.rank(18) == 3);
        System.out.println(tree.rank(29) == 4);
        tree.delMin();
        System.out.println(tree.size() == 4);
        System.out.println(tree.inOrder());
        tree.delete(19);
        System.out.println(tree.size() == 3);
        System.out.println(tree.inOrder());
        System.out.println(false == tree.search(19));
    }
}