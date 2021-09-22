package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.game.GameManager;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.resource.ScreenResource.GAME;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResultController extends BaseController {

    private final GameManager gameManager = GameManager.getInstance();


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


    public ResultController() {
        populateViewData();
    }

    private void populateViewData() {
        GameScreenDto data = gameManager.getGameScreenData();
        System.out.println("Loaded data: ");
        System.out.println(data);
    }

}
