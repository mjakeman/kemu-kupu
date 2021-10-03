package nz.ac.auckland.se206.team27;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

import java.io.IOException;

// Creates a custom control via composition
// Uses some code from JavaFX Documentation
// See: https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SpeedSwitcher extends HBox {

    @FXML
    public ToggleGroup toggleGroupSpeed;

    @FXML
    public ToggleButton slowToggle;

    @FXML
    public ToggleButton normalToggle;

    @FXML
    public ToggleButton fastToggle;

    // SpeechSpeed Property
    public final SimpleObjectProperty<SpeechSpeed> speechSpeedProperty
            = new SimpleObjectProperty<>(SpeechSpeed.NORMAL) {

        /**
         * Override set method to update toggle when the property
         * value changes.
         *
         * @param newSpeed New speed value
         */
        @Override
        public void set(SpeechSpeed newSpeed) {
            System.out.println("Setting speech speed to: " + newSpeed.toString());
            updateToggleGroup(newSpeed);
            super.set(newSpeed);
        }
    };

    public SpeedSwitcher() {

        // Attempt to populate control using FXML
        try {
            setupFXML();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // By default, toggle buttons can be deselected. We want them to behave
        // more like radio buttons. Thanks to:
        // https://stackoverflow.com/questions/23629181/making-togglebuttons-behave-like-radiobuttons
        toggleGroupSpeed.selectedToggleProperty().addListener((value, oldToggle, newToggle) -> {

            // Ensure we always have a toggle selected
            if (newToggle == null) {
                toggleGroupSpeed.selectToggle(oldToggle);
                return;
            }

            // Selection changed, determine new speed
            if (newToggle.equals(slowToggle)) {
                setSpeechSpeed(SpeechSpeed.SLOW);
            } else if (newToggle.equals(fastToggle)) {
                setSpeechSpeed(SpeechSpeed.FAST);
            } else {
                setSpeechSpeed(SpeechSpeed.NORMAL);
            }
        });
    }

    /**
     * Select the correct toggle for the preferred text-to-speech speed
     *
     * @param speed Preferred speed
     */
    private void updateToggleGroup(SpeechSpeed speed) {
        switch (speed) {
            case SLOW:
                toggleGroupSpeed.selectToggle(slowToggle);
                break;
            case FAST:
                toggleGroupSpeed.selectToggle(fastToggle);
                break;
            default:
                toggleGroupSpeed.selectToggle(normalToggle);
        }
    }

    /**
     * Initializes the control using the FXML template 'speed-switcher.fxml'. For
     * internal use by the class, must be called in the constructor
     *
     * @throws IOException FXML loading failed
     */
    private void setupFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ResourceUtil.getResourceUrl("view/controls/speed-switcher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        fxmlLoader.load();
    }

    /**
     * Getter for {@link #speechSpeedProperty}
     *
     * @return the current {@link SpeechSpeed}
     */
    public SpeechSpeed getSpeechSpeed() {
        return speechSpeedProperty.get();
    }

    /**
     * Setter for {@link #speechSpeedProperty}
     *
     * @param value The {@link SpeechSpeed} to display
     */
    public void setSpeechSpeed(SpeechSpeed value) {
        speechSpeedProperty.set(value);
    }
}
