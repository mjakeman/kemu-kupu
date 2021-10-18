package nz.ac.auckland.se206.team27.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.view.controls.OnOffSwitcher;
import nz.ac.auckland.se206.team27.view.controls.SpeedSwitcher;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.HOME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class PreferencesController extends BaseController {

    @FXML
    public Node container;

    @FXML
    public SpeedSwitcher speedSwitcher;

    @FXML
    public OnOffSwitcher colourModeSwitcher;

    @FXML
    public OnOffSwitcher useEffectsSwitcher;

    @FXML
    public AnchorPane image;

    public void initialize() {
        PreferencesManager prefsManager = PreferencesManager.getInstance();

        speedSwitcher.setSpeechSpeed(prefsManager.getSpeechSpeed());
        prefsManager.speechSpeedProperty.bind(speedSwitcher.speechSpeedProperty);

        colourModeSwitcher.setState(prefsManager.getColourblindMode());
        prefsManager.colourblindModeProperty.bind(colourModeSwitcher.stateProperty);

        useEffectsSwitcher.setState(prefsManager.getUseEffects());
        prefsManager.useEffectsProperty.bind(useEffectsSwitcher.stateProperty);

        updateSidebarImage();

        // Also update sidebar image whenever colourblind mode is enabled
        colourModeSwitcher.stateProperty.addListener((observable, oldValue, newValue) -> {
            updateSidebarImage();
        });
    }

    private void updateSidebarImage() {
        String imgUrl = PreferencesManager.getInstance().getColourblindMode()
                ? "media/safe.png"
                : "media/default.png";

        image.setStyle("-fx-background-image: url('" + ResourceUtil.getResourceUrl(imgUrl).toExternalForm() + "');");
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Action executed when the "Back" button is clicked.
     */
    public void clickBack() {
        PreferencesManager.getInstance().save();
        sceneLoader.loadScreen(HOME);
    }

    /**
     * Open the image source in the user's web browser (attribution
     * is required under the image licence).
     */
    public void clickImageCredit() {
        App.openWebPage("https://www.vecteezy.com/vector-art/1265919-new-zealand-fantail-bird-set");
    }
}
