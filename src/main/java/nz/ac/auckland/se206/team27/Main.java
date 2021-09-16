package nz.ac.auckland.se206.team27;

import javafx.application.Application;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.resource.Screen;
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

        loadResources();

        Main.stage = primaryStage;

        SceneLoader loader = new SceneLoader(stage);
        loader.loadScreen(Screen.HOME);

        stage.show();
    }

    /**
     * Loads resources that are used throughout the application (i.e. fonts).
     */
    // TODO: Load any CSS
    private void loadResources() {

    }

    public static void main(String[] args) {
        launch(args);
    }

}
