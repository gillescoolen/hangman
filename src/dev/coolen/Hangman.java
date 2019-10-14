package dev.coolen;

/**
 * This class contains the main game loop. 
 * Hangman is able to create play multiple matches.
 */
public class Hangman {

    public void initialize() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Welkom bij galgje!");
        System.out.println("Bij dit spel moet je het woord van de tegenstander raden.");
        System.out.println("Wanneer je het woord in minder beurten dan je tegenstander raadt, heb je gewonnen!");

        System.out.println("");

        System.out.println("Speler 1, voer je naam in.");
        Player player = this.createPlayer();
        System.out.println(String.format("Welkom, %s!", player.getName()));

        System.out.println("");

        System.out.println("Speler 2, voer je naam in.");
        Player opponent = this.createPlayer();
        System.out.println(String.format("Welkom, %s!", opponent.getName()));

        System.out.println("");
        System.out.println("");
        System.out.println("");
        Match match = new Match(player, opponent);
        match.start();
    }

    private Player createPlayer() {
        String name = System.console().readLine();

        return name.equalsIgnoreCase("c") ? new PlayerComputer(name) : new PlayerHuman(name);
    }
}
