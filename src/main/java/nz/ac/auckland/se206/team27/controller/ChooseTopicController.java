package nz.ac.auckland.se206.team27.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ChooseTopicController extends MenuController {

    @FXML
    public ListView<String> listview;

    @FXML
    public VBox container;


    public void clickBack() {
        wordListViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickContinue() {
        String selected = listview.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        wordListViewModel.selectTopic(selected);
        sceneLoader.loadScreen(ScreenResource.PREVIEW_TOPIC);
    }

    @Override
    public void transitionOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        ObservableList<String> topicList = FXCollections.observableArrayList(wordListViewModel.getTopics());
        listview.setItems(topicList);
    }

}
