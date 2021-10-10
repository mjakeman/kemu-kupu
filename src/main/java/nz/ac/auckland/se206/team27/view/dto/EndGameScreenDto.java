package nz.ac.auckland.se206.team27.view.dto;

import javafx.collections.ObservableList;
import nz.ac.auckland.se206.team27.game.Round;

/**
 * Data object for populating the end game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    public final String topic;
    public final int totalScore;
    public final ObservableList<Round> rounds;
    public final boolean isPracticeMode;

    public EndGameScreenDto(String topic,
                            int totalScore,
                            ObservableList<Round> rounds,
                            boolean isPracticeMode) {
        this.topic = topic;
        this.totalScore = totalScore;
        this.rounds = rounds;
        this.isPracticeMode = isPracticeMode;
    }

}
