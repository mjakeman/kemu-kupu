package nz.ac.auckland.se206.team27.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PreviewTopicController extends BaseController {

    @FXML
    public Label title;

    @FXML
    public Label blurb;

    @FXML
    public VBox container;

    @FXML
    public AnchorPane image;

    @FXML
    public Hyperlink credit;

    @FXML
    public void initialize() {
        String topicName = "Topic: Engineering";
        String topicDescription = "The application of scientific and mathematical principles to practical ends such as the design, manufacture, and operation of efficient and economical structures, machines, processes, and systems.";

        title.textProperty().setValue(topicName);
        blurb.textProperty().setValue(topicDescription);

        URL imageUrl = ResourceUtil.getResourceUrl("media/University_of_Auckland_Clock_Tower.png");
        image.setStyle("-fx-background-image: url('" + imageUrl + "');");
        credit.setText("Colin Rose (CC BY 2.0)");
        credit.setOnAction(event -> App.openWebPage("https://commons.wikimedia.org/wiki/File:University_of_Auckland_Clock_Tower.jpg"));
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
