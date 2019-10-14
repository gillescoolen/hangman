package dev.coolen;

import java.util.Scanner;

/**
 * Player
 */
public interface Player {
    String getName();
    boolean isHuman();

    String play(Scanner scanner);
}