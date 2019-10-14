package dev.coolen;

public class PlayerHuman implements Player {
    /**
     * PlayerHuman represents a human player.
     */

    private String name;

    /**
     * @param name
     */
    public PlayerHuman(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        // Ask the human to play something.
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
