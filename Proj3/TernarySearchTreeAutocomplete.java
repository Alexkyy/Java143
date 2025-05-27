package Proj3;

/**
 *
 * @author alexyang
 */
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {

    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        //adds all to the ternary search tree. 
        for (CharSequence term : terms) {
            if (term.length() > 0) {
                overallRoot = put(overallRoot, term, 0);
            }
        }
    }

    private Node put(Node x, CharSequence word, int d) {
        //puts the word into ternary tree
        char c = word.charAt(d);
        if (x == null) {
            x = new Node(c);
        }

        if (c < x.data) {
            x.left = put(x.left, word, d);
        } else if (c > x.data) {
            x.right = put(x.right, word, d);
        } else if (d < word.length() - 1) {
            x.mid = put(x.mid, word, d + 1);
        } else {
            x.isTerm = true;
        }

        return x;
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        //finds all the words that start with prefix
        List<CharSequence> result = new ArrayList<>();
        if (prefix.length() == 0) {
            return result;
        }

        Node node = getPrefixNode(overallRoot, prefix, 0);
        if (node == null) {
            return result;
        }

        if (node.isTerm) {
            result.add(prefix.toString());
        }

        collect(node.mid, new StringBuilder(prefix), result);
        return result;
    }

    private Node getPrefixNode(Node x, CharSequence prefix, int d) {
        //checks characters and navigates until finds node 
        //that matches final charcater of prefix
        if (x == null) {
            return null;
        }

        char c = prefix.charAt(d);
        if (c < x.data) {
            return getPrefixNode(x.left, prefix, d);
        } else if (c > x.data) {
            return getPrefixNode(x.right, prefix, d);
        } else if (d < prefix.length() - 1) {
            return getPrefixNode(x.mid, prefix, d + 1);
        } else {
            return x;
        }
    }

    private void collect(Node x, StringBuilder searchTerm, List<CharSequence> matches) {
        //collects and builds full words.
        if (x == null) {
            return;
        }

        collect(x.left, searchTerm, matches);

        searchTerm.append(x.data);
        if (x.isTerm) {
            matches.add(searchTerm.toString());
        }
        collect(x.mid, searchTerm, matches);
        searchTerm.deleteCharAt(searchTerm.length() - 1);

        collect(x.right, searchTerm, matches);
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }

}
