package nz.ac.auckland.se206.team27.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.EndGameScreenDto;

public class EndGameController extends GameController {

    @FXML
    public Label labelTotalScore;

    @FXML
    public Label labelTitle;

    @FXML
    public VBox container;

    public void clickHome() {
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickPlayAgain() {
        gameViewModel.playAgain();
        sceneLoader.loadScreen(ScreenResource.GUESS);
    }

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildSlideAndFadeTransition(container).play();
    }

    @Override
    protected void populateViewData() {
        EndGameScreenDto data = gameViewModel.getEndGameScreenData();
        labelTitle.setText(data.topic);
        labelTotalScore.setText("" + data.totalScore);
    }

}
