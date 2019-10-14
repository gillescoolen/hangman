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

    /**
     * @param player
     * @param opponent
     */
    public Match(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void start() {
        Game playerGame = this.createGame(this.player);
        Game opponentGame = this.createGame(this.opponent);

        System.out.println(playerGame.getWord());
        System.out.println(opponentGame.getWord());
    }

    private Game createGame(Player participant) {
        // TODO: Get the max character length.
        if (participant.isHuman()) {
            System.out.println(String.format("%s, voer een woord in.", participant.getName()));
            String word = System.console().readLine();

            return new Game(word);

        } else {
            // TODO: Get the max character length.
            String word = this.getRandomWord();
            return new Game(word);
        }
    }

    private String getRandomWord() {
        List<String> words = readFile();
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private List<String> readFile() {
        List<String> words = null;

        try {
            words = Files.readAllLines(new File("woordenlijst_12.txt").toPath());
        } catch (IOException e) {
            System.err.println("Something went wrong trying to read the woordenlijst file. Are you sure it's in the right place?");
            e.printStackTrace();
        }

        return words;
    }
}