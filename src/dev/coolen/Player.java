package dev.coolen;

import java.util.List;
import java.util.Scanner;

/**
 * Player
 */
public abstract class Player {
    private String name;
    protected Scanner scanner;

    public Player(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;

    }

    public String getName() {
        return this.name;
    }

    abstract public String play(List<String> letters);

    abstract public String validateGuess(String guess);

}