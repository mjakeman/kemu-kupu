package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.ViewConfig;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HomeController extends BaseController {

    @FXML
    public Label title;

    @FXML
    public Pane root;

    @FXML
    public VBox container;


    @FXML
    public void initialize() {
        title.textProperty().set(ViewConfig.TITLE);
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Action executed when the "New Game" button is clicked.
     */
    public void clickNewGame() {
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    /**
     * Action executed when the "Practice" button is clicked.
     */
    public void clickPractice() {
        sceneLoader.loadScreen(ScreenResource.GUESS);
    }

    /**
     * Action executed when the "Preferences" button is clicked.
     */
    public void clickPreferences() {
        sceneLoader.loadScreen(ScreenResource.PREFERENCES);
    }

    /**
     * Action executed when the "Quit" button is clicked.
     */
    public void clickQuit() {
        closeApplication();
    }

}
