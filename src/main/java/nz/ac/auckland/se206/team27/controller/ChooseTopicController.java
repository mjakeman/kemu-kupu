package nz.ac.auckland.se206.team27.controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;

/**
 * Controller associated with the {@link ScreenResource#CHOOSE_TOPIC} screen.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ChooseTopicController extends MenuController {

    @FXML
    public ListView<String> listview;

    @FXML
    public VBox container;

    @FXML
    public Button buttonBack;

    @FXML
    public Button buttonContinue;

    /**
     * Text label for a random topic.
     */
    private final String randomTopicLabelText = "RANDOM TOPIC";


    /**
     * Action executed when the "Back" button is clicked.
     */
    public void clickBack() {
        // Reset the topic selection to null then go back home
        menuViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    /**
     * Action executed when the "Continue" button is clicked.
     */
    public void clickContinue() {
        String selected = listview.getSelectionModel().getSelectedItem();

        // Do nothing if no topic is selected
        if (selected == null) return;

        while (selected.equals(randomTopicLabelText)) {
            List<String> topicList = menuViewModel.getTopics();

            Random selectRand = new Random();
            int a = selectRand.nextInt(topicList.size());
            selected = topicList.get(a);
        }

        menuViewModel.selectTopic(selected);
        sceneLoader.loadScreen(ScreenResource.PREVIEW_TOPIC);
    }

    /**
     * Transition that is played when this controller is loaded.
     */
    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    /**
     * Data that is populated when this controller is loaded.
     */
    @Override
    protected void populateViewData() {
        ObservableList<String> topicList = FXCollections.observableArrayList(menuViewModel.getTopics());

        // Sort the topic list alphabetically
        Collections.sort(topicList);

        // Add random topic at the end of the list
        topicList.add(randomTopicLabelText);

        listview.setItems(topicList);

        // Bind enter and escape keys to continue and back buttons
        listview.setOnKeyPressed((event) -> {
            KeyCode keyPressed = event.getCode();
            if (keyPressed == KeyCode.ENTER) {
                clickContinue();
            } else if (keyPressed == KeyCode.ESCAPE) {
                clickBack();
            }
        });
    }

}
