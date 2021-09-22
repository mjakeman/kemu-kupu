package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.game.GameManager;
import nz.ac.auckland.se206.team27.game.GameManager.SubmitResult;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.game.GameManager.SubmitResult.REDO;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GameController extends BaseController {

    private final GameManager gameManager = GameManager.getInstance();


    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        SubmitResult result = gameManager.submitWord("test");

        if (result == REDO) {
            populateViewData();
            return;
        }

        sceneLoader.loadScreen(RESULT);
    }

    /**
     * Action executed when "Give Up" button is clicked.
     */
    public void clickSkip() {
        gameManager.giveUpOnWord();
        sceneLoader.loadScreen(RESULT);
    }


    /**
     * Initialise screen data on load.
     */
    public GameController() {
        populateViewData();
    }

    /**
     * Loads the screen data to be used in the view.
     */
    private void populateViewData() {
        GameScreenDto data = gameManager.getGameScreenData();
        System.out.println("Loaded data: ");
        System.out.println(data);
    }

}
