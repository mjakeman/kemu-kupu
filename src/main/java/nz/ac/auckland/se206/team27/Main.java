package nz.ac.auckland.se206.team27;

import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.resource.FontResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.SceneLoader;

import static nz.ac.auckland.se206.team27.view.ViewConfig.HEIGHT;
import static nz.ac.auckland.se206.team27.view.ViewConfig.TITLE;
import static nz.ac.auckland.se206.team27.view.ViewConfig.VERSION;
import static nz.ac.auckland.se206.team27.view.ViewConfig.WIDTH;

/**
 * Main class.
 */
public class Main extends Application {

    private static Stage stage;


    /**
     * Returns the main stage.
     */
    public static Stage getMainStage() {
        return stage;
    }

    /**
     * Function that is run to start the application.
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        primaryStage.setTitle(String.format("%s (%s)", TITLE, VERSION));

        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        // NB: Also setting min width to fix bug with linux window not being set correctly
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);

        primaryStage.setResizable(false);

        loadFonts();

        // Add temporary scene
        stage.setScene(new Scene(new AnchorPane()));

        SceneLoader loader = new SceneLoader(primaryStage);
        loader.loadScreen(ScreenResource.HOME);

        stage.show();

        stage.setOpacity(0);

        // Set the stage back to visible after 350 milliseconds to avoid white flash on startup
        runAfter(() -> stage.setOpacity(1), 350);
    }

    /**
     * Loads fonts that are used throughout the application.
     */
    private void loadFonts() {
        FontResource[] fonts = {};
        Arrays.stream(fonts).forEach((font) -> Font.loadFont(font.getResourceUrl().toExternalForm(), 10));
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Run the given {@link Runnable} after {@code delayMs} milliseconds.
     */
    private void runAfter(Runnable runnable, int delayMs) {
        new Thread(() -> {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(runnable);
        }).start();
    }

}
