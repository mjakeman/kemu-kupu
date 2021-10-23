package nz.ac.auckland.se206.team27.view.dto;

import java.util.Map;

/**
 * Data object for populating the guess screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GuessScreenDto implements ViewDto {

    public final String topic;
    public final String word;
    public final int wordCount;
    public final int wordIndexStarting1;
    public final int guessesRemaining;
    public final Map<Integer, Character> hints;
    public final boolean isFirstGuess;
    public final boolean showHint;
    public final boolean isPracticeMode;

    /**
     * Creates a new {@link GuessScreenDto}.
     *
     * @param topic Topic label
     * @param isPracticeMode Whether practice mode is enabled
     * @param word The word to be guessed
     * @param wordCount Number of words in game
     * @param wordIndexStarting1 Current word in game (starting from 1)
     * @param guessesRemaining Guesses remaining for the current word
     * @param hints Map of hints for this word
     * @param isFirstGuess Whether this is the user's first attempt
     * @param showHint Whether the hint should be shown
     */
    public GuessScreenDto(String topic,
                          boolean isPracticeMode,
                          String word,
                          int wordCount,
                          int wordIndexStarting1,
                          int guessesRemaining,
                          Map<Integer, Character> hints,
                          boolean isFirstGuess,
                          boolean showHint) {
        this.topic = topic;
        this.isPracticeMode = isPracticeMode;
        this.word = word;
        this.wordCount = wordCount;
        this.wordIndexStarting1 = wordIndexStarting1;
        this.guessesRemaining = guessesRemaining;
        this.hints = hints;
        this.isFirstGuess = isFirstGuess;
        this.showHint = showHint;
    }

}
