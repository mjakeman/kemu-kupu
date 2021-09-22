package nz.ac.auckland.se206.team27.view;

/**
 * Data object for populating the result screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultScreenDto implements ViewDto {

    final boolean hasNextWord;
    final String topic;
    final String word;

    final int currentScore;
    final int scoreAddedFromLastRound;

    final String encouragingMsg;
    final String definition;
    final String imgUrl;


    /**
     * Constructor with only basic information needed.
     *
     * NB: Use this for assignment 3.
     */
    // TODO: Change to initiate score
    public ResultScreenDto(boolean hasNextWord, String topic, String word) {
        this(hasNextWord, topic, word, 10, 1, null, null, null);
    }

    /**
     * Constructor with all nice-to-have elements.
     *
     * NB: Use this for final project.
     */
    public ResultScreenDto(boolean hasNextWord,
                           String topic,
                           String word,
                           int currentScore,
                           int scoreAddedFromLastRound,
                           String encouragingMsg,
                           String definition,
                           String imgUrl) {
        this.hasNextWord = hasNextWord;
        this.topic = topic;
        this.word = word;
        this.encouragingMsg = encouragingMsg;
        this.definition = definition;
        this.imgUrl = imgUrl;
        this.currentScore = currentScore;
        this.scoreAddedFromLastRound = scoreAddedFromLastRound;
    }

}
