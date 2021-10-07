package nz.ac.auckland.se206.team27.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.game.Round;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.dto.EndGameScreenDto;

import java.util.HashMap;

public class EndGameController extends GameController {

    @FXML
    public Label labelTotalScore;

    @FXML
    public Label labelTitle;

    @FXML
    public VBox container;

    @FXML
    public TableView<Round> tableView;

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
        // labelTotalScore.setText("" + data.totalScore);

        // Clear table view
        tableView.getColumns().clear();

        ObservableList<Round> items = data.rounds;
        HashMap<Round, Integer> roundIdMap = new HashMap<Round, Integer>();

        int index = 1;
        for (Round round : items) {
            roundIdMap.put(round, index++);
        }

        TableColumn<Round, Integer> roundCol = new TableColumn<>("Round");
        roundCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            return new ReadOnlyObjectWrapper<>(roundIdMap.get(round));
        });

        TableColumn<Round, String> wordCol = new TableColumn<>("Word");
        wordCol.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getWord()));

        TableColumn<Round, String> resultCol = new TableColumn<>("Result");
        resultCol.setCellValueFactory(cellData -> {
            String result = cellData.getValue().getResult().name();

            // Convert enum case e.g. "FAULTED" to "Faulted":
            String wordCase = result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
            return new ReadOnlyObjectWrapper<>(wordCase);
        });

        TableColumn<Round, String> guessCol = new TableColumn<>("Your Guess(es)");
        guessCol.setMinWidth(200);
        guessCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            String guessesDisplay = String.join("\n", round.getGuesses());
            return new ReadOnlyObjectWrapper<>(guessesDisplay);
        });

        TableColumn<Round, Integer> timeTakenCol = new TableColumn<>("Time Taken");
        timeTakenCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            // TODO: Get time from here
            return new ReadOnlyObjectWrapper<>(0);
        });

        TableColumn<Round, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            return new ReadOnlyObjectWrapper<>(round.getScoreContribution());
        });

        tableView.setItems(items);
        tableView.getColumns().addAll(roundCol, wordCol, resultCol, guessCol, timeTakenCol, scoreCol);
    }

}
