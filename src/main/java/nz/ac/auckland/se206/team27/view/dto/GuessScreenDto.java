package nz.ac.auckland.se206.team27.view.dto;

import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

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
    public final boolean isFirstGuess;
    public final SpeechSpeed speechSpeed;
    public final boolean showHint;

    public GuessScreenDto(String topic,
                          String word,
                          int wordCount,
                          int wordIndex,
                          int guessesRemaining,
                          boolean isFirstGuess,
                          SpeechSpeed speechSpeed,
                          boolean showHint) {
        this.topic = topic;
        this.word = word;
        this.wordCount = wordCount;
        // Incrementing wordIndex by 1 to have this ready for display (1 indexed)
        this.wordIndexStarting1 = wordIndex + 1;
        this.guessesRemaining = guessesRemaining;
        this.isFirstGuess = isFirstGuess;
        this.speechSpeed = speechSpeed;
        this.showHint = showHint;
    }

}
