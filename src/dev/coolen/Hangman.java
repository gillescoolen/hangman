package dev.coolen;
/**
 * This class contains the main game loop. 
 * Hangman is able to create play multiple matches.
 */
public class Hangman {

    public void start() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Welkom bij galgje!");
        System.out.println("Bij dit spel moet je het woord van de tegenstander raden.");
        System.out.println("Wanneer je het woord in minder beurten dan je tegenstander raadt, heb je gewonnen!");

        System.out.println("");
        System.out.println("");
        System.out.println("");
        
    }

    private Player createPlayer() {
        System.out.println("Player, enter your name:");
        
        String playerName = System.console().readLine();
        
        if (playerName.equalsIgnoreCase("c")) {
            // Player is a robot.
            return new PlayerComputer(playerName);
        } else {
            return new PlayerHuman(playerName);
        }
    }
}
