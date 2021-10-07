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
