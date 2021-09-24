package nz.ac.auckland.se206.team27.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.Game.GuessResult;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.speech.SpeedUtil;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.game.Game.GuessResult.REDO;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GuessController extends GameController {

    @FXML
    public TextField inputGuess;

    @FXML
    public Label labelNumbering;

    @FXML
    public Label labelGuessesRemaining;

    @FXML
    public ToggleGroup toggleGroupSpeed;


    /**
     * Action executed when the "Play Word" button is clicked.
     */
    public void clickPlayWord() {
        String currentWord = gameViewModel.getGameScreenData().word;
        float currentSpeed = SpeedUtil.getSpeedFromString(((ToggleButton) toggleGroupSpeed.getSelectedToggle()).getText());

        SpeechManager.getInstance().talk(currentWord, currentSpeed);
    }

    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        String guess = inputGuess.getText();
        GuessResult result = gameViewModel.submitWord(guess);

        if (result == REDO) {
            populateViewData();
            return;
        }

        sceneLoader.loadScreen(RESULT);
    }

    /**
     * Action executed when a button for a macron input is entered.
     */
    public void clickMacronButton(ActionEvent event) {
        String macronisedChar = ((Button) event.getSource()).getText();

        // TODO: Make macron input anywhere in text field based on current cursor location
        inputGuess.setText(inputGuess.getText() + macronisedChar);
    }

    /**
     * Action executed when "Give Up" button is clicked.
     */
    public void clickSkip() {
        gameViewModel.giveUpOnWord();
        sceneLoader.loadScreen(RESULT);
    }

    // TODO: Implement this
    @Override
    protected void populateViewData() {
        GameScreenDto data = gameViewModel.getGameScreenData();
        labelNumbering.setText(String.format("Word %d of %d:", data.wordIndexStarting1, data.wordCount));
        labelGuessesRemaining.setText(String.format("%d guess%s remaining", data.guessesRemaining, data.guessesRemaining == 1 ? "" : "es"));
    }

}
