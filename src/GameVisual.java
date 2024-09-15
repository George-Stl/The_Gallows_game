import java.util.Set;

public class GameVisual {
    private int difficulty;

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }
    public int getDifficulty(){
        return difficulty;
    }

    private static final String[] GallowsPics = {

            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "     -+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "    --+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",


            "  +---+\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  o   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  O   |\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|   |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            "      |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " /    |\n" +
            "      |\n" +
            "=========",

            "  +---+\n" +
            "  |   |\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " / \\  |\n" +
            "      |\n" +
            "========="
    };

    public GameVisual(int difficulty){
        setDifficulty(difficulty);
    }

    public void gameVisual(int counter, StringBuilder hiddenWord, String hint){
        this.gameVisual(getDifficulty(), counter, hiddenWord, hint);
    }

    public void gameVisual(int difficulty, int counter, StringBuilder hiddenWord, String hint){
        int numberOfAttempts;
        System.out.println("Подсказка к слову: " + hint);
        switch (difficulty){
            case 1:
                numberOfAttempts = 11;
                System.out.println(GallowsPics[numberOfAttempts - counter] + "\n" +
                        "Оставшееся количество попыток: " + (counter) + "\n" +
                        hiddenWord + "\n");
                break;
            case 2:
                numberOfAttempts = 8;
                System.out.println(GallowsPics[3 + numberOfAttempts - counter] + "\n" +
                        "Оставшееся количество попыток: " + (counter) + "\n" +
                        hiddenWord + "\n");
                break;
            case 3:
                numberOfAttempts = 6;
                System.out.println(GallowsPics[5 + numberOfAttempts - counter] + "\n" +
                        "Оставшееся количество попыток: " + (counter) + "\n" +
                        hiddenWord + "\n");
                break;

        }
    }

}
