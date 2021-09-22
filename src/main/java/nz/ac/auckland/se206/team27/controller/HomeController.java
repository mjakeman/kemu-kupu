package nz.ac.auckland.se206.team27.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.ViewConfig;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class HomeController extends BaseController implements Initializable {

    @FXML
    public Label title;

    @FXML
    public Pane root;

    @FXML
    public VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.textProperty().set(ViewConfig.TITLE);

        // Transition Experiment
        FadeTransition fade = new FadeTransition(Duration.seconds(1.0), root);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.setCycleCount(1);

        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), container);
        scale.setFromX(1.05);
        scale.setFromY(1.05);
        scale.setToX(1.0);
        scale.setToY(1.0);

        ParallelTransition parallel = new ParallelTransition(fade, scale);
        parallel.play();
    }

    /**
     * Action executed when the "New Game" button is clicked.
     */
    public void clickNewGame() {
        System.out.println("New game!");
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    /**
     * Action executed when the "Practice" button is clicked.
     */
    public void clickPractice() {
        System.out.println("Practice!");
        sceneLoader.loadScreen(ScreenResource.GAME);
    }

    /**
     * Action executed when the "Preferences" button is clicked.
     */
    public void clickPreferences() {
        System.out.println("Preferences");
        sceneLoader.loadScreen(ScreenResource.PREFERENCES);
    }

    /**
     * Action executed when the "Quit" button is clicked.
     */
    public void clickQuit() {
        closeApplication();
    }
}
