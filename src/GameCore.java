import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class GameCore {
    private int counter; // количество попыток
    private String word; // отгадываемое слово
    private String hint;
    private StringBuilder hiddenWord = new StringBuilder(); // скрытое отгадываемое слово
    private GameVisual gameVisual;

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

    private String getHint(){
        return hint;
    }

    public GameCore(int difficulty, String word, String hint){
        gameVisual = new GameVisual(difficulty);
        switch (difficulty){
            case 1: this.counter = 11; break; //полное количество попыток
            case 2: this.counter = 8; break; // в зависимости
            case 3: this.counter = 6; break; // от уровня сложности
        }
        this.word = word;
        this.hint = hint;
        this.hiddenWord.append("_".repeat(word.length()));
    }

    //тело игры
    public void start() throws IOException {

        Map<Character, Integer> letterMap = AlphabetMap.fillMap(); //карта проверит, была ли буква уже использована

        do{
            gameVisual.gameVisual(getCounter(), getHiddenWord(), getHint());
            System.out.println("Введите букву: ");
            String inputLetter = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim().toLowerCase();
            char inputChar = inputLetter.charAt(0);

            //проверка правильности ввода
            if(inputLetter.length() != 1 || !isRussianLetter(inputChar) || letterMap.get(inputChar) != 0){
                System.out.println("Ввод некорректен, попробуйте ещё раз.");
                do{
                    System.out.println("Введите букву (от \"а\" до \"я\" русского алфавита: ");
                    inputLetter = (new BufferedReader(new InputStreamReader(System.in))).readLine().trim().toLowerCase();
                    inputChar = inputLetter.charAt(0);
                    if(letterMap.get(inputChar) != 0) {
                        System.out.println("Данная буква уже была использована, введите другую.");
                    }
                }while(inputLetter.length() != 1 || !isRussianLetter(inputChar) || letterMap.get(inputChar) != 0);
            }

            letterMap.put(inputChar, 1);
            StringBuilder modifiedHiddenWord = WordContainsLetter.
                    containsLetter(inputChar, getWord(), getHiddenWord());//проверка, содержит ли слово букву. Возвращает обновленное скрытое слово
            if(modifiedHiddenWord.compareTo(getHiddenWord()) == 0){ //если скрытое слово не изменилось, значит, такой буквы нет и число попыток уменьшается
                setCounter(getCounter() - 1);
            }
            setHiddenWord(modifiedHiddenWord); //обновить скрытое слово

        } while (getCounter() > 0 && !(getHiddenWord().toString().equalsIgnoreCase(getWord())));
        if(getHiddenWord().toString().equalsIgnoreCase(getWord())){
            System.out.println("Слово отгадано верно!");
        } else {
            System.out.println("Вам не удалось отгадать слово.");
        }
        System.out.println("Загаданное слово: " + getWord());
    }

    //проверяет, что введенная буква принадлежит русскому алфавиту
    public static boolean isRussianLetter(char c) {
        return ( c >= 'а' && c <= 'я');
    }







}
