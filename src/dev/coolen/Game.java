package dev.coolen;

/**
 *  A game consists of a single word. This word is the one the opponent should guess.
 */
public class Game {
    private String word;
    private Integer guesses;

    public Game(String word) {
        this.word = word;
        this.guesses = null;
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

    
}
