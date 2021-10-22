package nz.ac.auckland.se206.team27;

import java.net.URISyntaxException;
import java.util.Arrays;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import nz.ac.auckland.se206.team27.controller.HomeController;
import nz.ac.auckland.se206.team27.resource.ColourProfileResource;
import nz.ac.auckland.se206.team27.resource.FontResource;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.SceneLoader;

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
     * Opens a web page or document in the default application.
     *
     * @param uri The URL or path to the file to open.
     */
    public static void openDocument(String uri) {
        hostServices.showDocument(uri);
    }

    /**
     * Applies a colour scheme to the global scene.
     * @param colourScheme The colour scheme to use
     */
    public static void applyColourScheme(ColourProfileResource colourScheme) {
        String colourSchemeCSS = colourScheme.getResourceUrl().toExternalForm();

        Scene scene = stage.getScene();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(colourSchemeCSS);
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

        // Add the application icon
        try {
            stage.getIcons().add(new Image(ResourceUtil.getResourceUrl("media/icon.PNG").toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

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
