package dev.coolen;

/**
 * This class contains the main game loop. 
 * Hangman is able to create play multiple matches.
 */
public class Hangman {

    public void initialize() {
        Boolean playing = true;

        while (playing) {
            this.printWhiteSpace(100);
            System.out.println("Welkom bij galgje!");
            System.out.println("Bij dit spel moet je het woord van de tegenstander raden.");
            System.out.println("Wanneer je het woord in minder beurten dan je tegenstander raadt, heb je gewonnen!");
            System.out.println("Allereerst kiezen we de lengte van de woorden. Er mag gekozen worden uit 10 t/m 13.");

            Integer length = this.setWordLength();
            System.out.println(String.format("De geselecteerde lengte is %s.", length));

            Player player = this.createPlayer("Speler 1, voer je naam in.", "CPU - James");
            Player opponent = this.createPlayer("Speler 2, voer je naam in.", "CPU - Gary");

            this.printWhiteSpace(50);
            Match match = new Match(player, opponent, length);

            playing = match.create();
        }
    }

    private Player createPlayer(String message, String alternativeName) {
        String name = null;

        this.printWhiteSpace(20);
        System.out.println(message);

        while (name == null) {
            String potentialName = System.console().readLine();

            if (potentialName.length() != 0) {
                name = potentialName;
            } else {
                System.out.println("Voer aljeblieft een naam in.");
            }
        }

        return name.equalsIgnoreCase("c") ? new PlayerComputer(alternativeName) : new PlayerHuman(name);
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
