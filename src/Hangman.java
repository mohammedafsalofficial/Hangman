import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    Scanner scanner = new Scanner(System.in);

    String chosenWord;
    String[] display;
    private int lives = 6;

    //    Getting random word from words.txt file.
    public String getChosenWord() {
        int totalLines = 0;
        File file = new File("./src/words.txt");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));

            while ((br.readLine()) != null) {
                totalLines++;
            }
            br.close();;

            br = new BufferedReader(new FileReader(file));

            Random random = new Random();
            int randomInt = random.nextInt(totalLines);
            int count = 0;
            String chosenWord;
            while ((chosenWord = br.readLine()) != null) {
                if (count == randomInt) {
                    br.close();
                    return chosenWord;
                }
                count++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file.toString());
        }

        return "Puppies";
    }

    public void setGame(String chosenWord, String[] display) {
        this.chosenWord = chosenWord;
        this.display = display;
    }

//    get lives method
    public int getLives() {
        return lives;
    }

    public void runGame(int lives) {
        System.out.println("You have " + lives + " lives left");

//        If guess is not a letter in the chosenWord Then reduce 'lives' by 1.
//        If lives goes down to 0 then the game should stop, and it should print “You lose."


//        Use a while loop to let the user guess again. The loop should only stop once the user guessed all the letters
//        in the chosenWord and display has no more blanks ("_"). Then you can tell the user that they've won.
        boolean endOfGame = false;
        while (!endOfGame) {
//            Ask the user to guess a letter and assign their answer to a variable called guess.
//            Make guess lowercase.
            System.out.print("Guess a letter: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            for (int i = 0; i < chosenWord.length(); i++) {
                if (chosenWord.charAt(i) == guess) {
                    display[i] = String.valueOf(guess);
                }
            }

            for (String str : display) {
                System.out.print(str + " ");
            }

            System.out.println("\n");

            if (!chosenWord.contains(Character.toString(guess))) {
                lives--;
                System.out.println("You have " + lives + " lives left");
                System.out.println(guess +  " not in the word!!!");
            }

            boolean isBlank = Arrays.asList(display).contains("_");

            if (!isBlank) {
                endOfGame = true;
                System.out.println("The word is " + chosenWord);
                System.out.println("Congratulations! You won :)");
            }

            if (lives == 0) {
                endOfGame = true;
                System.out.println("The word is " + chosenWord);
                System.out.println("You are dead :(");
            }
        }
    }

}
