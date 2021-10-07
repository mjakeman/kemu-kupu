package nz.ac.auckland.se206.team27;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.controller.HomeController;
import nz.ac.auckland.se206.team27.resource.FontResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.SceneLoader;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;
import static nz.ac.auckland.se206.team27.view.ViewConfig.*;

/**
 * Main class.
 */
public class App extends Application {

    private static Stage stage;
    private static HostServices hostServices;

    /**
     * Returns the main stage.
     */
    public static Stage getMainStage() {
        return stage;
    }

    /**
     * Opens a web page in the system web browser
     * @param url The website to open
     */
    public static void openWebPage(String url) {
        hostServices.showDocument(url);
    }

    /**
     * Function that is run to start the application.
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        hostServices = getHostServices();

        primaryStage.setTitle(String.format("%s (%s)", TITLE, VERSION));

        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        // NB: Also setting min width to fix bug with linux window not being set correctly
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMinHeight(HEIGHT);

        primaryStage.setResizable(false);

        loadFonts();

        stage.setOpacity(0);
        SceneLoader loader = new SceneLoader(primaryStage);
        loader.loadScreen(ScreenResource.HOME, c -> {
            // Normally, home plays a slide and fade transition. On first load,
            // we want to instead play a 'Zoom and Fade' transition.
            HomeController controller = (HomeController)c;
            Animation anim = AnimationBuilder.buildZoomAndFadeTransition(controller.root, controller.container);
            anim.play();
        });

        stage.getIcons().add(new Image(new File("\\src\\resources\\media\\icon.PNG").toURI().toString()));

        stage.show();

        runAfterDelay(() -> stage.setOpacity(1), 100);
    }

    /**
     * Loads fonts that are used throughout the application.
     */
    private void loadFonts() {
        FontResource[] fonts = FontResource.values();
        Arrays.stream(fonts).forEach((font) -> Font.loadFont(font.getResourceUrl().toExternalForm(), 10));
    }

    /**
     * Starts the application.
     */
    public static void startApplication(String[] args) {
        launch(args);
    }

}
