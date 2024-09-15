package backend.academy;

import lombok.experimental.UtilityClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@UtilityClass
public class Gallows_game {
    public static void main(String[] args) throws IOException, InterruptedException {
        Thread gameThread;
        while (true) {
            //переменная управления циклами
            boolean cycleControlVariable = true;

            //пользовательский выбор уровня сложности
            int difficulty;
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

            //пользовательский выбор категории слов
            cycleControlVariable = true;
            int wordCategory;
            do{
                System.out.println("Выберите категорию слов: машины (легко), ученые (средне) или столицы (сложно)." +
                    "\n" + "Введите только одно слово: машины, ученые или столицы: ");
                BufferedReader inputWord = new BufferedReader(new InputStreamReader(System.in));
                wordCategory = getWordCategory(getFirstWord(inputWord.readLine().trim().toLowerCase()));
                if(wordCategory < 0){
                    System.out.println("Вы ввели неверную категорию слов." + "\n" +
                        "Для выбора категории слов по умолчанию нажмите \"Enter\"");
                    continue;
                }
                cycleControlVariable = false;
            } while(cycleControlVariable);

            //получить слово для угадывания и подсказку
            String wordWithHint = wordList.getWordFromList(wordCategory);
            int spaceIndex = wordWithHint.indexOf(' ');
            String word = wordWithHint.substring(0, spaceIndex).toLowerCase(); //слово для угадывания
            String hint = wordWithHint.substring(spaceIndex); //подсказка к этому слову

            //ядро игры
            GameCore game = new GameCore(difficulty, word, hint);
            gameThread = new Thread(() -> {
                try {
                    game.start();
                } catch (IOException e) {
                    System.out.println("Не удалось считать пользовательский ввод внутри метода game.start()");
                }
            });
            gameThread.start();

            // ожидание завершения потока игры
            gameThread.join();

            // перезапуск игры
            System.out.println("Хотите сыграть еще раз? да/нет: ");
            BufferedReader inputPlayAgain = new BufferedReader(new InputStreamReader(System.in));
            String playAgainResponse = inputPlayAgain.readLine().trim().toLowerCase();

            if (!playAgainResponse.equalsIgnoreCase("да")) {
                break;
            }
        }
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

    //метод для выбора только одного слова из пользовательской строки (на случай, если слов несколько)
    public static String getFirstWord(String input) {
        int spaceIndex = input.indexOf(' ');
        if (spaceIndex != -1) {
            return input.substring(0, spaceIndex);
        } else {
            return input;
        }
    }

    //метод для определения выбранной пользователем категории слов или установления дефолтной
    public static int getWordCategory(String inputWord){
        return switch (inputWord) {
            case "машины" -> 1;
            case "", "ученые" -> 2;
            case "столицы" -> 3;
            default -> -1;
        };
    }

    public static void ReloadGame(String[] args) {
        System.out.println("Хотите начать заново? y/n");
    }

}
