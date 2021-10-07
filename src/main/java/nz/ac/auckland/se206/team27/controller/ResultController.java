package nz.ac.auckland.se206.team27.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.util.JavaFXUtil;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.ResultScreenDto;

import java.util.Currency;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.END_GAME;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.GUESS;

/**
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
     * Action executed when the "Skip" button is clicked.
     */
    public void clickNext() {

        nextRoundTimerTask.cancel();
        if (gameViewModel.getResultScreenData().hasNextWord) {
            gameViewModel.loadNextWord();
            sceneLoader.loadScreen(GUESS);
        } else {
            sceneLoader.loadScreen(END_GAME);
        }
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        ResultScreenDto data = gameViewModel.getResultScreenData();

        String[] encouragingMsgPassed = new String[3];
        String[] encouragingMsgFaulted = new String[3];
        String[] encouragingMsgFailed = new String[3];
        String[] encouragingMsgSkipped = new String[3];
        String encouragingMsg;
        String resultMsg;
        String styleClass;
        Random passedRand = new Random();

        switch (data.resultFromLastRound)
        {
            case PASSED:
                resultMsg = "Correct!";
                encouragingMsgPassed[0] = "Ka pai! On the first try";
                encouragingMsgPassed[1] = "Tino Pai! Awesome work";
                encouragingMsgPassed[2] = "Great job! You smashed it";
                while (passedRand.nextInt() < 0) {
                    passedRand = new Random();
                }
                encouragingMsg = encouragingMsgPassed[passedRand.nextInt(3)];
                styleClass = "answer-correct";
                break;

            case FAULTED:
                resultMsg = "Correct!";
                encouragingMsgFaulted[0] = "Not bad, keep it up!";
                encouragingMsgFaulted[1] = "Well done! Perseverance is key";
                encouragingMsgFaulted[2] = "Good job for sticking with it";
                while (passedRand.nextInt() < 0) {
                    passedRand = new Random();
                }
                encouragingMsg = encouragingMsgFaulted[passedRand.nextInt(3)];
                styleClass = "answer-correct";
                break;

            case SKIPPED:
                resultMsg = "Skipped";
                encouragingMsgSkipped[0] = "Give it a go next time";
                encouragingMsgSkipped[1] = "Have a crack at it next time";
                encouragingMsgSkipped[2] = "Go for it next time";
                while (passedRand.nextInt() < 0) {
                    passedRand = new Random();
                }
                encouragingMsg = encouragingMsgSkipped[passedRand.nextInt(3)];
                styleClass = "answer-skipped";
                break;

            case FAILED:
                resultMsg = "Incorrect :(";
                encouragingMsgFailed[0] = "Keep putting in the mahi, you'll get it one day!";
                encouragingMsgFailed[1] = "Kia Kaha! Keep going";
                encouragingMsgFailed[2] = "Don't give up, keep building";
                while (passedRand.nextInt() < 0) {
                    passedRand = new Random();
                }
                encouragingMsg = encouragingMsgFailed[passedRand.nextInt(3)];
                styleClass = "answer-incorrect";
                break;

            default:
                throw new IllegalArgumentException();
        }

        if (data.isPracticeMode) {
            JavaFXUtil.toggleNodeVisibility(scoreContainer, false);
            JavaFXUtil.toggleNodeVisibility(labelPlusScore, false);
        }

        labelResult.setText(resultMsg);
        labelEncouragement.setText(encouragingMsg);
        answerContainer.getStyleClass().add(styleClass);

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText(String.format("(+%d points from this round)", data.scoreAddedFromLastRound));
        answer.setText(data.word);

        String btnText = (data.hasNextWord) ? "Next Word" : "Results";

        nextRoundTimerTask = new TimerTask() {

            private int secondsPast = 10;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (secondsPast-- == 0) {
                        clickNext();
                        cancel();
                        return;
                    }

                    btnNext.setText(btnText + " (" + (secondsPast + 1) + "s)");
                });
            }
        };

        Timer nextScreenTimer = new Timer();
        nextScreenTimer.schedule(nextRoundTimerTask, 0,1000L);

        btnNext.setText(btnText);
    }

}
