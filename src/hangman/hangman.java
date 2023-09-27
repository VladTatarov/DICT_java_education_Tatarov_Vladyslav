package hangman;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class hangman{

    // Maximum number of incorrect guesses allowed
    private static final int MAX_HP = 8;

    // Array of words that can be guessed
    private static final String[] words = {"python", "java", "javascript", "php"};

    public static void main(String[] args) {
        displayMenu();
    }

    public static void gameProcess() {
        int userHp = MAX_HP;
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        String wordToGuess = words[randomIndex];
        ArrayList<Character> playerGuesses = new ArrayList<>();
        char[] dashes = new char[wordToGuess.length()];

        // Initialize dashes with '-' to represent unknown letters
        for (int i = 0; i < wordToGuess.length(); i++) {
            dashes[i] = '-';
        }
        System.out.println("Word to guess: " + String.valueOf(dashes));
        boolean letterGuessed;

        try (Scanner scanner = new Scanner(System.in)) {
            while (userHp > 0) {
                System.out.println("User hp = " + userHp);

                // Get the user's guess
                char userLetter = scanner.next().charAt(0);
                playerGuesses.add(userLetter);
                letterGuessed = false;

                // Check if the guessed letter is in the word
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == userLetter) {
                        dashes[i] = userLetter;
                        letterGuessed = true;
                    }
                }

                if (!letterGuessed) {
                    System.out.println("That letter doesn't appear in the word");
                    userHp -= 1;
                }

                System.out.println("Word to guess: " + String.valueOf(dashes));

                // Check if the player has won
                if (String.valueOf(dashes).equals(wordToGuess)) {
                    System.out.println("Win!\nThanks for playing!");
                    break;
                }
            }
        }

        if (userHp <= 0) {
            System.out.println("You lose!");
            System.out.println("The word to guess was: '" + wordToGuess + "'");
        }
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;

        while (menuChoice != 1 && menuChoice != 2) {
            System.out.println("GAME MENU - HANGMAN");
            System.out.println("[1] START");
            System.out.println("[2] EXIT");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                menuChoice = scanner.nextInt();

                switch (menuChoice) {
                    case 1:
                        System.out.println("Welcome to the game!");
                        gameProcess();
                        break;
                    case 2:
                        System.out.println("Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Try again!");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
