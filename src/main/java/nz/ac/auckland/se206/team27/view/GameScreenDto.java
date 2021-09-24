package nz.ac.auckland.se206.team27.view;

/**
 * Data object for populating the game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GameScreenDto implements ViewDto {

    public final String topic;
    public final String word;
    public final int wordCount;
    public final int wordIndexStarting1;
    public final int guessesRemaining;


    public GameScreenDto(String topic, String word, int wordCount, int wordIndex, int guessesRemaining) {
        this.topic = topic;
        this.word = word;
        this.wordCount = wordCount;
        // Incrementing wordIndex by 1 to have this ready for display (1 indexed)
        this.wordIndexStarting1 = wordIndex + 1;
        this.guessesRemaining = guessesRemaining;
    }

}
