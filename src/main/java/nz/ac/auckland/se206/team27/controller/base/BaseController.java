package nz.ac.auckland.se206.team27.controller.base;

import javafx.application.Platform;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.view.SceneLoader;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public abstract class BaseController {

    protected final SceneLoader sceneLoader;

    public BaseController() {
        this.sceneLoader = new SceneLoader(App.getMainStage());
    }

    /**
     * Derived classes should override this to specify a default transition to
     * be played when the view is loaded.
     */
    public abstract void transitionOnEnter();

    /**
     * Closes the application.
     */
    protected void closeApplication() {
        Platform.exit();
    }

}
