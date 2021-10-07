package nz.ac.auckland.se206.team27.view.dto;

import nz.ac.auckland.se206.team27.game.RoundResult;

/**
 * Data object for populating the result screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultScreenDto implements ViewDto {

    public final boolean hasNextWord;
    public final String topic;
    public final String word;

    public final RoundResult resultFromLastRound;

    public final int currentScore;
    public final int scoreAddedFromLastRound;

    public final String definition;
    public final String imgUrl;
    public final boolean isPracticeMode;


    /**
     * Constructor with all nice-to-have elements.
     *
     * NB: Use this for final project.
     */
    public ResultScreenDto(boolean hasNextWord,
                           String topic,
                           String word,
                           RoundResult resultFromLastRound,
                           int currentScore,
                           int scoreAddedFromLastRound,
                           boolean isPracticeMode,
                           String definition,
                           String imgUrl) {
        this.hasNextWord = hasNextWord;
        this.topic = topic;
        this.word = word;
        this.resultFromLastRound = resultFromLastRound;
        this.isPracticeMode = isPracticeMode;
        this.definition = definition;
        this.imgUrl = imgUrl;
        this.currentScore = currentScore;
        this.scoreAddedFromLastRound = scoreAddedFromLastRound;
    }

}
