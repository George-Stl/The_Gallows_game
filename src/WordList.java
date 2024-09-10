import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordList {
    Map<Integer, List<String>> wordsMap;

    //конструктор, создающий словарь, содержащий значения сложность-список слов
    public WordList() throws FileNotFoundException{
        wordsMap = new HashMap<>();
        for(int i = 1, j = 1; i <= 3; i++){
            List<String> wordsList = new ArrayList<>();
            String filePath = "D:\\Backend_Academy_T-Bank\\gallows_game\\src\\words.txt";
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (j <= i*10){
                wordsList.add(scanner.nextLine());
                j++;
            }
            wordsMap.put(i, wordsList);
        }
    }

    public String getWordFromList(int category){

    }


}
