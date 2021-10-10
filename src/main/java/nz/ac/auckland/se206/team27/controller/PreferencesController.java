package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
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

    public void initialize() {
        PreferencesManager prefsManager = PreferencesManager.getInstance();

        speedSwitcher.setSpeechSpeed(prefsManager.getSpeechSpeed());
        prefsManager.speechSpeedProperty.bind(speedSwitcher.speechSpeedProperty);

        colourModeSwitcher.setState(prefsManager.getColourblindMode());
        prefsManager.colourblindModeProperty.bind(colourModeSwitcher.stateProperty);

        useEffectsSwitcher.setState(prefsManager.getUseEffects());
        prefsManager.useEffectsProperty.bind(useEffectsSwitcher.stateProperty);
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

}
