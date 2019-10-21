package dev.coolen;

import java.util.List;
import java.util.Scanner;

/**
 * PlayerHuman represents a human player.
 */
public class PlayerHuman extends Player {

    public PlayerHuman(String name, Scanner scanner) {
        super(name, scanner);
    }

    public String play(List<String> letters) {
        return scanner.nextLine().toUpperCase();
    }

    public String validateGuess(String guess) {
        System.out.println();
        return "";
    }
}
