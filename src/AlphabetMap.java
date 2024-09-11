import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {
    public static Map<Character, Integer> fillMap(){
        Map<Character, Integer> letterMap = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            letterMap.put(c, 0);
        }
        return letterMap;
    }
}
