package nz.ac.auckland.se206.team27.view;

import java.util.Map;

/**
 * Data object for populating the EndGame Screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class EndGameScreenDto implements ViewDto {

    final int totalScore;
    final String topic;
    final Map<String, Integer> wordScores;

    public EndGameScreenDto(String topic, Map<String, Integer> wordScores) {
        this.topic = topic;
        this.wordScores = wordScores;

        this.totalScore = wordScores.values().stream().mapToInt(Integer::intValue).sum();
    }

}
