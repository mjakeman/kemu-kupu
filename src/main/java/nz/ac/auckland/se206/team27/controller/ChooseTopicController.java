package nz.ac.auckland.se206.team27.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.SceneLoader;
import nz.ac.auckland.se206.team27.view.ViewConfig;

import java.net.URL;
import java.util.ResourceBundle;

// TODO: Extend some kind of shared 'MenuController'
public class ChooseTopicController extends BaseController implements Initializable {
    @FXML
    public Label title;

    @FXML
    public ListView<String> listview;

    private final ObservableList<String> model = FXCollections.observableArrayList("Random Topic");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.textProperty().set(ViewConfig.TITLE);
        listview.setItems(model);
    }

    public void clickBack(ActionEvent actionEvent) {
        System.out.println("Back to Menu");
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickContinue(ActionEvent actionEvent) {
        System.out.println("Continue");
        sceneLoader.loadScreen(ScreenResource.PREVIEW_TOPIC);
    }
}
