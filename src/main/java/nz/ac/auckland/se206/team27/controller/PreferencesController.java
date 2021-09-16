package nz.ac.auckland.se206.team27.controller;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.HOME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class PreferencesController extends BaseController {

    /**
     * Action executed when the "Back" button is clicked.
     */
    public void clickBack() {
        sceneLoader.loadScreen(HOME);
    }

}
