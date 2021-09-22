package nz.ac.auckland.se206.team27.controller;

import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.Game.GuessResult;
import nz.ac.auckland.se206.team27.view.GameScreenDto;

import static nz.ac.auckland.se206.team27.game.Game.GuessResult.REDO;
import static nz.ac.auckland.se206.team27.resource.ScreenResource.RESULT;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class GuessController extends GameController {

    /**
     * Action executed when "Submit" button is clicked.
     */
    public void clickSubmit() {
        GuessResult result = gameViewModel.submitWord("test");

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
        gameViewModel.giveUpOnWord();
        sceneLoader.loadScreen(RESULT);
    }

    // TODO: Implement this
    @Override
    protected void populateViewData() {
        GameScreenDto data = gameViewModel.getGameScreenData();
        System.out.println("Loaded data: ");
        System.out.println(data);
    }

}
