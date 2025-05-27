package Proj3;

/**
 *
 * @author alexyang
 */
import java.util.Arrays;
import java.util.List;

public class TestAutocomplete {
    public static void main(String[] args) {
        if (!testAllMatches1(new TernarySearchTreeAutocomplete())) {
            System.out.println("FAIL TernarySearchTreeAutocomplete testAllMatches1 ");
            return;
        }

        if (!testAllMatches1(new SequentialSearchAutocomplete())) {
            System.out.println("FAIL SequentialSearchAutocomplete testAllMatches1 ");
            return;
        }

        if (!testAllMatches1(new TreeSetAutocomplete())) {
            System.out.println("FAIL TreeSetAutocomplete testAllMatches1 ");
            return;
        }

        if (!testAllMatches2(new TernarySearchTreeAutocomplete())) {
            System.out.println("FAIL TernarySearchTreeAutocomplete testAllMatches2 ");
            return;
        }

        if (!testAllMatches2(new SequentialSearchAutocomplete())) {
            System.out.println("FAIL SequentialSearchAutocomplete testAllMatches2 ");
            return;
        }

        if (!testAllMatches2(new TreeSetAutocomplete())) {
            System.out.println("FAIL TreeSetAutocomplete testAllMatches2 ");
            return;
        }

        if (!testAllMatches3(new TernarySearchTreeAutocomplete())) {
            System.out.println("FAIL TernarySearchTreeAutocomplete testAllMatches3 ");
            return;
        }

        if (!testAllMatches3(new SequentialSearchAutocomplete())) {
            System.out.println("FAIL SequentialSearchAutocomplete testAllMatches3 ");
            return;
        }

        if (!testAllMatches3(new TreeSetAutocomplete())) {
            System.out.println("FAIL TreeSetAutocomplete testAllMatches3 ");
            return;
        }

        System.out.println("Congrats you just survived the 3rd round in the Squid Game!");

    }

    static boolean testAllMatches1(Autocomplete instance) {
        List<String> words = Arrays.asList("cat", "dog", "butter", "can", "catalog", "dug", "bottom");
        instance.addAll(words);
        final List<CharSequence> matches = instance.allMatches("c");
        return matches.size() == 3 && matches.contains("can")
                && matches.contains("cat") && matches.contains("catalog");
    }

    static boolean testAllMatches2(Autocomplete instance) {
        List<String> words = Arrays.asList("cat", "dog", "butter", "can", "catalog", "dug", "bottom", "door");
        instance.addAll(words);
        final List<CharSequence> matches = instance.allMatches("d");
        return matches.size() == 3  && matches.contains("dog")
                && matches.contains("dug") && matches.contains("door");
    }

    static boolean testAllMatches3(Autocomplete instance) {
        List<String> words = Arrays.asList("cat", "dog", "butter", "can", "catalog", "dug", "bottom", "door");
        instance.addAll(words);
        final List<CharSequence> matches = instance.allMatches("b");
        return matches.size() == 2 && matches.contains("bottom") && matches.contains("butter") ;
    }
}
