package nz.ac.auckland.se206.team27;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PrefsManager {

    public final SimpleBooleanProperty colourblindModeProperty;
    public final SimpleObjectProperty<SpeechSpeed> speechSpeedProperty;

    /**
     * Returns a single instance of Prefs Manager to be used throughout the application.
     */
    private static PrefsManager _instance;
    public static PrefsManager getInstance() {
        _instance = _instance == null ? new PrefsManager() : _instance;
        return _instance;
    }

    private PrefsManager() {
        // TODO: These should be determined from disk
        this.colourblindModeProperty = new SimpleBooleanProperty(false);
        this.speechSpeedProperty = new SimpleObjectProperty<>(SpeechSpeed.NORMAL);
    }

    /**
     * Setter for {@link #colourblindModeProperty}
     * @param value Whether colourblind mode should be used
     */
    public void setColourblindMode(boolean value) {
        colourblindModeProperty.set(value);
    }

    /**
     * Getter for {@link #colourblindModeProperty}
     * @return Whether colourblind mode is active
     */
    public boolean getColourblindMode() {
        return colourblindModeProperty.get();
    }

    /**
     * Setter for {@link #speechSpeedProperty}
     * @param value The {@link SpeechSpeed} to be used
     */
    public void setSpeechSpeed(SpeechSpeed value) {
        speechSpeedProperty.set(value);
    }

    /**
     * Getter for {@link #speechSpeedProperty}
     * @return The {@link SpeechSpeed} being used
     */
    public SpeechSpeed getSpeechSpeed() {
        return speechSpeedProperty.get();
    }
}
