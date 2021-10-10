package nz.ac.auckland.se206.team27.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.controls.ParticleView;
import nz.ac.auckland.se206.team27.game.Round;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.util.JavaFXUtil;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.ViewConfig;
import nz.ac.auckland.se206.team27.view.dto.EndGameScreenDto;
import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;

import java.util.HashMap;

public class EndGameController extends GameController {

    @FXML
    public Label labelTotalScore;

    @FXML
    public Label labelTopic;

    @FXML
    public Label labelTitle;

    @FXML
    public VBox container;

    @FXML
    public TableView<Round> tableView;

    @FXML
    public HBox scoreContainer;

    @FXML
    public ParticleView particleView;

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

    @FXML
    public void initialize() {
        // Need to call this manually as we override initialize()
        // in GameController.
        populateViewData();

        // Only proceed if effects are enabled
        PreferencesManager prefsManager = PreferencesManager.getInstance();
        if (!prefsManager.getUseEffects())
            return;

        // Setup Particle View
        double width = ViewConfig.WIDTH;
        double height = ViewConfig.HEIGHT;
        particleView.setWidth(width);
        particleView.setHeight(height);

        // Emit confetti on mouse click
        particleView.setOnMouseClicked(event -> {
            particleView.emit(80, event.getX(), event.getY());
        });

        // This is so mouse clicks pass through to the particle view
        container.setPickOnBounds(false);

        // Initial confetti bursts
        runAfterDelay(() -> {
            particleView.emit(80, width * 1/6, height/3);
            particleView.emit(80, width * 2/6, height/3);
            particleView.emit(80, width * 3/6, height/3);
            particleView.emit(80, width * 4/6, height/3);
            particleView.emit(80, width * 5/6, height/3);
        }, 500L);
    }

    @Override
    protected void populateViewData() {
        EndGameScreenDto data = gameViewModel.getEndGameScreenData();
        labelTitle.setText(data.isPracticeMode ? "Practice Summary" : "Game Summary");
        labelTopic.setText(data.topic);
        labelTotalScore.setText("" + data.totalScore);

        if (data.isPracticeMode) {
            JavaFXUtil.toggleNodeVisibility(scoreContainer, false);
        }

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
        guessCol.setMinWidth(170);
        guessCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            String guessesDisplay = String.join("\n", round.getGuesses());
            return new ReadOnlyObjectWrapper<>(guessesDisplay);
        });

        TableColumn<Round, Integer> timeTakenCol = new TableColumn<>("Time Taken");
        timeTakenCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            return new ReadOnlyObjectWrapper<Integer>((int)round.getDurationSeconds());
        });

        TableColumn<Round, Integer> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            return new ReadOnlyObjectWrapper<>(round.getScoreContribution());
        });

        // Thanks to: https://www.mmbyte.com/article/8864.html for the rough outline
        // on how to style table rows according to the properties of the bound item.
        tableView.setRowFactory(messageTable -> new TableRow<>() {
            @Override
            protected void updateItem(Round round, boolean empty) {
                super.updateItem(round, empty);

                if (round == null || empty) {
                    getStyleClass().clear();
                    return;
                }

                String styleClass;
                switch (round.getResult()) {
                    case FAILED:
                        styleClass = "table-row-failed";
                        break;
                    case PASSED:
                        styleClass = "table-row-passed";
                        break;
                    case FAULTED:
                        styleClass = "table-row-faulted";
                        break;
                    case SKIPPED:
                    default:
                        styleClass = "table-row-skipped";
                }

                getStyleClass().add(styleClass);
            }
        });

        tableView.setItems(items);
        tableView.getColumns().addAll(roundCol, wordCol, resultCol, guessCol, timeTakenCol, scoreCol);

        if (data.isPracticeMode) {
            // Hide score column in practice mode
            tableView.getColumns().remove(scoreCol);
        }
    }

}
