import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class GameCore {
    private int counter; // количество попыток
    private String word; // отгадываемое слово
    private StringBuilder hiddenWord = new StringBuilder(); // скрытое отгадываемое слово

    public String getWord(){
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public StringBuilder getHiddenWord(){
        return hiddenWord;
    }

    public void setHiddenWord(StringBuilder hiddenWord){
        this.hiddenWord = hiddenWord;
    }

    public int getCounter(){
        return counter;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public GameCore(int difficulty, String word){
        GameVisual gameVisual = new GameVisual(difficulty);
        switch (difficulty){
            case 1: this.counter = 11; break; //полное количество попыток
            case 2: this.counter = 8; break; // в зависимости
            case 3: this.counter = 6; break; // от уровня сложности
        }
        this.word = word;
        hiddenWord.append("_".repeat(word.length()));
    }

    //тело игры
    public void start() throws IOException {
        int numberOfAttempts = getCounter(); //переменная управления циклом
        Map<Character, Integer> letterMap = AlphabetMap.fillMap(); //карта проверит, была ли буква уже использована
        char inputChar;
        do{
            GameVisual.gameVisual(counter, hiddenWord);
            System.out.println("Введите букву: ");
            String inputLetter = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim();
            inputChar = Character.toLowerCase(inputLetter.charAt(0));
            if(inputLetter.length() != 1 || !isLatinLetter(inputChar)){
                System.out.println("Ввод некорректен, попробуйте ещё раз. (нажмите Enter)");
                Scanner pressEnter = new Scanner(System.in);
                String button = pressEnter.nextLine();
                pressEnter.close();
                continue;
            }
            else if(letterMap.get(inputChar) != 0){
                System.out.println("Данная буква уже была использована, введите другую. (нажмите Enter)");
                Scanner pressEnter = new Scanner(System.in);
                String button = pressEnter.nextLine();
                pressEnter.close();
                continue;
            } else{
                letterMap.put(inputChar, 1);
                setCounter(getCounter() - 1);
                setHiddenWord(WordContainsLetter.containsLetter(inputChar, getWord(), getHiddenWord())); //если слово содержит букву - обновляем скрытое слово
                System.out.println("Если знаете слово - введите. Если нет - нажмите \"Enter\": ");
                String guessWord = (new BufferedReader(new InputStreamReader(System.in))).readLine();
                if(guessWord.equalsIgnoreCase(word)){
                    System.out.println("Вы выиграли!");
                    setHiddenWord(new StringBuilder(guessWord));
                    break;
                }
            }
        } while (counter > 0);
        if(getHiddenWord().toString().equalsIgnoreCase(getWord())){
            System.out.println("Слово отгадано верно!");
        } else{
            System.out.println("Вам не удалось отгадать слово.");
        }


    }


    //проверяет, что введенная буква принадлежит латинскому алфавиту
    public static boolean isLatinLetter(char c) {
        return ( c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }




}
