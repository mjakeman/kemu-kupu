package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
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
    public Label labelEncouragement;

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
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        ResultScreenDto data = gameViewModel.getResultScreenData();

        String encouragingMsg;
        String resultMsg;
        String styleClass;

        switch (data.resultFromLastRound)
        {
            case PASSED:
                resultMsg = "Correct!";
                encouragingMsg = "Ka pai! On the first try";
                styleClass = "answer-correct";
                break;

            case FAULTED:
                resultMsg = "Correct!";
                encouragingMsg = "Not bad, keep it up!";
                styleClass = "answer-correct";
                break;

            case SKIPPED:
                resultMsg = "Skipped";
                encouragingMsg = "Give it a go next time!";
                styleClass = "answer-skipped";
                break;

            case FAILED:
                resultMsg = "Incorrect :(";
                encouragingMsg = "Keep putting in the mahi, you'll get it one day!";
                styleClass = "answer-incorrect";
                break;

            default:
                throw new IllegalArgumentException();
        }

        labelResult.setText(resultMsg);
        labelEncouragement.setText(encouragingMsg);
        answerContainer.getStyleClass().add(styleClass);

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText(String.format("(+%d points from this round)", data.scoreAddedFromLastRound));
        answer.setText(data.word);

        String btnText = (data.hasNextWord) ? "Next Word" : "See Results";
        btnNext.setText(btnText);
    }

}
