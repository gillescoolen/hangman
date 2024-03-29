package dev.coolen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class contains a single match.
 * A match exists of 2 games.
 */
public class Match {
    private Player player;
    private Player opponent;
    private Scanner scanner;
    private Integer chosenWordLength;

    public Match(Player player, Player opponent, Integer chosenWordLength, Scanner scanner) {
        this.player = player;
        this.opponent = opponent;
        this.scanner = scanner;
        this.chosenWordLength = chosenWordLength;
    }

    public Boolean create() {
        Game playerGame = this.createGame(this.player, this.opponent);
        Game opponentGame = this.createGame(this.opponent, this.player);

        Integer playerGuesses = 0;
        Integer OpponentGuesses = 0;

        Random random = new Random();

        // Choose a random game to start with.
        if (random.nextBoolean()) {
            playerGuesses = playerGame.start();
            OpponentGuesses = opponentGame.start();
        } else {
            OpponentGuesses = opponentGame.start();
            playerGuesses = playerGame.start();
        }

        if (playerGuesses > OpponentGuesses) {
            return announceWinner(this.player, playerGuesses);
        } else if (playerGuesses < OpponentGuesses) {
            return announceWinner(this.opponent, OpponentGuesses);
        } else {
            return announceWinner(null, 0);
        }
    }

    private Game createGame(Player player, Player opponent) {
        String word = null;

        if (player instanceof PlayerHuman) {
            word = readWord(player);
        } else {
            word = this.getRandomWord();
        }
        return new Game(word.toUpperCase(), player, opponent);

    }

    private boolean announceWinner(Player participant, Integer guesses) {
        if (participant == null) {
            System.out.println("Het is gelijkspel!");
        } else {
            System.out.println(participant.getName() + " heeft gewonnen met een score van: " + (11 - guesses));
        }

        System.out.println("Willen jullie nog eens spelen? Y/N");

        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("ja")) {
            return true;
        } else {
            return false;
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
            words = Files.readAllLines(new File(String.format("assets/woordenlijst_%s.txt", this.chosenWordLength)).toPath());
        } catch (IOException e) {
            System.err.println("Er is iets misgegaan met het uitlezen van de woordenlijst. Staat deze op de juiste plek ( ./assets/ )? ");
        }

        return words;
    }

    private String readWord(Player participant) {
        String word = null;

        System.out.println(String.format("%s, voer een woord in.", participant.getName()));

        while (word == null) {
            String potentialWord = scanner.nextLine();

            if (potentialWord.length() == this.chosenWordLength && potentialWord.matches("[a-zA-Z]+")) {
                word = potentialWord;
            } else {
                System.out.println(String.format("Het gekozen woord voldoet niet aan de eisen. Zorg dat het woord %s letters is. Probeer het opnieuw.", chosenWordLength));
            }
        }

        return word;
    }
}