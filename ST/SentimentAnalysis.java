package ST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/**
 *
 * @author alexyang
 * top 5 words were: silence=7, people=6, sound=5, words=5, neon=3
 */
public class SentimentAnalysis {
    static String lyrics = 
            """
               Hello darkness, my old friend
               I've come to talk with you again
               Because a vision softly creeping
               Left its seeds while I was sleeping
               And the vision that was planted in my brain
               Still remains
               Within the sound of silence
            
               In restless dreams, I walked alone
               Narrow streets of cobblestone
               'Neath the halo of a street lamp
               I turned my collar to the cold and damp
               When my eyes were stabbed by the flash of a neon light
               That split the night
               And touched the sound of silence
            
               And in the naked light, I saw
               Ten thousand people, maybe more
               People talking without speaking
               People hearing without listening
               People writing songs that voices never shared
               And no one dared
               Disturb the sound of silence
            
               "Fools" said I, "You do not know
               Silence like a cancer grows
               Hear my words that I might teach you
               Take my arms that I might reach you"
               But my words, like silent raindrops fell
               And echoed in the wells of silence
            
               And the people bowed and prayed
               To the neon god they made
               And the sign flashed out its warning
               In the words that it was forming
               Then the sign said, "The words of the prophets are written on the subway walls
               In tenement halls"
               And whispered in the sound of silence
            """;
    
    static String preprocessing(String lyrics){
        return lyrics.
                replaceAll("\"", "")
                .replaceAll("[.]", "")
                .replaceAll(",", "")
                .replaceAll("'", "")
                .toLowerCase(Locale.ROOT)
                .replaceAll("ive", "i");
    }
    
    static List<String> tokenize(String lyrics){
        return Collections.emptyList();
    }
    
    static Map<String, Integer> analyze(String[] tokens){
        List<String> stopwords = Arrays.asList("", "a", "like", "the", "this", "where", "which", "you", "i", "that", "in", "and", "my", "was", "of", "to", "its");
        
        String[] filtered = Arrays.stream(tokens).filter(str -> ! stopwords.contains(str)).toArray(String[]::new);
                
        Map<String, Integer> result = new HashMap<>();
        for(String token : filtered){
            if(!result.containsKey(token)) result.put(token, 1);
            result.put(token, result.get(token) + 1);
        }
        return result;
    }
    
    public static void main(String[] args){
        String cleanString = preprocessing(lyrics);
        //System.out.println(cleanString);
        String[] tokens = cleanString.split("[\n ]");
        //System.out.println(Arrays.toString(tokens));
        Map<String, Integer> result = analyze(tokens);
        //System.out.println(result);
        
        List<Map.Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
        Comparator<Map.Entry<String, Integer>> valueComparator = 
                (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue());
        Collections.sort(list, valueComparator);
        System.out.println(list.reversed());
    }
}


