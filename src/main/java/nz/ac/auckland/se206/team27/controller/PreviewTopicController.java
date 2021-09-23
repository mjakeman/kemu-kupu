package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.TopicPreviewScreenDto;
import nz.ac.auckland.se206.team27.view.TransitionBuilder;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PreviewTopicController extends MenuController {

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


    public void clickBack() {
        // Reset topic selection
        wordListViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    public void clickPlay() {
        wordListViewModel.startGameWithCurrentTopic();
        sceneLoader.loadScreen(ScreenResource.GAME);
    }

    @Override
    public void transitionOnEnter() {
        TransitionBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        TopicPreviewScreenDto dto = wordListViewModel.getTopicPreviewData();

        title.textProperty().setValue(dto.name);
        blurb.textProperty().setValue(dto.description);

        image.setStyle("-fx-background-image: url('" + dto.image.imgUrl + "');");
        credit.setText(String.format("%s (%s)", dto.image.creator, dto.image.copyright));
        credit.setOnAction(event -> App.openWebPage(dto.image.externalLink));
    }
}
