package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.SoundManager;
import nz.ac.auckland.se206.team27.controller.base.MenuController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.TopicPreviewScreenDto;

/**
 * Controller associated with the {@link ScreenResource#PREVIEW_TOPIC} screen.
 *
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

    @FXML
    public Button playBtn;


    /**
     * Action executed when the "Back" button is clicked.
     */
    public void clickBack() {
        // Reset topic selection
        menuViewModel.selectTopic(null);
        sceneLoader.loadScreen(ScreenResource.CHOOSE_TOPIC);
    }

    /**
     * Action executed when the "Play" button is clicked.
     */
    public void clickPlay() {
        menuViewModel.startGameWithCurrentTopic();
        sceneLoader.loadScreen(ScreenResource.GUESS);
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
        TopicPreviewScreenDto dto = menuViewModel.getTopicPreviewData();
        SoundManager.getInstance().setBackgroundTrackVolume(0);

        title.setText(dto.name);
        blurb.setText(dto.description);
        playBtn.setText(dto.isPractice ? "Start Practice" : "Play");

        image.setStyle("-fx-background-image: url('" + dto.image.imgUrl + "');");
        credit.setText(String.format("%s (%s)", dto.image.creator, dto.image.copyright));
        credit.setOnAction(event -> App.openDocument(dto.image.externalLink));
    }
}
