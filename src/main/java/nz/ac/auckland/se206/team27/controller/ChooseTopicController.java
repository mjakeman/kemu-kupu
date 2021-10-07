package nz.ac.auckland.se206.team27.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;

import java.util.Collections;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ChooseTopicController extends MenuController {

    @FXML
    public ListView<String> listview;

    @FXML
    public VBox container;


    public void clickBack() {
        // Reset the topic selection to null then go back home
        menuViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickContinue() {
        String selected = listview.getSelectionModel().getSelectedItem();

        // Do nothing if no topic is selected
        if (selected == null) return;

        menuViewModel.selectTopic(selected);
        sceneLoader.loadScreen(ScreenResource.PREVIEW_TOPIC);
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        ObservableList<String> topicList = FXCollections.observableArrayList(menuViewModel.getTopics());
        Collections.sort(topicList);
        listview.setItems(topicList);
    }

}
