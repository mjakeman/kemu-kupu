package nz.ac.auckland.se206.team27.game;

import java.util.HashMap;
import java.util.Map;

import static nz.ac.auckland.se206.team27.game.RoundResult.FAILED;
import static nz.ac.auckland.se206.team27.game.RoundResult.FAULTED;
import static nz.ac.auckland.se206.team27.game.RoundResult.PASSED;
import static nz.ac.auckland.se206.team27.game.RoundResult.SKIPPED;

/**
 * A round encapsulates each of the turns inside a game.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class Round {

    /**
     * The word that is being tested in this round.
     */
    private final String word;

    /**
     * The maximum number of guesses allowed for this word.
     */
    private final int maxGuesses;

    /**
     * The number of guesses made in this round.
     */
    private int guessesMade = 0;

    /**
     * The end result of this round.
     */
    private RoundResult result;

    /**
     * The total score contribution of this round.
     */
    private int scoreContribution;

    /**
     * Map of hints (character at an index) to display after the first guess.
     */
    private final Map<Integer, Character> hints;


    public Round(String word, int maxGuesses, boolean isPracticeMode) {
        this.maxGuesses = maxGuesses;
        this.word = word.trim();
        this.hints = getHints(isPracticeMode);
    }

    /**
     * @return the word that is being tested in this round.
     */
    public String getWord() {
        return word;
    }

    /**
     * Makes a guess.
     *
     * @return {@code true} if the guess was incorrect, and another guess can still be made.
     */
    public boolean makeGuess(String guess) {
        assertResultNotSet();
        guessesMade++;

        // Check if the guess is equal to the word
        if (word.equalsIgnoreCase(guess.trim())) {
            result = guessesMade == 1 ? PASSED : FAULTED;
            scoreContribution = guessesMade == 1 ? 10 : 5;
            return false;
        }

        // Check if there are any more guesses left
        if (guessesMade >= maxGuesses) {
            result = FAILED;
            return false;
        }

        return true;
    }

    /**
     * Marks the current round as skipped.
     */
    public void markSkipped() {
        assertResultNotSet();
        result = SKIPPED;
    }

    /**
     * @return The {@link RoundResult} of ths round, or {@code null} if this
     * round is not finished.
     */
    public RoundResult getResult() {
        return result;
    }

    /**
     * @return The number of guesses remaining for this round.
     */
    public int getGuessesRemaining() {
        return maxGuesses - guessesMade;
    }

    /**
     * @return Whether this is the user's first guess this round.
     */
    public boolean isFirstGuess() {
        return (guessesMade == 0);
    }

    /**
     * @return The map of hints.
     */
    public Map<Integer, Character> getHints() {
        return hints;
    }

    /**
     * @throws IllegalCallerException if {@link Round#result} is already set.
     */
    private void assertResultNotSet() {
        if (result != null) {
            throw new IllegalCallerException("Cannot make a guess! Round result has already been determined");
        }
    }

    /**
     * @return The contribution to the overall score this round makes.
     */
    public int getScoreContribution() {
        return scoreContribution;
    }

    /**
     * @return a map of all hints for this round.
     */
    // TODO: Complete this for multiple hints depending on practice mode
    private Map<Integer, Character> getHints(boolean isPractice) {
        Map<Integer, Character> hints = new HashMap<>();
        hints.put(1, word.charAt(1));

        if (isPractice) {
            int wordLength = word.length();
        }

        return hints;
    }

}
