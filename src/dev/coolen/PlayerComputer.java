package dev.coolen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * PlayerComputer represents a computer player.
 */
public class PlayerComputer extends Player {

    public PlayerComputer(String name, Scanner scanner) {
        super(name, scanner);
    }

    public String play(List<String> letters) {
        Random random = new Random();
        String guess = null;

        while (guess == null) {
            String potentialGuess = String.valueOf((char) (random.nextInt(26) + 'a')).toUpperCase();

            // Set guess when the letter hasn't been guessed yet.
            guess = (!letters.contains(potentialGuess)) ? potentialGuess : null;
        }

        return guess;
    }

    public List<Integer> givePositions(String guess, String word) {
        List<Integer> positions = new ArrayList<Integer>();

        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).equalsIgnoreCase(guess)) {
                // When the character position is equal to the guessed character.
                positions.add(i);
            }
        }

        return positions;
    }
}