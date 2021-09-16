package nz.ac.auckland.se206.team27.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.team27.resource.ScreenResource;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class SceneLoader {

    /**
     * The stage to load the {@link Scene}s onto.
     */
    Stage stage;


    public SceneLoader(Stage stage) {
        this.stage = stage;
    }

    /**
     * Loads a JavaFX {@link Scene} associated with the {@link ScreenResource}.
     */
    public void loadScreen(ScreenResource screen) {
        try {
            Parent root = FXMLLoader.load(screen.getResourceUrl());
            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        } catch (Exception e) {
            System.err.println("[Error] Could not load screen: " + screen.name() + ", caused by: " + e);
        }
    }

}
