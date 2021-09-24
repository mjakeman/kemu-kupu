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

        String resultText = (data.resultFromLastRound == RoundResult.PASSED || data.resultFromLastRound == RoundResult.FAULTED) ?
                "Correct!" : (data.resultFromLastRound == RoundResult.SKIPPED) ? "Skipped" : "Incorrect :(";
        labelResult.setText(resultText);

        labelTotalScore.setText("" + data.currentScore);
        labelPlusScore.setText("" + data.scoreAddedFromLastRound);

        String btnText = (data.hasNextWord) ? "Next Word" : "See Results";
        btnNext.setText(btnText);
    }

}
