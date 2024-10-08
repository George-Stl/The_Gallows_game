package backend.academy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordList {
    private Map<Integer, List<String>> wordsMap;

    public Map<Integer, List<String>> getWordsMap(){
        return wordsMap;
    }

    public void setWordsMap(Map<Integer, List<String>> wordsMap){
        this.wordsMap = wordsMap;
    }

    //конструктор, создающий словарь, содержащий значения сложность-список слов
    public WordList() throws FileNotFoundException{
        wordsMap = new HashMap<>();
        for(int i = 1; i <= 3; i++){
            String filePath = "..\\backend_academy_2024_project_1-java-George-Stl\\src\\main\\java\\backend\\academy\\words"
                    + i + ".txt";
            try{
                wordsMap.put(i, addWordsToList(filePath));
            } catch (FileNotFoundException e) {
                System.out.println("Проблема считывания файлов .txt");
            }

        }
    }

    //метод для считывания файлов из .txt и добавления в List
    public List<String> addWordsToList(String filePath) throws FileNotFoundException {
        List<String> wordsList = new ArrayList<>(); //словарь слов с подсказками
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            wordsList.add(scanner.nextLine().trim());
        }
        scanner.close();
        return wordsList;
    }

    //из выбранной категории слов вернуть одно случайное слово с подсказкой
    public String getWordFromList(int category){
        List<String> wordList = getWordsMap().get(category);
        Collections.shuffle(wordList);
        return wordList.getFirst();
    }


}
