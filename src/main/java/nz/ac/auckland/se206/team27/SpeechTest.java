package nz.ac.auckland.se206.team27;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Small test program for testing SpeechManager.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SpeechTest extends Application {

    private final SpeechManager speechManager = SpeechManager.getInstance();

    /**
     * Function that is run to star the application.
     */
    @Override
    public void start(Stage primaryStage) {

        // Setup Window
        primaryStage.setTitle("SpeechManager Test");

        // Create a simple GUI for testing SpeechManager
        TextField phraseEntry = new TextField();
        Button playButton = new Button("Play");
        Button cancelButton = new Button("Cancel");
        Label status = new Label("Status of most recent Festival Task:");
        Label statusDesc = new Label("Waiting");
        Spinner<Double> speed = new Spinner<>(0.0, 2.0, 1.0, 0.1);

        // Queue new text-to-speech task
        EventHandler<ActionEvent> handler = event -> {
            Task<Void> task = speechManager.talk(phraseEntry.getText(), speed.getValue().floatValue());
            statusDesc.textProperty().bind(task.messageProperty());
        };

        playButton.setOnAction(handler);
        phraseEntry.setOnAction(handler);
        cancelButton.setOnAction(e -> speechManager.silence());

        // Vertical Layout
        VBox vbox = new VBox();
        vbox.getChildren().addAll(phraseEntry, speed, playButton, status, statusDesc, cancelButton);

        // Add scene
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        loadResources();

        primaryStage.show();
    }

    @Override
    public void stop() {
        speechManager.shutdown();
    }

    /**
     * Loads resources that are used throughout the application (i.e. fonts).
     */
    private void loadResources() {
    }

    /**
     * Starts the application.
     */
    public static void startApplication(String[] args) {
        launch(args);
    }

}
