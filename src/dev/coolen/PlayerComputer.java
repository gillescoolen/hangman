package dev.coolen;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * PlayerComputer represents a computer player.
 */
public class PlayerComputer extends Player {

    public PlayerComputer(String name) {
        super(name);
    }

    public String play(Scanner scanner, List<String> letters) {
        Random random = new Random();
        String guess = null;

        while (guess == null) {
            String potentialGuess = String.valueOf((char) (random.nextInt(26) + 'a')).toUpperCase();

            guess = (!letters.contains(potentialGuess)) ? potentialGuess : null;
        }

        return guess;
    }
}
