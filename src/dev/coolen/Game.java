package dev.coolen;

import java.util.ArrayList;
import java.util.List;

/**
 *  A game consists of a single word. This word is the one the opponent should guess.
 */
public class Game {
    private String word;
    private String hiddenWord = "";
    private Player challenger;
    private Integer guesses = 0;
    private List<String> guessedCharacters = new ArrayList<String>();

    public Game(String word, Player challenger) {
        this.word = word;
        this.challenger = challenger;

        for (int i = 0; i < word.length(); i++) {
            this.hiddenWord += "*";
        }
    }

    public Integer start() {
        System.out.println(word);

        while (guesses < 10 && hiddenWord.contains("*")) {
            this.printStatus();

            String guess = this.guess();
            String hiddenWord = this.generateHiddenWord(guess);            

            if (this.hiddenWord.equals(hiddenWord)) {
                guesses++;
                showLives(guesses);
            } else {
                this.hiddenWord = hiddenWord;
            }

            if (hiddenWord.equals(word)) {
                System.out.println("Je hebt het woord geraden!" + word);
            }

            if (guesses == 10) {
                System.out.println("Je hebt het woord niet kunnen raden. Helaas!");
            }
        }

        return guesses;
    }

    private String guess() {
        String guess = null;

        while (guess == null) {
            String potentialGuess = this.challenger.play();

            if (!this.guessedCharacters.contains(potentialGuess)) {
                guess = potentialGuess;
                this.guessedCharacters.add(potentialGuess);
            } else {
                System.out.println("Deze letter is al een keer geraden, probeer het opnieuw.");
            }
        }

        return guess;
    }

    private String generateHiddenWord(String guess) {
        String hiddenWord = "";

        /**
         * Add an asterix when the guess is wrong.guess
         * Add the letter when the guess is right.
         * Replace the hidden word with the new one.
         */
        for (int i = 0; i < this.word.length(); i++) {
            if (this.word.charAt(i) == guess.charAt(0)) {
                hiddenWord += guess.charAt(0);
            } else if (this.hiddenWord.charAt(i) != '*') {
                hiddenWord += this.word.charAt(i);
            } else {
                hiddenWord += "*";
            }
        }

        return hiddenWord;
    }

    private void printStatus() {
        System.out.println(hiddenWord);
        System.out.println("");
        System.out.println("");
        System.out.println(String.format("%s, raad een letter in het woord.", this.challenger.getName()));
        System.out.println(String.format("Progress tot nu toe: %s.", this.hiddenWord));
        System.out.print("Gebruikte letters tot nu toe: ");

        for (String letter : this.guessedCharacters) {
            System.out.print(letter);
        }
        
        System.out.println("");
        System.out.println("");
    }

    private void showLives(Integer lives) {
        switch (lives) {
        case 10:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  / \\ \n" + "|____");
            break;
        case 9:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  /\n" + "|____");

            break;
        case 8:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____");

            break;
        case 7:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|  /|\n" + "|\n" + "|____");

            break;
        case 6:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|   |\n" + "|\n" + "|____");

            break;
        case 5:
            System.out.println("—————\n" + "|/  |\n" + "|   0\n" + "|\n" + "|\n" + "|____");

            break;
        case 4:
            System.out.println("—————\n" + "|/\n" + "|\n" + "|\n" + "|\n" + "|____");

            break;
        case 3:
            System.out.println("—————\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|____");

            break;
        case 2:
            System.out.println("\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|_ _");

            break;
        case 1:
            System.out.println("\n" + "\n" + "\n" + "\n" + "\n" + "|_ _");
            break;
        default:
            break;
        }
    }
}
