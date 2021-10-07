package nz.ac.auckland.se206.team27.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.App;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.resource.ColourProfileResource;
import nz.ac.auckland.se206.team27.resource.ScreenResource;

import static nz.ac.auckland.se206.team27.view.ViewConfig.HEIGHT;
import static nz.ac.auckland.se206.team27.view.ViewConfig.WIDTH;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class SceneLoader {

    /**
     * The stage to load the {@link Scene}s onto.
     */
    Stage stage;

    /**
     * A callback method that is called by {@link SceneLoader} once
     * a new view is loaded. This can be used to play 'entry'
     * animations/transitions. Note, this overrides the behaviour
     * defined in {@link BaseController#transitionOnEnter()}.
     */
    @FunctionalInterface
    public interface ScreenLoadCallback {
        void onEnter(BaseController controller);
    }


    public SceneLoader(Stage stage) {
        this.stage = stage;

        // Add root scene if no scene was previously set
        if (stage.getScene() == null) {
            Scene scene = new Scene(new AnchorPane(), WIDTH, HEIGHT);
            scene.setFill(Color.BLACK);
            stage.setScene(scene);

            // Now that the scene is set, we can apply a colour scheme
            App.applyColourScheme(ColourProfileResource.DEFAULT);
        }
    }

    /**
     * Loads a JavaFX {@link Scene} associated with the {@link ScreenResource}.
     *
     * @param screen The screen to load
     * @param callback An (optional) callback that is called when the screen is loaded
     */
    public void loadScreen(ScreenResource screen, ScreenLoadCallback callback) {
        try {
            var loader = new FXMLLoader(screen.getResourceUrl());

            // Set root
            Parent root = loader.load();
            stage.getScene().setRoot(root);

            // Configure controller
            BaseController controller = loader.getController();
            if (callback != null) {
                callback.onEnter(controller);
            } else {
                controller.transitionOnEnter();
            }

        } catch (Exception e) {
            System.err.println("[Error] Could not load screen: " + screen.name() + ", caused by: " + e);
            e.printStackTrace();
        }
    }

    /**
     * Loads a JavaFX {@link Scene} associated with the {@link ScreenResource}.
     *
     * @param screen The screen to load
     */
    public void loadScreen(ScreenResource screen) {
        loadScreen(screen, null);
    }

}
