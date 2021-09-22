package nz.ac.auckland.se206.team27.controller.base;

import nz.ac.auckland.se206.team27.game.Game;
import nz.ac.auckland.se206.team27.game.GameViewModel;
import nz.ac.auckland.se206.team27.resource.ScreenResource;

public abstract class GameController extends BaseController {

    protected final GameViewModel gameViewModel = new GameViewModel(Game.getInstance());

    public GameController() {

        // Populate any dynamic data required for the corresponding view
        populateViewData();
    }

    /**
     * populates the {@link ScreenResource} associated with the current
     * Controller with any dynamic data it may need.
     */
    protected abstract void populateViewData();

}
