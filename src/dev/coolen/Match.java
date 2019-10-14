package dev.coolen;

/**
 * This class contains a single match.
 * A match exists of 2 games.
 */
public class Match {
    private Player player;
    private Player opponent;

    /**
     * @param player
     * @param opponent
     */
    public Match(Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void start() {
        this.setWord(this.player);
        this.setWord(this.opponent);
    }

    private void setWord(Player participant) {
        if (participant.isHuman()) {
            
            System.out.println(String.format("%s, voer een woord in.", participant.name()));

        } else {

        }
    }
}