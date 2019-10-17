package dev.coolen;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  A game consists of a single word. This word is the one the opponent should guess.
 */
public class Game {
    private String word;
    private Scanner scanner;
    private Player challenger;
    private Integer guesses = 0;
    private String hiddenWord = "";
    private List<String> guessedCharacters = new ArrayList<String>();
    private String[] lives = { "", "\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|_ _", "—————\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/\n" + "|\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|   |\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  /\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  / \\ \n" + "|____" };

    public Game(String word, Player challenger, Scanner scanner) {
        this.word = word;
        this.challenger = challenger;
        this.scanner = scanner;

        for (int i = 0; i < word.length(); i++) {
            this.hiddenWord += "*";
        }
    }

    public Integer start() {
        System.out.println(word);

        while (this.guesses < 10 && hiddenWord.contains("*")) {
            this.printStatus();

            String guess = this.guess();
            String hiddenWord = this.generateHiddenWord(guess);

            if (this.hiddenWord.equals(hiddenWord)) {
                this.guesses++;
            } else {
                this.hiddenWord = hiddenWord;
            }

            if (hiddenWord.equals(word)) {
                System.out.println("Je hebt het woord geraden!" + word);
            }

            if (this.guesses == 10) {
                System.out.println("Je hebt het woord niet kunnen raden. Helaas!");
                this.showLives(this.guesses);
                System.out.println("");
                System.out.println(String.format("Het woord was: %s", this.word));
            }
        }

        return this.guesses;
    }

    private String guess() {
        String guess = null;

        while (guess == null) {
            String potentialGuess = this.challenger.play(this.scanner, this.guessedCharacters);

            if (potentialGuess.length() != 1) {
                System.out.println("Voer alsjeblieft een letter in.");
            } else if (!this.guessedCharacters.contains(potentialGuess)) {
                guess = potentialGuess;
                this.guessedCharacters.add(potentialGuess);
            } else {
                System.out.println("Deze letter is al een keer geraden, probeer het opnieuw.");
            }
        }

        return guess;
    }

    /**
     * Add an asterix when the guess is wrong.
     * Add the letter when the guess is right.
     * Replace the hidden word with the new one.
     */
    private String generateHiddenWord(String guess) {
        String hiddenWord = "";

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
        System.out.println("");
        System.out.println("");
        System.out.println(String.format("%s, raad een letter in het woord.", this.challenger.getName()));
        System.out.println(String.format("Progress tot nu toe: %s.", this.hiddenWord));
        System.out.print("Gebruikte letters tot nu toe: ");

        for (String letter : this.guessedCharacters) {
            System.out.print(letter);
        }

        System.out.println("");
        this.showLives(this.guesses);
        System.out.println("");
        System.out.println("");
        System.out.println(String.format("%s is aan het spelen...", this.challenger.getName()));
    }

    private void showLives(Integer guesses) {
        System.out.println(this.lives[guesses]);
    }
}
