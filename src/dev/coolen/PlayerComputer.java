package dev.coolen;

/**
 * PlayerComputer represents a computer.
 */
public class PlayerComputer implements Player {

    private String name;

    /**
     * @param name
     */
    public PlayerComputer(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        // Do computer stuff
    }

    @Override
    public String name() {
        return this.name;
    }


    @Override
    public boolean isHuman() {
        return false;
    }
}
