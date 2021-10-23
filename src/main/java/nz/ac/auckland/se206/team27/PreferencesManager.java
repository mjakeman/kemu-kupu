package nz.ac.auckland.se206.team27;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import nz.ac.auckland.se206.team27.resource.ColourProfileResource;
import nz.ac.auckland.se206.team27.speech.SpeechSpeed;

/**
 * Manager for saving and reading preferences.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class PreferencesManager {

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
     * Whether visually intensive effects should be used. May not perform
     * well on less capable machines (i.e. non accelerated virtual machines).
     */
    public final SimpleBooleanProperty useEffectsProperty;
    private final String useEffectsKey = "use-effects";

    /**
     * File-backed storage for arbitrary key-value pairs.
     */
    private final PreferencesStorage prefsStorage = new PreferencesStorage();

    /**
     * Returns a single instance of Prefs Manager to be used throughout the application.
     */
    private static PreferencesManager _instance;
    public static PreferencesManager getInstance() {
        _instance = _instance == null ? new PreferencesManager() : _instance;
        return _instance;
    }

    private PreferencesManager() {

        // For each property, attempt to load it from the file-backed preferences storage. If
        // a value was found then use it, otherwise proceed using a specified default value.

        // Colourblind Mode
        boolean useColourblindMode = prefsStorage.getBooleanOrDefault(colourblindModeKey, false);
        this.colourblindModeProperty = new SimpleBooleanProperty(useColourblindMode);

        colourblindModeProperty.addListener((p1, p2, isEnabled) -> {
            ColourProfileResource profile = isEnabled
                    ? ColourProfileResource.SAFE
                    : ColourProfileResource.DEFAULT;

            App.applyColourScheme(profile);
        });

        // Speech Speed
        SpeechSpeed speechSpeed = prefsStorage.getEnumOrDefault(speechSpeedKey, SpeechSpeed.class, SpeechSpeed.NORMAL);
        this.speechSpeedProperty = new SimpleObjectProperty<>(speechSpeed);

        // Effects
        boolean useEffects = prefsStorage.getBooleanOrDefault(useEffectsKey, true);
        this.useEffectsProperty = new SimpleBooleanProperty(useEffects);
    }

    /**
     * Save all preferences to disk.
     */
    public void save() {
        prefsStorage.setValue(colourblindModeKey, colourblindModeProperty.getValue().toString());
        prefsStorage.setValue(speechSpeedKey, speechSpeedProperty.getValue().toString());
        prefsStorage.setValue(useEffectsKey, useEffectsProperty.getValue().toString());
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

    /**
     * Setter for {@link #useEffectsProperty}
     * @param value Whether effects should be used
     */
    public void setUseEffects(boolean value) {
        useEffectsProperty.set(value);
    }

    /**
     * Getter for {@link #useEffectsProperty}
     * @return Whether effects are being used
     */
    public boolean getUseEffects() {
        return useEffectsProperty.get();
    }
}
