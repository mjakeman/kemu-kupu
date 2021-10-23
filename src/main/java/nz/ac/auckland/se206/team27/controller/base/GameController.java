package nz.ac.auckland.se206.team27.controller.base;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.game.Game;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.viewmodel.GameViewModel;

/**
 * Controller that is inherited by all game-adjacent screens (e.g. guess, result and reward).
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public abstract class GameController extends BaseController {

    protected final GameViewModel gameViewModel = new GameViewModel(Game.getInstance());


    /**
     * Initialize the application with the dynamic view data.
     */
    @FXML
    public void initialize() {
        populateViewData();
        SoundManager.getInstance().clearBackgroundTrack();
    }

    /**
     * populates the {@link ScreenResource} associated with the current
     * Controller with any dynamic data it may need.
     */
    protected abstract void populateViewData();

}
