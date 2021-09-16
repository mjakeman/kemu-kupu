package nz.ac.auckland.se206.team27;

import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.resource.FontResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.SceneLoader;

import static nz.ac.auckland.se206.team27.view.ViewConfig.HEIGHT;
import static nz.ac.auckland.se206.team27.view.ViewConfig.TITLE;
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
        primaryStage.setTitle(TITLE);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setResizable(false);

        loadFonts();

        Main.stage = primaryStage;

        SceneLoader loader = new SceneLoader(stage);
        loader.loadScreen(ScreenResource.HOME);

        stage.show();
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

}
