package nz.ac.auckland.se206.team27;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PrefsManager {

    /**
     * The colours should be changed to accommodate for colourblind
     * or otherwise visually impaired users.
     */
    public final SimpleBooleanProperty colourblindModeProperty;
    private final String colourblindModeKey = "colourblind-mode";

    /**
     * The user's preferred speed for text-to-speech playback.
     */
    public final SimpleObjectProperty<SpeechSpeed> speechSpeedProperty;
    private final String speechSpeedKey = "speech-speed";

    /**
     * File-backed storage for arbitrary key-value pairs.
     */
    private final PrefsKeystore keystore = new PrefsKeystore();

    /**
     * Returns a single instance of Prefs Manager to be used throughout the application.
     */
    private static PrefsManager _instance;
    public static PrefsManager getInstance() {
        _instance = _instance == null ? new PrefsManager() : _instance;
        return _instance;
    }

    private PrefsManager() {

        // For each property, attempt to load it from the file-backed preferences keystore. If
        // a value was found then use it, otherwise proceed using a specified default value.

        // Colourblind Mode
        boolean useColourblindMode = keystore.getBooleanOrDefault(colourblindModeKey, false);
        this.colourblindModeProperty = new SimpleBooleanProperty(useColourblindMode);

        // Speech Speed
        SpeechSpeed speechSpeed = keystore.getEnumOrDefault(speechSpeedKey, SpeechSpeed.class, SpeechSpeed.NORMAL);
        this.speechSpeedProperty = new SimpleObjectProperty<>(speechSpeed);
    }

    /**
     * Save all preferences to disk.
     */
    public void save() {
        keystore.setValue(colourblindModeKey, colourblindModeProperty.getValue().toString());
        keystore.setValue(speechSpeedKey, speechSpeedProperty.getValue().toString());
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
