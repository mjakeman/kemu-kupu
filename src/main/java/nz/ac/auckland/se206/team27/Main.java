package nz.ac.auckland.se206.team27;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.concurrent.Task;

/**
 * Main class.
 */
public class Main extends Application {

    private static Stage stage;

    /**
     * Function that is run to star the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Set relevant application config
        primaryStage.setTitle("Some cool title");
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(360);
        primaryStage.setResizable(false);

        // Create SpeechManager
        SpeechManager speechManager = new SpeechManager();

        VBox vbox = new VBox();
        Label label = new Label("Kēmu Kupu");

        TextField textField = new TextField();
        Button playButton = new Button("Play");
        Button cancelButton = new Button("Cancel");
        Label status = new Label("Waiting");

        EventHandler<ActionEvent> handler = event -> {
            Task<Void> task = new FestivalTask(textField.getText(), 1.0f);
            new Thread(task).start();
            status.textProperty().bind(task.messageProperty());
        };
        playButton.setOnAction(handler);
        textField.setOnAction(handler);
        // cancelButton.setOnAction(e -> speechManager.stop());

        vbox.getChildren().addAll(label, textField, playButton, status, cancelButton);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        Main.stage = primaryStage;

        loadResources();

        stage.show();
    }

    /**
     * Loads resources that are used throughout the application (i.e. fonts).
     */
    private void loadResources() {
    }

    public static void main(String[] args) {
        launch(args);
    }

}
