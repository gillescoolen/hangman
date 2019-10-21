package dev.coolen;

import java.util.Scanner;

/**
 * This class contains the main game loop. 
 * Hangman is able to create play multiple matches.
 */
public class Hangman {
    private Scanner scanner;

    public Hangman() {
        this.scanner = new Scanner(System.in);
    }

    public void initialize() {
        Boolean playing = true;
        
        while (playing) {
            System.out.println("Welkom bij galgje!");
            System.out.println("Bij dit spel moet je het woord van de tegenstander raden.");
            System.out.println("Allereerst kiezen we de lengte van de woorden. Er mag gekozen worden uit 10 t/m 13.");

            Integer length = this.configureWordLength();
            System.out.println(String.format("De geselecteerde lengte is %s.", length));

            Player player = this.createPlayer("Geef de naam van speler 1 (of een C voor een computer):", "CPU - James");
            Player opponent = this.createPlayer("Geef de naam van speler 2 (of een C voor een computer):", "CPU - Gary");

            playing = new Match(player, opponent, length, scanner).create();
        }

        System.out.println("Bedankt voor het spelen van Galgje.");
        System.out.println("Hopelijk tot een volgende keer!");
    }

    private Player createPlayer(String message, String alternativeName) {
        String name = null;

        System.out.println(message);

        while (name == null) {
            String potentialName = scanner.nextLine();

            if (potentialName.length() != 0) {
                name = potentialName;
            } else {
                System.out.println("Voer alsjeblieft een naam in.");
            }
        }

        return name.equalsIgnoreCase("c") ? new PlayerComputer(alternativeName, this.scanner) : new PlayerHuman(name, this.scanner);
    }

    private Integer configureWordLength() {
        Integer length = null;

        while (length == null) {

            try {
                Integer potentialLength = Integer.parseInt(scanner.nextLine());

                if (potentialLength >= 10 && potentialLength <= 13) {
                    length = potentialLength;
                } else {
                    System.out.println("Kies alsjeblieft een nummer tussen 10 t/m 13");
                }

            } catch (Exception e) {
                System.out.println("Kies alsjeblieft een nummer tussen 10 t/m 13");
            }
        }

        return length;
    }
}
