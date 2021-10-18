package nz.ac.auckland.se206.team27.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.ViewConfig;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HomeController extends MenuController {

    @FXML
    public Label title;

    @FXML
    public Pane root;

    @FXML
    public VBox container;

    @FXML
    public void initialize() {
        title.textProperty().set(ViewConfig.TITLE);
        SoundManager.getInstance().setBackgroundTrack(AudioResource.BG_TRACK);
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Action executed when the "New Game" button is clicked.
     */
    public void clickNewGame() {
        menuViewModel.setPracticeMode(false);
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    /**
     * Action executed when the "Practice" button is clicked.
     */
    public void clickPractice() {
        menuViewModel.setPracticeMode(true);
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    /**
     * Action executed when "Vote for Team 27" is clicked
     * Special addition for Project Competition Demo
     */
    public void clickVote() {
        sceneLoader.loadScreen(ScreenResource.VOTE);
    }

    /**
     * Action executed when the "Preferences" button is clicked.
     */
    public void clickPreferences() {
        sceneLoader.loadScreen(ScreenResource.PREFERENCES);
    }

    /**
     * Action executed when the "Help Manual" button is clicked.
     */
    public void clickHelp() {
        String pathToHelpManual = new File("help_manual.pdf").getAbsolutePath();
        App.openDocument(pathToHelpManual);
    }

    /**
     * Action executed when the "Quit" button is clicked.
     */
    public void clickQuit() {
        closeApplication();
    }

    @Override
    protected void populateViewData() {}

}
