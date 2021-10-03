package nz.ac.auckland.se206.team27.controls;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

import java.io.IOException;

// Creates a custom control via composition
// Uses some code from JavaFX Documentation
// See: https://docs.oracle.com/javafx/2/fxml_get_started/custom_control.htm

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class OnOffSwitcher extends HBox {

    @FXML
    public ToggleGroup colourToggleGroup;

    @FXML
    public RadioButton onToggle;

    @FXML
    public RadioButton offToggle;

    /**
     * Whether the switch is on (true) or off (false)
     */
    public final SimpleBooleanProperty stateProperty = new SimpleBooleanProperty(false) {

        /**
         * Override set method to update radios when the property value changes.
         * @param newState New state value
         */
        @Override
        public void set(boolean newState) {
            updateRadioButtons(newState);
            System.out.println("State: " + newState);
            super.set(newState);
        }
    };

    /**
     * A control which can be either 'on' or 'off'.
     */
    public OnOffSwitcher() {

        // Attempt to populate control using FXML
        try {
            setupFXML();
        } catch (IOException e) {
            e.printStackTrace();
        }

        colourToggleGroup.selectedToggleProperty().addListener((value, oldToggle, newToggle) -> {

            // Selection changed, determine on/off state
            if (newToggle != null) {
                setState(newToggle.equals(onToggle));
            }
        });
    }

    /**
     * Update radio buttons to match property
     *
     * @param state Whether 'on' or 'off' radio is selected
     */
    private void updateRadioButtons(boolean state) {
        if (state) {
            colourToggleGroup.selectToggle(onToggle);
        } else {
            colourToggleGroup.selectToggle(offToggle);
        }
    }

    /**
     * Initializes the control using the FXML template 'on-off-switcher.fxml'. For
     * internal use by the class, must be called in the constructor
     *
     * @throws IOException FXML loading failed
     */
    private void setupFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ResourceUtil.getResourceUrl("view/controls/on-off-switcher.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        fxmlLoader.load();
    }

    /**
     * Getter for {@link #stateProperty}
     *
     * @return the current state
     */
    public boolean getState() {
        return stateProperty.get();
    }

    /**
     * Setter for {@link #stateProperty}
     *
     * @param value The state to display
     */
    public void setState(boolean value) {
        stateProperty.set(value);
    }
}
