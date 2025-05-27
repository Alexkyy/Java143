package Proj3;

/**
 *
 * @author alexyang
 */
import java.util.*;

/**
 * {@link TreeSet} implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TreeSetAutocomplete implements Autocomplete {
    /**
     * {@link NavigableSet} of added autocompletion terms.
     */
    private final NavigableSet<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public TreeSetAutocomplete() {
        elements = new TreeSet<>(CharSequence::compare);
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        //adds all into treeset
        elements.addAll(terms);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        //Looks at the words starting at prefix and after
        List<CharSequence> result = new ArrayList<>();
        for (CharSequence term : elements) {
            if (Autocomplete.isPrefixOf(prefix, term)) {
                result.add(term);
            }
        }
        return result;
    }
}