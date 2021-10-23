package nz.ac.auckland.se206.team27.controller.base;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.viewmodel.MenuViewModel;

/**
 * Controller that is inherited by all menu-adjacent screens (e.g. home, preferences and topic selection).
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public abstract class MenuController extends BaseController {

    protected MenuViewModel menuViewModel = MenuViewModel.getInstance();


    /**
     * Initialize the application with the dynamic view data.
     */
    @FXML
    public void initialize() {
        SoundManager.getInstance().setBackgroundTrack(AudioResource.BG_TRACK);
        SoundManager.getInstance().setBackgroundTrackVolume(1.0);
        populateViewData();
    }

    /**
     * populates the {@link ScreenResource} associated with the current
     * Controller with any dynamic data it may need.
     */
    protected abstract void populateViewData();

}
