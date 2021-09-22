package nz.ac.auckland.se206.team27.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PreviewTopicController extends BaseController implements Initializable {

    @FXML
    public Label title;

    @FXML
    public Label blurb;

    @FXML
    public VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String topicName = "Topic: Engineering";
        String topicDescription = "The application of scientific and mathematical principles to practical ends such as the design, manufacture, and operation of efficient and economical structures, machines, processes, and systems.";

        title.textProperty().setValue(topicName);
        blurb.textProperty().setValue(topicDescription);
    }

    @Override
    public void defaultOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    public void clickBack(ActionEvent actionEvent) {
        System.out.println("Back to Topic Selection");
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    public void clickPlay(ActionEvent actionEvent) {
        System.out.println("Play Game");
        sceneLoader.loadScreen(ScreenResource.GAME);
    }
}
