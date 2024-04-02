import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final Random random = new Random();
        final String[] words = {"Python", "Hangman", "Skillmea"};
        final String wordGuess = selectWord(words, random);
        String hiddenWordGuess = generateHiddenWord(wordGuess);

        System.out.println("Guess the word:        " + wordGuess);
        System.out.println("Guess the hidden word: " + hiddenWordGuess);

        final int MAX_INCORRECT_GUESSES = 5;
        int incorrectGuesses = 0;

        while (incorrectGuesses < MAX_INCORRECT_GUESSES && hiddenWordGuess.contains("_")) {
            System.out.println("Enter a letter: ");
            char guess = scanLetter(scanner);

            if (wordGuess.contains(String.valueOf(guess))) {
                hiddenWordGuess = revealLetters(wordGuess, hiddenWordGuess, guess);
                System.out.println("Correct guess. Updated word: " + hiddenWordGuess);
            } else {
                incorrectGuesses++;
                System.out.println("Incorrect guess! You have " + (MAX_INCORRECT_GUESSES - incorrectGuesses) + " guesses");
            }

        }
        if (hiddenWordGuess.contains("_")) {
            System.out.println("Loose, you lose!!");
        } else {
            System.out.println("Success, you winn!!");
       }

    }

    public static String revealLetters(String wordToGuess, String hiddenWord, char letter) {
        char[] hiddenWordChars = hiddenWord.toCharArray();
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }
        return String.valueOf(hiddenWordChars);
    }

    public static char scanLetter(Scanner scanner) {
        // vraci jeden znak
        char guess;
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1) {
                    throw new Exception("Line length is not 1");
                }
                guess = line.charAt(0);
                if (!Character.isLetter(guess)) {
                    throw new Exception("Character is not a letter");
                }
                break;
            } catch ( Exception e)  {
                System.out.println("Invalid input: " + e.getMessage());

            }
        }
        return guess;
    }

    public static String selectWord(String[] words, Random random ) {
        // metoda vrací vybrane slovo
        return words[random.nextInt(words.length)];
    }
    public static String generateHiddenWord(String words) {
        // metoda vrací počet podtržítek kolik je písmen
        return "_".repeat(words.length());
    }
}