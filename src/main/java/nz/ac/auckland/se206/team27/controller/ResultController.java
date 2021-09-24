package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.RoundResult;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;
import nz.ac.auckland.se206.team27.view.dto.ResultScreenDto;

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
    public Label encouragement;

    @FXML
    public VBox container;

    /**
     * Action executed when the "Skip" button is clicked.
     */
    public void clickNext() {
        if (gameViewModel.getResultScreenData().hasNextWord) {
            gameViewModel.loadNextWord();
            sceneLoader.loadScreen(GUESS);
        } else {
            sceneLoader.loadScreen(END_GAME);
        }
    }

    @Override
    public void transitionOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        ResultScreenDto data = gameViewModel.getResultScreenData();

        switch (data.resultFromLastRound)
        {
            case FAULTED:
            case PASSED:
            {
                labelResult.setText("Correct!");
                answerContainer.getStyleClass().add("answer-correct");
                encouragement.setText("Keep up the good work!");
                break;
            }

            case SKIPPED:
            {
                labelResult.setText("Skipped");
                answerContainer.getStyleClass().add("answer-skipped");
                encouragement.setText("Give it a go next time!");
                break;
            }

            case FAILED:
            {
                labelResult.setText("Incorrect :(");
                encouragement.setText("If at first you don't succeed... try, try, try again.");
                answerContainer.getStyleClass().add("answer-incorrect");
            }
        }

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText(String.format("(+%d points from this round)", data.scoreAddedFromLastRound));
        answer.setText(data.word);

        String btnText = (data.hasNextWord) ? "Next Word" : "See Results";
        btnNext.setText(btnText);
    }

}
