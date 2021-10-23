package nz.ac.auckland.se206.team27.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.RoundResult;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.util.JavaFXUtil;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.ResultScreenDto;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.GUESS;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.REWARD;

/**
 * Controller associated with the {@link ScreenResource#RESULT} screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultController extends GameController {

    @FXML
    public Label labelTotalScore;

    @FXML
    public Label labelPlusScore;

    @FXML
    public Label labelResult;

    @FXML
    public Button btnNext;

    @FXML
    public Label answer;

    @FXML
    public VBox answerContainer;

    @FXML
    public Label labelEncouragement;

    @FXML
    public Node container;

    @FXML
    public VBox scoreContainer;

    private TimerTask nextRoundTimerTask;

    /**
     * Define and populate a list of messages for each different round result.
     */
    private static final Map<RoundResult, String[]> encouragingMessages = new HashMap<>();
    static {
        String[] passed = {
                "Ka pai! On the first try",
                "Tino Pai! Awesome work",
                "Great job! You smashed it",
        };

        String[] faulted = {
                "Not bad, keep it up!",
                "Well done! Perseverance is key",
                "Good job for sticking with it"
        };

        String[] failed = {
                "Give it a go next time",
                "Have a crack at it next time",
                "Go for it next time"
        };

        String[] skipped = {
                "Keep putting in the mahi, you'll get it one day!",
                "Kia Kaha! Keep going",
                "Don't give up, keep building"
        };

        encouragingMessages.put(RoundResult.FAILED, failed);
        encouragingMessages.put(RoundResult.FAULTED, faulted);
        encouragingMessages.put(RoundResult.PASSED, passed);
        encouragingMessages.put(RoundResult.SKIPPED, skipped);
    }


    /**
     * Action executed when the "Skip" button is clicked.
     */
    public void clickNext() {
        nextRoundTimerTask.cancel();

        // Load the next guess screen if there is at least one more word remaining
        if (gameViewModel.getResultScreenData().hasNextWord) {
            gameViewModel.loadNextWord();
            sceneLoader.loadScreen(GUESS);
        } else {
            sceneLoader.loadScreen(REWARD);
        }
    }

    /**
     * Transition that is played when this controller is loaded.
     */
    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Data that is populated when this controller is loaded.
     */
    @Override
    protected void populateViewData() {
        ResultScreenDto data = gameViewModel.getResultScreenData();

        if (data.isPracticeMode) {
            JavaFXUtil.toggleNodeVisibility(scoreContainer, false);
            JavaFXUtil.toggleNodeVisibility(labelPlusScore, false);
        }

        // Convert the round result to human form (i.e. result name, encouragement, audio and colour)
        ResultDisplayDto resultDisplay = getResultDisplay(data.resultFromLastRound);
        labelResult.setText(resultDisplay.resultMessage);
        labelEncouragement.setText(resultDisplay.encouragingMessage);
        answerContainer.getStyleClass().add(resultDisplay.styleClass);
        SoundManager.getInstance().playClip(resultDisplay.soundClip);

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText(String.format("(+%d points from this round)", data.scoreAddedFromLastRound));

        answer.setText(data.word);

        // Automate the button countdown from 10 seconds and change the prefix depending
        // on whether there is another word left
        setButtonCountdown((data.hasNextWord) ? "Next Word" : "Results", 10);
    }

    /**
     * Sets up a timer for automatically progressing to the next screen after
     * the number of seconds elapses.
     *
     * @param seconds The number of seconds elapsed before moving to the next screen.
     */
    private void setButtonCountdown(String buttonPrefix, int seconds) {
        nextRoundTimerTask = new TimerTask() {

            private int secondsPast = seconds;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (secondsPast-- == 0) {
                        clickNext();
                        return;
                    }

                    btnNext.setText(buttonPrefix + " (" + (secondsPast + 1) + "s)");
                });
            }
        };

        Timer nextScreenTimer = new Timer();
        nextScreenTimer.schedule(nextRoundTimerTask, 0,1000L);
    }

    /**
     * @return the corresponding display elements (audio, text etc) based on
     * the result of the round.
     */
    private static ResultDisplayDto getResultDisplay(RoundResult result) {
        // List of possible messages given the round's result
        String[] possibleMsgs = encouragingMessages.get(result);

        // Get a random encouraging message from the list of possible messages
        int randIndex = new Random().nextInt(possibleMsgs.length);
        String encouragingMsg = possibleMsgs[randIndex];

        switch (result) {
            case SKIPPED:
                return new ResultDisplayDto("Skipped",
                                            encouragingMsg,
                                            AudioResource.SKIPPED,
                                            "answer-skipped");
            case FAILED:
                return new ResultDisplayDto("Incorrect :(",
                                            encouragingMsg,
                                            AudioResource.INCORRECT,
                                            "answer-incorrect");
            // Faulted and passed both have the same styling and other attributes
            case FAULTED:
            case PASSED:
                return new ResultDisplayDto("Correct",
                                            encouragingMsg,
                                            AudioResource.CORRECT,
                                            "answer-correct");
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Data class for storing information related to displaying the result.
     */
    private static class ResultDisplayDto {

        public final String resultMessage;
        public final String encouragingMessage;
        public final AudioResource soundClip;
        public final String styleClass;

        public ResultDisplayDto(String resultMessage,
                                String encouragingMessage,
                                AudioResource soundClip,
                                String styleClass) {
            this.resultMessage = resultMessage;
            this.encouragingMessage = encouragingMessage;
            this.soundClip = soundClip;
            this.styleClass = styleClass;
        }

    }

}
