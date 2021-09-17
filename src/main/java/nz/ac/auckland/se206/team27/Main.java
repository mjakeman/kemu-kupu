package nz.ac.auckland.se206.team27;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.concurrent.Task;

/**
 * Main class.
 */
public class Main extends Application {

    private static Stage stage;

    private SpeechManager speechManager;

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
        speechManager = new SpeechManager();

        Label label = new Label("Kēmu Kupu");
        Scene scene = new Scene(label);
        primaryStage.setScene(scene);

        Main.stage = primaryStage;

        loadResources();

        stage.show();
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

    public static void main(String[] args) {
        launch(args);
    }

}
