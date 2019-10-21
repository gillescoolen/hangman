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
    private String[] lives = { "\n" + "\n" + "\n" + "\n" + "\n" + "|_ _", "\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|_ _", "—————\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/\n" + "|\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|   |\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  /\n" + "|____", "—————\n" + "|/  |\n" + "|   0\n" + "|  /|\\ \n" + "|  / \\ \n" + "|____" };

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
        System.out.println(String.format("Het is de beurt aan: %s", this.opponent.getName()));

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
            if (hiddenWord.equals(this.word)) {
                System.out.println("Je hebt het woord geraden!");
                System.out.println(String.format("Het woord was: %s.", this.word));
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

                this.validateGuess(guess);
            } else {
                System.out.println("Deze letter is al een keer geraden, probeer het opnieuw.");
            }
        }

        return guess;
    }

    private void validateGuess(String guess) {
        Boolean validated = false;
        List<Integer> verifiedPositions = new ArrayList<Integer>();
        Integer count = this.word.length() - this.word.replaceAll(guess, "").length();

        System.out.println(String.format("%s raadt een: %s. Op welke plaats(en) staat die letter? ", this.opponent.getName(), guess));

        while (!validated) {
            // Ask player for input if the potential guess is correct.
            List<Integer> positions = this.player.givePositions(guess, word);

            // Validate each position.
            for (Integer position : positions) {
                if (positions.size() == count && position <= this.word.length() && String.valueOf(this.word.charAt(position)).equalsIgnoreCase(guess)) {
                    verifiedPositions.add(position);
                } else {
                    System.out.println("Geef alstublieft de juiste posities.");
                    verifiedPositions.clear();
                }
            }
            validated = (verifiedPositions.size() == positions.size()) ? true : false;
        }

        this.guessedCharacters.add(guess);
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
        for (String letter : this.wrongCharacters) {
            System.out.print(letter);
        }
        System.out.println(")");

        this.showLives(this.guesses);
    }

    private void showLives(Integer guesses) {
        System.out.println(this.lives[guesses]);
    }
}
