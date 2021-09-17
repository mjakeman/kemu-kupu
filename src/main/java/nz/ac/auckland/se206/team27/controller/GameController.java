package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.resource.ScreenResource;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GameController extends BaseController {

    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        getNext();
    }

    /**
     * Action executed when "Skip" button is clicked.
     */
    public void clickSkip() {
        // TODO: Do something to get the next word displayed.
    }


    private void getNext() {
        sceneLoader.loadScreen(RESULT);
    }

}
