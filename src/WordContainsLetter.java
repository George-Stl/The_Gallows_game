import java.util.ArrayList;
import java.util.List;

public class WordContainsLetter {
    public static StringBuilder containsLetter(char letter, String word, StringBuilder hiddenWord){
        StringBuilder modifiedHiddenWord = new StringBuilder(hiddenWord.toString());
        List<Integer> letterIndex = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                letterIndex.add(i);
            }
        }
        for(Integer i : letterIndex){
            modifiedHiddenWord.setCharAt(i, letter);
        }

        return modifiedHiddenWord;
    }
}
