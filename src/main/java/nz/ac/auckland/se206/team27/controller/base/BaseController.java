package nz.ac.auckland.se206.team27.controller.base;

import javafx.application.Platform;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.speech.SpeechManager;
import nz.ac.auckland.se206.team27.view.SceneLoader;

/**
 * Base controller inherited by all JavaFX controllers in this application.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public abstract class BaseController {

    protected final SceneLoader sceneLoader;


    public BaseController() {
        this.sceneLoader = new SceneLoader(App.getMainStage());
        App.getMainStage().setOnCloseRequest((event) -> closeApplication());
    }

    /**
     * Derived classes should override this to specify a default transition to
     * be played when the view is loaded.
     *
     * NB: This is purposefully not abstract. If this is not overridden, then
     * no transition is played.
     */
    public void transitionOnEnter() {}

    /**
     * Closes the application.
     */
    protected void closeApplication() {
        SpeechManager.getInstance().shutdown();
        PreferencesManager.getInstance().save();
        SoundManager.getInstance().clearBackgroundTrack();
        Platform.exit();

        // Temporary fix, bug with application not closing if game has been finished
        System.exit(0);
    }

}
