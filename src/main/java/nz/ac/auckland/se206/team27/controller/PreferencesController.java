package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

import java.net.URL;
import java.util.ResourceBundle;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.HOME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class PreferencesController extends BaseController {

    @FXML
    public VBox container;

    @Override
    public void defaultOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Action executed when the "Back" button is clicked.
     */
    public void clickBack() {
        sceneLoader.loadScreen(HOME);
    }
}
