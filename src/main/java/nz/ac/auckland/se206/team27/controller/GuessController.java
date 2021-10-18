package nz.ac.auckland.se206.team27.controller;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;
import nz.ac.auckland.se206.team27.util.JavaFXUtil;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.controls.HintDisplay;
import nz.ac.auckland.se206.team27.view.controls.SpeedSwitcher;
import nz.ac.auckland.se206.team27.view.dto.GuessScreenDto;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.HOME;
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
    public Button buttonPlayWord;

    @FXML
    public Label labelTopic;

    @FXML
    public VBox body;

    @FXML
    public Button buttonSubmit;

    @FXML
    public Button buttonSkip;

    @FXML
    public Button buttonQuit;

    @FXML
    public HBox hintContainer;

    @FXML
    public SpeedSwitcher speedSwitcher;

    @FXML
    public Label labelPractice;

    /**
     * When true, this signifies the user should not be able to press enter in the text
     * field and have it submit their guess. This should be turned on when text to speech
     * is running.
     */
    private boolean inhibitAction = true;

    /**
     * Action executed when the "Play Word" button is clicked.
     */
    public void clickPlayWord() {
        String currentWord = gameViewModel.getGuessScreenData().word;
        sayWord(currentWord);
    }

    /**
     * Action executed when the "Quit" button is clicked.
     */
    public void clickQuit() {
        sceneLoader.loadScreen(HOME);
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

        // Clear the text input field
        inputGuess.setText("");

        if (redo) {
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

        if (data.isPracticeMode) {
            JavaFXUtil.toggleNodeVisibility(labelPractice, true);
        }

        // Automatically update the global speech speed preference whenever
        // our speed switcher control is changed.
        PreferencesManager prefsManager = PreferencesManager.getInstance();
        speedSwitcher.setSpeechSpeed(prefsManager.getSpeechSpeed());
        prefsManager.speechSpeedProperty.bind(speedSwitcher.speechSpeedProperty);

        // Hints
        ObservableList<Node> children = hintContainer.getChildren();
        children.clear();

        HintDisplay hint = new HintDisplay(data.word, data.hints, data.showHint);
        children.addAll(hint.getNodes());

        inputGuess.textProperty().addListener((observable, oldValue, newValue) -> {
            // Replace the word inside the hint display with updated values when the input changes
            hint.overrideWithUserInput(newValue);
        });

        // Incorrect Guess
        if (!data.isFirstGuess) {
            inputGuess.getStyleClass().add("text-field-incorrect");
            AnimationBuilder.buildShakeTransition(inputGuess).play();
        }

        runAfterDelay(() -> inputGuess.requestFocus(), 50L);
        runAfterDelay(() -> sayWord(data.word), 500L);
    }

    /**
     * Says a word while disabling the "Play again" button.
     */
    private void sayWord(String word) {

        buttonPlayWord.setDisable(true);
        buttonSubmit.setDisable(true);
        buttonSkip.setDisable(true);
        inhibitAction = true;

        SpeechSpeed speed = PreferencesManager.getInstance().getSpeechSpeed();

        Task<Void> task = SpeechManager.getInstance().talk(word, speed);
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

}
