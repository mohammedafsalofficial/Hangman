import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Hangman hangman = new Hangman();

//        Check if the letter the user guessed (guess) is one of the letters in the chosen word.
        String chosenWord = hangman.getChosenWord().toLowerCase();

//       Create a String array called display. for each letter in the chosenWord, add an " _ " to the display.
        String[] display = new String[chosenWord.length()];
        Arrays.fill(display, "_");

        hangman.setGame(chosenWord, display);

//        Create a variable called lives to keep track of the number of lives left. Set lives to equal six.
        int lives = hangman.getLives();

        hangman.runGame(lives);

    }

}
