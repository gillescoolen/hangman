package dev.coolen;

import java.util.List;
import java.util.Scanner;

/**
 * PlayerHuman represents a human player.
 */
public class PlayerHuman extends Player {

    public PlayerHuman(String name) {
        super(name);
    }

    public String play(Scanner scanner, List<String> letters) {
        return scanner.nextLine().toUpperCase();
    }
}
