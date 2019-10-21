package dev.coolen;

import java.security.InvalidParameterException;
import java.util.ArrayList;
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
        String guess = null;

        while (guess == null) {
            String potentialGuess = scanner.nextLine().toUpperCase();

            if (potentialGuess.length() == 1 && potentialGuess.matches("[a-zA-Z]+")) {
                guess = potentialGuess;
            } else {
                System.out.println("Voer alstublieft 1 letter in.");
            }
        }

        return guess;
    }

    public List<Integer> givePositions(String guess, String word) {
        List<Integer> positions = new ArrayList<Integer>();

        while (positions.size() == 0) {
            // Validate input. Player has been asked to enter positions.
            String potentialPositions = this.scanner.nextLine();

            if (potentialPositions.equals("*") && !word.contains(guess.toUpperCase())) {
                    return positions;
            }

            try {
                String[] potentialPositionsArray = potentialPositions.split("\\s+");

                for (String potentialPosition : potentialPositionsArray) {
                    Integer parsedPosition = Integer.parseInt(potentialPosition);

                    if (parsedPosition != 0) {
                        // Add the position to the list
                        System.out.println(potentialPosition);
                        positions.add(Integer.parseInt(potentialPosition) - 1);
                    } else {
                        throw new InvalidParameterException();
                    }
                }
            } catch (Exception e) {
                System.out.println("Geef alstublieft geldige posities.");
            }
        }

        return positions;
    }
}
