package nz.ac.auckland.se206.team27.view.dto;

import java.util.Map;

/**
 * Data object for populating the end game screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    public final int totalScore;
    public final String topic;
    public final Map<String, Integer> wordScores;


    public EndGameScreenDto(String topic, Map<String, Integer> wordScores) {
        this.topic = topic;
        this.wordScores = wordScores;

        this.totalScore = wordScores.values().stream().mapToInt(Integer::intValue).sum();
    }

}
