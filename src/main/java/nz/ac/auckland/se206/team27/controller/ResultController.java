package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.resource.ScreenResource;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.GAME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultController extends BaseController {

    /**
     * Action executed when the "Skip" button is clicked.
     */
    public void clickSkip() {
        sceneLoader.loadScreen(GAME);
    }

    /**
     * Action executed when the "Try again" button is clicked.
     */
    public void clickTryAgain() {
        sceneLoader.loadScreen(GAME);
    }

}
