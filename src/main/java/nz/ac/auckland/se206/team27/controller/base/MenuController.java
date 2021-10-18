package nz.ac.auckland.se206.team27.controller.base;

import javafx.fxml.FXML;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.viewmodel.MenuViewModel;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public abstract class MenuController extends BaseController {

    protected MenuViewModel menuViewModel = MenuViewModel.getInstance();


    /**
     * Initialize the application with the dynamic view data.
     */
    // TODO: Do something about this both `MenuController` and `GameController` having this duplicated code fragment
    @FXML
    public void initialize() {
        populateViewData();
        SoundManager.getInstance().setBackgroundTrack(AudioResource.BG_TRACK);
    }

    /**
     * populates the {@link ScreenResource} associated with the current
     * Controller with any dynamic data it may need.
     */
    protected abstract void populateViewData();

}
