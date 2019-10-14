package dev.coolen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

/**
 * This class contains a single match.
 * A match exists of 2 games.
 */
public class Match {
    private Player player;
    private Player opponent;
    private Integer chosenWordLength;

    public Match(Player player, Player opponent, Integer chosenWordLength) {
        this.player = player;
        this.opponent = opponent;
        this.chosenWordLength = chosenWordLength;
    }

    public void create() {
        Game playerGame = this.createGame(this.player);
        Game opponentGame = this.createGame(this.opponent);
        
        Random random = new Random();

        if (random.nextInt(1) == 0) {
            playerGame.start();
            opponentGame.start();
        } else {
            opponentGame.start();
            playerGame.start();
        }
    }

    private Game createGame(Player participant) {
        String word = null;

        if (participant.isHuman()) {
            word = readWord(participant);
        } else {
            word = this.getRandomWord();
        }

        return new Game(word.toUpperCase(), participant);
    }

    private String getRandomWord() {
        List<String> words = readFile();
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private List<String> readFile() {
        List<String> words = null;

        try {
            words = Files.readAllLines(
                    new File(String.format("assets/woordenlijst_%s.txt", this.chosenWordLength)).toPath());
        } catch (IOException e) {
            System.err.println(
                    "Something went wrong trying to read the woordenlijst file. Are you sure its in the right place?");
            e.printStackTrace();
        }

        return words;
    }

    private String readWord(Player participant) {
        String word = null;

        System.out.println(String.format("%s, voer een woord in.", participant.getName()));

        while (word == null) {
            String potentialWord = System.console().readLine();

            if (potentialWord.length() == this.chosenWordLength) {
                word = potentialWord;
            } else {
                System.out.println(String.format(
                        "Het gekozen woord voldoet niet aan de eisen. Zorg dat het woord %s characters is. Probeer het opnieuw.",
                        chosenWordLength));
            }
        }

        return word;
    }
}