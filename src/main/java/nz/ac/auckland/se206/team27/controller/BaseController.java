package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.view.SceneLoader;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.GAME;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;

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

    public void clickAgain() {
        sceneLoader.loadScreen(GAME);
    }

}
