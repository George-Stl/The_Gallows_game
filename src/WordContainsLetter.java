import java.util.ArrayList;
import java.util.List;

public class WordContainsLetter {
    public static StringBuilder containsLetter(char letter, String word, StringBuilder hiddenWord){
        List<Integer> letterIndex = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                letterIndex.add(i);
            }
        }
        for(Integer i : letterIndex){
            hiddenWord.setCharAt(i, letter);
        }
        return hiddenWord;
    }
}
