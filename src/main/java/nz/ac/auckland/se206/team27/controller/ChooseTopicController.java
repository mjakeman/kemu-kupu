package nz.ac.auckland.se206.team27.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

// TODO: Extend some kind of shared 'MenuController'

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ChooseTopicController extends BaseController {

    @FXML
    public ListView<String> listview;

    @FXML
    public VBox container;

    private final ObservableList<String> topicList = FXCollections.observableArrayList("Random Topic");

    @FXML
    public void initialize() {
        listview.setItems(topicList);
    }

    @Override
    public void transitionOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    public void clickBack() {
        System.out.println("Back to Menu");

        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickContinue() {
        System.out.println("Continue");
        sceneLoader.loadScreen(ScreenResource.PREVIEW_TOPIC);
    }
}
