package nz.ac.auckland.se206.team27.controller;

import javafx.animation.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.GameController;
import nz.ac.auckland.se206.team27.controls.ParticleView;
import nz.ac.auckland.se206.team27.game.Round;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.util.JavaFXUtil;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.ViewConfig;
import nz.ac.auckland.se206.team27.view.dto.EndGameScreenDto;

import java.util.function.Function;

import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;

public class EndGameController extends GameController {

    @FXML
    public Label miniScoreLabel;

    @FXML
    public Label labelTopic;

    @FXML
    public Label labelTitle;

    @FXML
    public VBox mainContainer;

    @FXML
    public TableView<Round> tableView;

    @FXML
    public HBox scoreContainer;

    @FXML
    public ParticleView particleView;

    @FXML
    public Label bigScoreLabel;

    @FXML
    public VBox bigScoreContainer;

    public void clickHome() {
        sceneLoader.loadScreen(ScreenResource.HOME);
    }

    public void clickPlayAgain() {
        gameViewModel.playAgain();
        sceneLoader.loadScreen(ScreenResource.GUESS);
    }

    @Override
    public void transitionOnEnter() {

        EndGameScreenDto data = gameViewModel.getEndGameScreenData();
        if (data.isPracticeMode)
        {
            // Slide and fade as normal in practice mode (since we don't want to show the score)
            bigScoreContainer.setOpacity(0);
            AnimationBuilder.buildSlideAndFadeTransition(mainContainer).play();
            return;
        }

        // This is a fairly elaborate transition. First we show the use their score
        // in the form of the "bigScoreContainer" element. This is shown for two seconds,
        // then faded out and replaced with the detailed "mainContainer" element, which
        // occurs simultaneously.

        mainContainer.setOpacity(0);

        // 1. Slide and Fade the big score into view
        Animation bigScoreTransition = AnimationBuilder.buildSlideAndFadeTransition(bigScoreContainer);

        // 2. Pause on the score while confetti plays
        PauseTransition pauseOnScore = new PauseTransition(Duration.seconds(2));

        // 3. (simultaneous) Fade out the score
        FadeTransition bigScoreFadeOut = new FadeTransition(Duration.millis(250), bigScoreContainer);
        bigScoreFadeOut.setFromValue(1.0);
        bigScoreFadeOut.setToValue(0);

        // 3. (simultaneous) Slide and fade in the main content
        Animation mainContainerTransition = AnimationBuilder.buildSlideAndFadeTransition(mainContainer);

        new SequentialTransition(bigScoreTransition, pauseOnScore,
                new ParallelTransition(bigScoreFadeOut, mainContainerTransition)).play();
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
        mainContainer.setPickOnBounds(false);
        bigScoreContainer.setPickOnBounds(false);

        // Initial confetti bursts
        runAfterDelay(() -> {
            particleView.emit(200, width * 1/2, height/2);
        }, 500L);
    }

    @Override
    protected void populateViewData() {
        EndGameScreenDto data = gameViewModel.getEndGameScreenData();
        labelTitle.setText(data.isPracticeMode ? "Practice Summary" : "Game Summary");
        labelTopic.setText(data.topic);
        miniScoreLabel.setText(String.valueOf(data.totalScore));
        bigScoreLabel.setText(String.valueOf(data.totalScore));

        if (data.isPracticeMode) {
            JavaFXUtil.toggleNodeVisibility(scoreContainer, false);
        }

        // Clear table view
        tableView.getColumns().clear();

        ObservableList<Round> items = FXCollections.observableList(data.rounds);

        /*
         * Add columns with relevant "value" display methods
         */

        // NB: We can use indexOf here since these columns cannot be sorted now
        TableColumn<Round, Integer> roundCol = createTableColumn("Round", (round) -> items.indexOf(round) + 1);
        TableColumn<Round, String> wordCol = createTableColumn("Word", Round::getWord);
        TableColumn<Round, String> resultCol = createTableColumn("Result", (round) -> round.getResult().toCapitalised());
        TableColumn<Round, String> guessCol = createTableColumn("Your Guess(es)", (round) -> String.join("\n", round.getGuesses()));
        TableColumn<Round, Integer> timeTakenCol = createTableColumn("Time Taken", Round::getDurationSecondsTruncated);
        TableColumn<Round, Integer> scoreCol = createTableColumn("Score", Round::getScoreContribution);

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

    /**
     * @return an unsortable table column with the given {@code title} header and uses
     * {@code mapper} to generate the display value.
     */
    private <T> TableColumn<Round, T> createTableColumn(String title, Function<Round, T> mapper) {
        TableColumn<Round, T> column = new TableColumn<>(title);

        // This ensures that the title column is always visible
        column.setMinWidth(title.length() * 10 + 10);

        column.setSortable(false);
        column.setCellValueFactory(cellData -> {
            Round round = cellData.getValue();
            return new ReadOnlyObjectWrapper<>(mapper.apply(round));
        });

        return column;
    }

}
