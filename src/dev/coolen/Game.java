package dev.coolen;

import java.util.ArrayList;
import java.util.List;

/**
 *  A game consists of a single word. This word is the one the opponent should guess.
 */
public class Game {
    private String word;
    private Player player;
    private Player opponent;
    private Integer guesses = 0;
    private String hiddenWord = "";
    private List<String> guessedCharacters = new ArrayList<String>();
    private List<String> wrongCharacters = new ArrayList<String>();
    private String[] lives = { "", "\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|_ _",
            "—————\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/\n" + "|\n" + "|\n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|\n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|   |\n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  /\n" + "|____",
            "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  / \\ \n" + "|____" };

    public Game(String word, Player player, Player challenger) {
        this.word = word;
        this.player = player;
        this.opponent = challenger;

        // Set the hiddenword.
        for (int i = 0; i < word.length(); i++) {
            this.hiddenWord += ".";
        }
    }

    public Integer start() {
        System.out.println(word); // TODO: Remove this line before submission.

        while (this.guesses < 10 && hiddenWord.contains(".")) {
            this.printStatus();

            String guess = this.guess();
            String hiddenWord = this.generateHiddenWord(guess);

            // When the hiddenword hasn't changes, add faulty guess to the counter.
            if (this.hiddenWord.equals(hiddenWord)) {
                this.guesses++;
                this.wrongCharacters.add(guess);
            } else {
                // When the hiddenword has changed, set the new hiddenword.
                this.hiddenWord = hiddenWord;
            }

            // If the oppent has guessed 10 times
            if (this.guesses == 10) {
                System.out.println("Je hebt het woord niet kunnen raden. Helaas!");
                this.showLives(this.guesses);
                System.out.println(String.format("Het woord was: %s.", this.word));
            }

            // When the hiddenword equals the full word. ( When the asteriks are gone )
            if (hiddenWord.equals(word)) {
                System.out.println("Je hebt het woord geraden!" + word);
            }
        }

        return this.guesses;
    }

    private String guess() {
        String guess = null;

        while (guess == null) {
            String potentialGuess = this.opponent.play(this.guessedCharacters);

            if (potentialGuess.length() != 1) {
                System.out.println("Voer alsjeblieft een letter in.");
            } else if (!this.guessedCharacters.contains(potentialGuess)) {
                guess = potentialGuess;
                this.guessedCharacters.add(potentialGuess);
            } else {
                System.out.println("Deze letter is al een keer geraden, probeer het opnieuw.");
            }

            // Ask player for input if the potential guess is correct.
            this.player.validateGuess(guess);
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
            } else if (this.hiddenWord.charAt(i) != '.') {
                hiddenWord += this.word.charAt(i);
            } else {
                hiddenWord += ".";
            }
        }

        return hiddenWord;
    }

    private void printStatus() {
        System.out.println(this.hiddenWord);

        System.out.print(String.format("Aantal fouten: %s", this.wrongCharacters.size()));

        System.out.print(" (");
        for (String letter : this.guessedCharacters) {
            System.out.print(letter);
        }
        System.out.println(")");

        this.showLives(this.guesses);
    }

    private void showLives(Integer guesses) {
        System.out.println(this.lives[guesses]);
    }
}
