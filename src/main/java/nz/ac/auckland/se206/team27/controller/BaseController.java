package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.Main;
import nz.ac.auckland.se206.team27.view.SceneLoader;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class BaseController {

    protected final SceneLoader sceneLoader;


    public BaseController() {
        this.sceneLoader = new SceneLoader(Main.getMainStage());
    }

    /**
     * Closes the application.
     */
    protected void closeApplication() {

    }

}
