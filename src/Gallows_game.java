import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gallows_game {
    public static void main(String[] args) throws IOException {
        boolean cycleControlVariable = true;

        int difficulty;

        //выбор уровня сложности
        do{
            System.out.println("Введите желаемый уровень сложности (1, 2 или 3): ");
            BufferedReader inputDifficulty = new BufferedReader(new InputStreamReader(System.in));
            difficulty = getDifficultyValue(inputDifficulty.readLine());
            if(difficulty < 0){
                System.out.println("Вы ввели неверное значение." + "\n" +
                        "Для выбора уровня сложности по умолчанию нажмите \"Enter\"");
                continue;
            }
            cycleControlVariable = false;

        } while(cycleControlVariable);

        //создание списка слов
        WordList wordList = new WordList();

        String wordCategory;
        do{
            System.out.println("Выберите категорию слов: cars (легко), birds (средне) или cities (сложно)." +
                    "\n" + "Введите только одно слово: cars, birds или cities: ");
            BufferedReader inputWord = new BufferedReader(new InputStreamReader(System.in));

            wordCategory = getWordCategory(getFirstWord(inputWord.readLine().trim()));
            if(difficulty < 0){
                System.out.println("Вы ввели неверное значение." + "\n" +
                        "Для выбора уровня сложности по умолчанию нажмите \"Enter\"");
                continue;
            }
            cycleControlVariable = false;

        } while(cycleControlVariable);

        String word = wordList.getWordFromList(wordCategory);


    }


    //метод определения уровня сложности по пользовательскому вводу
    public static int getDifficultyValue(String inputDifficulty){
        String difficulty = inputDifficulty.trim();
        return switch (difficulty) {
            case "", "2" -> 2;
            case "1" -> 1;
            case "3" -> 3;
            default -> -1;
        };
    }

    //метод для выбора только одного слова из пользовательской строки (на случай, если слов несоклько)
    public static String getFirstWord(String input) {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex != -1) {
            return input.substring(0, spaceIndex);
        } else {
            return input;
        }
    }

    //метод для определения выбранной пользователем категории слов или установления дефолтной


}