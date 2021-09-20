package nz.ac.auckland.se206.team27.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nz.ac.auckland.se206.team27.resource.ScreenResource;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String topicName = "Topic: Engineering";
        String topicDescription = "The application of scientific and mathematical principles to practical ends such as the design, manufacture, and operation of efficient and economical structures, machines, processes, and systems.";

        title.textProperty().setValue(topicName);
        blurb.textProperty().setValue(topicDescription);
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
