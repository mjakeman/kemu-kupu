package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.textProperty().set(ViewConfig.TITLE);
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
