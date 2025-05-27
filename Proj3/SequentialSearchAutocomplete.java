package Proj3;

/**
 *
 * @author alexyang
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sequential search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class SequentialSearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> elements;

    /**
     * Constructs an empty instance.
     */
    public SequentialSearchAutocomplete() {
        this.elements = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        //adds all word into the elemenst list
        this.elements.addAll(terms);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        //searches if the list has a word that starts with the giuven prefix
        //if it does it adds to result list. 
        List<CharSequence> result = new ArrayList<>();
        for (CharSequence term : elements) {
            if (Autocomplete.isPrefixOf(prefix, term)) {
                result.add(term);
            }
        }
        return result;
    }
}
