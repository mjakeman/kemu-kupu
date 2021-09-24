package nz.ac.auckland.se206.team27.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;
import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;

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

    @FXML
    public Button buttonPlayWord;

    @FXML
    public Label labelTopic;


    /**
     * Action executed when the "Play Word" button is clicked.
     */
    public void clickPlayWord() {
        String currentWord = gameViewModel.getGameScreenData().word;
        sayWord(currentWord);
    }

    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        String guess = inputGuess.getText();
        boolean redo = gameViewModel.submitWord(guess);

        if (redo) {
            clickPlayWord();
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

    @Override
    protected void populateViewData() {
        GameScreenDto data = gameViewModel.getGameScreenData();
        labelTopic.setText(data.topic);
        labelNumbering.setText(String.format("Word %d of %d:", data.wordIndexStarting1, data.wordCount));
        labelGuessesRemaining.setText(String.format("%d guess%s remaining", data.guessesRemaining, data.guessesRemaining == 1 ? "" : "es"));

        runAfterDelay(() -> sayWord(data.word), 1000L);
    }

    /**
     * Says a word while disabling the "Play again" button.
     */
    private void sayWord(String word) {
        ToggleButton selectedToggle = (ToggleButton) toggleGroupSpeed.getSelectedToggle();
        float currentSpeed = getSpeedFromString(selectedToggle.getText());

        buttonPlayWord.setDisable(true);

        Task<Void> task = SpeechManager.getInstance().talk(word, currentSpeed);

        EventHandler<WorkerStateEvent> setEnabled = (T) -> runAfterDelay(() -> buttonPlayWord.setDisable(false), 200L);
        task.setOnSucceeded(setEnabled);
        task.setOnFailed(setEnabled);
    }

    /**
     * @return the corresponding numeric speed value based on its string value.
     */
    // TODO: Rethink this
    private static float getSpeedFromString(String speed) {
        switch (speed.toUpperCase()) {
            case "SLOW": return 0.5f;
            case "FAST": return 1.5f;
            case "NORMAL":
            default: return 1f;
        }
    }

}
