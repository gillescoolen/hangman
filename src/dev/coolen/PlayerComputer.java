package dev.coolen;

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
    public String play(Scanner scanner) {
        return stupid();
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
    private String stupid() {
        Random random = new Random();

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(2000) + 1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return String.valueOf((char) (random.nextInt(26) + 'a')).toUpperCase();
    }
}
