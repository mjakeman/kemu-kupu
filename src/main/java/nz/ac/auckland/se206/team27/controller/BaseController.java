package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.view.SceneLoader;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class BaseController {

    protected final SceneLoader sceneLoader;


    public BaseController() {
        this.sceneLoader = new SceneLoader(App.getMainStage());
    }

    /**
     * Closes the application.
     */
    protected void closeApplication() {

    }

}
