package nz.ac.auckland.se206.team27.controller;

import javafx.animation.*;
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
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
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
    public ToggleButton slowToggle;

    @FXML
    public ToggleButton normalToggle;

    @FXML
    public ToggleButton fastToggle;

    @FXML
    public Button buttonPlayWord;

    @FXML
    public Label labelTopic;

    @FXML
    public VBox body;

    @FXML
    public Button buttonSubmit;

    @FXML
    public Button buttonSkip;

    /**
     * When true, this signifies the user should not be able to press enter in the text
     * field and have it submit their guess. This should be turned on when text to speech
     * is running.
     */
    private boolean inhibitAction = true;

    /**
     * Currently selected speed to use for text-to-speech
     */
    private SpeechSpeed currentSpeechSpeed;

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

        if (inhibitAction) {
            return;
        }

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
        AnimationBuilder.buildSlideAndFadeTransition(body).play();
    }

    @Override
    protected void populateViewData() {
        GuessScreenDto data = gameViewModel.getGuessScreenData();
        labelTopic.setText(data.topic);
        labelNumbering.setText(String.format("Word %d of %d:", data.wordIndexStarting1, data.wordCount));
        labelGuessesRemaining.setText(String.format("%d guess%s remaining", data.guessesRemaining, data.guessesRemaining == 1 ? "" : "es"));

        updateToggleGroup(data.speechSpeed);
        currentSpeechSpeed = data.speechSpeed;

        if (!data.isFirstGuess) {
            inputGuess.getStyleClass().add("text-field-incorrect");
            AnimationBuilder.buildShakeTransition(inputGuess).play();
        }

        // By default, toggle buttons can be deselected. We want them to behave
        // more like radio buttons. Thanks to:
        // https://stackoverflow.com/questions/23629181/making-togglebuttons-behave-like-radiobuttons
        toggleGroupSpeed.selectedToggleProperty().addListener((value, oldToggle, newToggle) -> {

            // Ensure we always have a toggle selected
            if (newToggle == null) {
                toggleGroupSpeed.selectToggle(oldToggle);
                return;
            }

            // Selection changed, determine new speed
            SpeechSpeed speed;
            if (newToggle.equals(slowToggle)) {
                speed = SpeechSpeed.SLOW;
            } else if (newToggle.equals(fastToggle)) {
                speed = SpeechSpeed.FAST;
            } else {
                speed = SpeechSpeed.NORMAL;
            }

            gameViewModel.setSpeechSpeed(speed);
            currentSpeechSpeed = speed;
        });

        runAfterDelay(() -> inputGuess.requestFocus(), 50L);
        runAfterDelay(() -> sayWord(data.word), 1000L);
    }

    /**
     * Says a word while disabling the "Play again" button.
     */
    private void sayWord(String word) {

        buttonPlayWord.setDisable(true);
        buttonSubmit.setDisable(true);
        buttonSkip.setDisable(true);
        inhibitAction = true;

        Task<Void> task = SpeechManager.getInstance().talk(word, currentSpeechSpeed);
        buttonPlayWord.setText("Playing...");

        EventHandler<WorkerStateEvent> setEnabled = (T) -> runAfterDelay(() -> {
            buttonPlayWord.setDisable(false);
            buttonSubmit.setDisable(false);
            buttonSkip.setDisable(false);
            inhibitAction = false;

            buttonPlayWord.setText("Play word again");
        }, 200L);
        task.setOnSucceeded(setEnabled);
        task.setOnFailed(setEnabled);
    }

    /**
     * Select the correct toggle for the preferred text-to-speech speed
     * @param speed Preferred speed
     */
    private void updateToggleGroup(SpeechSpeed speed) {
        switch (speed)
        {
            case SLOW:
                toggleGroupSpeed.selectToggle(slowToggle);
                break;
            case FAST:
                toggleGroupSpeed.selectToggle(fastToggle);
                break;
            default:
                toggleGroupSpeed.selectToggle(normalToggle);
        }
    }

}
