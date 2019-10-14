package dev.coolen;

import java.util.Random;

/**
 * PlayerComputer represents a computer.
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
    public String play() {
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

    private String stupid() {
        Random r = new Random();
        String guess = String.valueOf((char) (r.nextInt(26) + 'a')).toUpperCase();
        System.out.println(guess);
        return guess;
    }
}
