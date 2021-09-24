package nz.ac.auckland.se206.team27.controller;

import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;
import nz.ac.auckland.se206.team27.view.dto.GuessScreenDto;

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

    @FXML
    public VBox body;

    /**
     * Action executed when the "Play Word" button is clicked.
     */
    public void clickPlayWord() {
        String currentWord = gameViewModel.getGuessScreenData().word;
        sayWord(currentWord);
    }

    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        String guess = inputGuess.getText();
        boolean redo = gameViewModel.makeGuess(guess);

        if (redo) {
            clickPlayWord();
            populateViewData();
            return;
        }

        SpeechManager.getInstance().silence();
        sceneLoader.loadScreen(RESULT);
    }

    /**
     * Action executed when a button for a macron input is entered.
     */
    public void clickMacronButton(ActionEvent event) {
        String macronisedChar = ((Button) event.getSource()).getText();
        inputGuess.insertText(inputGuess.getCaretPosition(), macronisedChar);
    }

    /**
     * Action executed when "Give Up" button is clicked.
     */
    public void clickSkip() {
        gameViewModel.skipCurrentWord();
        SpeechManager.getInstance().silence();
        sceneLoader.loadScreen(RESULT);
    }

    @Override
    public void transitionOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(body).play();
    }

    @Override
    protected void populateViewData() {
        GuessScreenDto data = gameViewModel.getGuessScreenData();
        labelTopic.setText(data.topic);
        labelNumbering.setText(String.format("Word %d of %d:", data.wordIndexStarting1, data.wordCount));
        labelGuessesRemaining.setText(String.format("%d guess%s remaining", data.guessesRemaining, data.guessesRemaining == 1 ? "" : "es"));

        // By default, toggle buttons can be deselected. We want them to behave
        // more like radio buttons. Thanks to:
        // https://stackoverflow.com/questions/23629181/making-togglebuttons-behave-like-radiobuttons
        toggleGroupSpeed.selectedToggleProperty().addListener((value, oldToggle, newToggle) -> {
            if (newToggle == null) {
                toggleGroupSpeed.selectToggle(oldToggle);
            }
        });

        runAfterDelay(() -> inputGuess.requestFocus(), 50L);
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
        buttonPlayWord.setText("Playing...");

        EventHandler<WorkerStateEvent> setEnabled = (T) -> runAfterDelay(() -> {
            buttonPlayWord.setDisable(false);
            buttonPlayWord.setText("Play word again");
        }, 200L);
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
