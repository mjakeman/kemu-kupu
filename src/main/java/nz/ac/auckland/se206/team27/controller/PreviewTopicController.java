package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.TopicPreviewScreenDto;

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
        menuViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    public void clickPlay() {
        menuViewModel.startGameWithCurrentTopic();
        sceneLoader.loadScreen(ScreenResource.GUESS);
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        TopicPreviewScreenDto dto = menuViewModel.getTopicPreviewData();

        title.setText(dto.name);
        blurb.setText(dto.description);

        image.setStyle("-fx-background-image: url('" + dto.image.imgUrl + "');");
        credit.setText(String.format("%s (%s)", dto.image.creator, dto.image.copyright));
        credit.setOnAction(event -> App.openWebPage(dto.image.externalLink));
    }
}
