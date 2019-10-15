package dev.coolen;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * PlayerComputer represents a computer player.
 */
public class PlayerComputer implements Player {
    private String name;

    /**
     * @param name
     */
    public PlayerComputer(String name) {
        this.name = name;
    }

    @Override
    public String play(Scanner scanner, List<String> letters) {
        return stupid(letters);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    // Replace with a smarter version of the guessing algorithm.
    private String stupid(List<String> letters) {
        Random random = new Random();

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(2000) + 1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        String guess = null;

        while (guess == null) {
            String potentialGuess = String.valueOf((char) (random.nextInt(26) + 'a')).toUpperCase();

            guess = (!letters.contains(potentialGuess)) ? potentialGuess : null;
        }

        return guess;
    }
}
