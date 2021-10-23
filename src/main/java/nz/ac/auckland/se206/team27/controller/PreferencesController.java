package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.controls.OnOffSwitcher;
import nz.ac.auckland.se206.team27.view.controls.SpeedSwitcher;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.HOME;

/**
 * Controller associated with the {@link ScreenResource#PREFERENCES} screen.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class PreferencesController extends MenuController {

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
        App.openDocument("https://www.vecteezy.com/vector-art/1265919-new-zealand-fantail-bird-set");
    }

    /**
     * Transition that is played when this controller is loaded.
     */
    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Data that is populated when this controller is loaded.
     */
    @Override
    protected void populateViewData() {
        PreferencesManager prefsManager = PreferencesManager.getInstance();

        // Set playback speed setting and bind the JavaFX toggle
        speedSwitcher.setSpeechSpeed(prefsManager.getSpeechSpeed());
        prefsManager.speechSpeedProperty.bind(speedSwitcher.speechSpeedProperty);

        // Set colourblind mode toggle setting and bind the JavaFX toggle
        boolean isColourblindMode = prefsManager.getColourblindMode();
        colourModeSwitcher.setState(isColourblindMode);
        prefsManager.colourblindModeProperty.bind(colourModeSwitcher.stateProperty);

        // Update the sidebar image depending on whether colourblind mode is enabled or not
        toggleColourblindImage(isColourblindMode);
        colourModeSwitcher.stateProperty.addListener((observable, oldVal, newVal) -> toggleColourblindImage(newVal));

        // Set effects mode toggle setting and bind the JavaFX toggle
        useEffectsSwitcher.setState(prefsManager.getUseEffects());
        prefsManager.useEffectsProperty.bind(useEffectsSwitcher.stateProperty);

        SoundManager.getInstance().setBackgroundTrack(AudioResource.BG_TRACK);
    }

    /**
     * Toggles the colourblind image as required.
     */
    private void toggleColourblindImage(boolean isColourblindMode) {
        String imgUrl = isColourblindMode
                ? "media/safe.png"
                : "media/default.png";

        image.setStyle("-fx-background-image: url('" + ResourceUtil.getResourceUrl(imgUrl).toExternalForm() + "');");
    }

}
