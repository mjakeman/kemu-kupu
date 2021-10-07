package nz.ac.auckland.se206.team27.view.dto;

/**
 * Data object for populating the end game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    public final String topic;
    public final int totalScore;
    public final boolean isPracticeMode;


    public EndGameScreenDto(String topic, int totalScore, boolean isPracticeMode) {
        this.topic = topic;
        this.totalScore = totalScore;
        this.isPracticeMode = isPracticeMode;
    }

}
