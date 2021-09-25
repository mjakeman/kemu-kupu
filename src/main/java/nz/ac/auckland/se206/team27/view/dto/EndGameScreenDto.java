package nz.ac.auckland.se206.team27.view.dto;

/**
 * Data object for populating the end game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    public final String topic;
    public final int totalScore;


    public EndGameScreenDto(String topic, int totalScore) {
        this.topic = topic;
        this.totalScore = totalScore;
    }

}
