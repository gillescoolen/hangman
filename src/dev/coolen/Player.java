package dev.coolen;

import java.util.List;
import java.util.Scanner;


/**
 * Player
 */
public interface Player {
    String getName();
    boolean isHuman();

    String play(Scanner scanner, List<String> letters);
}