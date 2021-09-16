package nz.ac.auckland.se206.team27;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

        Label label = new Label ("Kēmu Kupu");
        Scene scene = new Scene(label);
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
