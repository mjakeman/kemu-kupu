package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.RoundResult;
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
    public Label labelEncouragement;


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
    protected void populateViewData() {
        ResultScreenDto data = gameViewModel.getResultScreenData();

        ResultText resultText = new ResultText(data.resultFromLastRound);
        labelResult.setText(resultText.resultMsg);
        labelEncouragement.setText(resultText.encouragingMsg);

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText(String.format("(+%d from last round)", data.scoreAddedFromLastRound));

        String btnText = (data.hasNextWord) ? "Next Word" : "See Results";
        btnNext.setText(btnText);
    }

    /*
     * Inner types
     */

    /**
     * Data class for representing the text displayed from a {@link RoundResult}.
     */
    private static final class ResultText {

        final public String resultMsg;
        final public String encouragingMsg;


        /**
         * Returns the corresponding encouraging message and result.
         */
        public ResultText(RoundResult result) {
            switch (result) {
                case PASSED:
                    resultMsg = "Correct!";
                    encouragingMsg = "Ka pai! On the first try";
                    break;
                case FAULTED:
                    resultMsg = "Correct!";
                    encouragingMsg = "Not bad, keep it up!";
                    break;
                case FAILED:
                    resultMsg = "Incorrect :(";
                    encouragingMsg = "Keep putting in the mahi, you'll get it one day!";
                    break;
                case SKIPPED:
                    resultMsg = "Skipped";
                    encouragingMsg = "You can always come back to it later :)";
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

}
