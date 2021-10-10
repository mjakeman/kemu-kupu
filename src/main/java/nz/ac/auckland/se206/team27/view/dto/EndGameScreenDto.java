package nz.ac.auckland.se206.team27.view.dto;

import nz.ac.auckland.se206.team27.game.Round;

import java.util.List;

/**
 * Data object for populating the end game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    public final String topic;
    public final int totalScore;
    public final List<Round> rounds;
    public final boolean isPracticeMode;

    public EndGameScreenDto(String topic,
                            int totalScore,
                            List<Round> rounds,
                            boolean isPracticeMode) {
        this.topic = topic;
        this.totalScore = totalScore;
        this.rounds = rounds;
        this.isPracticeMode = isPracticeMode;
    }

}
