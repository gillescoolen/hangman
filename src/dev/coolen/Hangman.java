package dev.coolen;

/**
 * This class contains the main game loop. 
 * Hangman is able to create play multiple matches.
 */
public class Hangman {

    public void initialize() {
        this.printWhiteSpace(50);
        System.out.println("Welkom bij galgje!");
        System.out.println("Bij dit spel moet je het woord van de tegenstander raden.");
        System.out.println("Wanneer je het woord in minder beurten dan je tegenstander raadt, heb je gewonnen!");
        System.out.println("Allereerst kiezen we de lengte van de woorden. Er mag gekozen worden uit 10 t/m 13.");

        Integer length = this.setWordLength();
        System.out.println(String.format("De geselecteerde lengte is %s.", length));

        this.printWhiteSpace(20);

        System.out.println("Speler 1, voer je naam in.");
        Player player = this.createPlayer();
        System.out.println(String.format("Welkom, %s!", player.getName()));

        this.printWhiteSpace(20);

        System.out.println("Speler 2, voer je naam in.");
        Player opponent = this.createPlayer();
        System.out.println(String.format("Welkom, %s!", opponent.getName()));

        this.printWhiteSpace(20);
        Match match = new Match(player, opponent, length);
        match.create();
    }

    private Player createPlayer() {
        String name = null;

        while (name == null) {
            String potentialName = System.console().readLine();

            if (potentialName.length() != 0) {
                name = potentialName;
            } else {
                System.out.println("Voer aljeblieft een naam in.");
            }
        }

        return name.equalsIgnoreCase("c") ? new PlayerComputer(name) : new PlayerHuman(name);
    }

    private Integer setWordLength() {
        Integer length = null;

        while (length == null) {

            try {
                Integer potentialLength = Integer.parseInt(System.console().readLine());

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

    private void printWhiteSpace(Integer amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println("");
        }
    }
}
