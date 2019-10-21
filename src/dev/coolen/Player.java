package dev.coolen;

import java.util.List;
import java.util.Scanner;

/**
 * Player
 */
public abstract class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    abstract public String play(Scanner scanner, List<String> letters);
}