package dev.coolen;

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

            guess = (!letters.contains(potentialGuess)) ? potentialGuess : null;
        }

        System.out.println(String.format("De computer raadt een: %s. Op welke plaats(en) staat die letter? ", guess));

        return guess;
    }

    public String validateGuess(String guess) {
        return "";
    }

}
