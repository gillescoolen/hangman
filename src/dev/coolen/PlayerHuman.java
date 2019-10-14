package dev.coolen;

public class PlayerHuman implements Player {
    /**
     * PlayerHuman represents a human player. The player has a word that the opponent should guess.
     */

    private String name;
    private Integer guesses;
    private String word;

    /**
     * @param name
     * @param guesses
     * @param word
     */
    public PlayerHuman(String name) {
        this.name = name;
        this.guesses = 0;
    }

    @Override
    public void play() {
        // TODO Auto-generated method stub
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the guesses
     */
    public Integer getGuesses() {
        return guesses;
    }

    /**
     * @param guesses the guesses to set
     */
    public void setGuesses(Integer guesses) {
        this.guesses = guesses;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }
}
