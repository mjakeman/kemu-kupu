package nz.ac.auckland.se206.team27.game;

import nz.ac.auckland.se206.team27.view.HintNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
     * A list of all guesses the user has made.
     */
    private final List<String> guessList = new ArrayList<>();

    /**
     * The end result of this round.
     */
    private RoundResult result;

    /**
     * The starting time of the round in ms since epoch.
     */
    private long startTimestamp;

    /**
     * The ending time of the round (when result is released) in ms since epoch.
     */
    private long endTimestamp;

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
        guessList.add(guess);

        // Check if the guess is equal to the word
        if (word.equalsIgnoreCase(guess.trim())) {
            result = guessesMade == 1 ? PASSED : FAULTED;
            endRoundTimer();
            return false;
        }

        // Check if there are any more guesses left
        if (guessesMade >= maxGuesses) {
            result = FAILED;
            endRoundTimer();
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
        endRoundTimer();
    }

    /**
     * @return The {@link RoundResult} of ths round, or {@code null} if this
     * round is not finished.
     */
    public RoundResult getResult() {
        return result;
    }

    /**
     * Starts the round timer (for scoring purposes) if it has not already started.
     */
    public void startRoundTimer() {
        if (startTimestamp == 0) {
            startTimestamp = System.currentTimeMillis();
        }
    }

    /**
     * Ends the round timer (for scoring purposes).
     *
     * Called when a result is returned.
     */
    private void endRoundTimer() {
        endTimestamp = System.currentTimeMillis();
    }

    /**
     * @return The number of guesses remaining for this round.
     */
    public int getGuessesRemaining() {
        return maxGuesses - guessesMade;
    }

    /**
     * @return A list of all guesses made in this round.
     */
    public List<String> getGuesses() {
        return guessList;
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
     * @return the corresponding score for this round.
     *
     * @throws IllegalCallerException when the result has not ended.
     */
    public int getScoreContribution() {
        if (startTimestamp == 0 || endTimestamp == 0) throw new IllegalCallerException();

        // The maximum score allowed for a perfect game (assuming 0 seconds taken for guess and first guess correct)
        int maxScore = 1000;

        // The reduction applied as a result of delay in answering (max 500 points)
        // See 1: https://github.com/SOFTENG206-2021/assignment-3-and-project-team-27/blob/main/wiki/minutes-03-10-21.md
        float duration = (float) (endTimestamp - startTimestamp) / 1000f;
        int scoreReduction = Math.min(500, (int) (500 * (Math.log10(0.03 * duration + 0.1) + 1)));
        int scoreAfterTimeReduction = maxScore - scoreReduction;

        float guessMultiplier = getResultScoreMultiplier();

        return Math.round(scoreAfterTimeReduction * guessMultiplier);
    }

    /**
     * @return The multiplier based on the round's result.
     */
    private float getResultScoreMultiplier() {
        switch (result) {
            case PASSED: return 1f;
            case FAULTED: return 0.66667f;
            case FAILED:
            case SKIPPED:
            default: return 0f;
        }
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
     * @return a map of all hints for this round.
     */
    private Map<Integer, Character> getHints(boolean isPractice) {
        Map<Integer, Character> hints = new HashMap<>();
        hints.put(1, word.charAt(1));

        if (isPractice) {
            int wordLength = word.length();

            // The distance between any two consecutive hints (excluding punctuation)
            // TODO: Extract spacing to somewhere else related to practice game options
            int spacing = 4;

            int currentIndex = 1 + spacing;
            while (currentIndex < wordLength) {
                char currentLetter = word.charAt(currentIndex);
                if (Pattern.matches(HintNode.ALPHABET_REGEX, String.valueOf(currentLetter))) {
                    // Does this work?
                    hints.put(currentIndex, currentLetter);
                    currentIndex += spacing;
                } else {
                    currentIndex++;
                }
            }
        }

        return hints;
    }

}
