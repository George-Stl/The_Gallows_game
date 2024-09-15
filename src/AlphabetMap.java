package backend.academy;

import java.util.HashMap;
import java.util.Map;

public class AlphabetMap {
    public static Map<Character, Integer> fillMap(){
        Map<Character, Integer> letterMap = new HashMap<>();
        for (char c = 'а'; c <= 'я'; c++) {
            letterMap.put(c, 0);
        }
        return letterMap;
    }
}
