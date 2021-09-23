package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.GAME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultController extends GameController {

    /**
     * Action executed when the "Skip" button is clicked.
     */
    public void clickNext() {
        sceneLoader.loadScreen(GAME);
    }

    /**
     * Action executed when the "Try again" button is clicked.
     */
    public void clickTryAgain() {
        sceneLoader.loadScreen(GAME);
    }


    // TODO: Implement this
    @Override
    protected void populateViewData() {
        GameScreenDto data = gameViewModel.getGameScreenData();
        System.out.println("Loaded data: ");
        System.out.println(data);
    }

}
