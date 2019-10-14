package dev.coolen;

import java.util.Scanner;

/**
 * PlayerHuman represents a human player.
 */
public class PlayerHuman implements Player {
    private String name;

    /** 
     * @param name
     */
    public PlayerHuman(String name) {
        this.name = name;
    }

    @Override
    public String play(Scanner scanner) {
        return scanner.nextLine().toUpperCase();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
