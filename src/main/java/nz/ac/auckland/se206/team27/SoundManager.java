package nz.ac.auckland.se206.team27;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;
import nz.ac.auckland.se206.team27.resource.AudioResource;

/**
 * Manager for sound related playback.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SoundManager {

    // The currently playing background track
    private MediaPlayer bgTrackPlayer;
    private AudioResource bgTrack;

    private Timeline fadeEffect;

    /**
     * Returns a single instance of Prefs Manager to be used throughout the application.
     */
    private static SoundManager _instance;
    public static SoundManager getInstance() {
        _instance = _instance == null ? new SoundManager() : _instance;
        return _instance;
    }

    /**
     * Play a sound clip. It is the caller's responsibility to ensure the clip is of
     * a short duration as playback cannot be cancelled once started.
     * @param soundClip The clip to be played
     */
    public void playClip(AudioResource soundClip) {
        AudioClip sound = new AudioClip(soundClip.getResourceUrl().toExternalForm());
        sound.play();
    }

    /**
     * Play the given audio track in the background
     * @param audio Track to be played
     */
    public void setBackgroundTrack(AudioResource audio) {

        // Only clear the background audio if the track has changed,
        // otherwise continue playback without restarting.
        if (bgTrack == audio) {
            return;
        }

        clearBackgroundTrack();
        Media sound = new Media(audio.getResourceUrl().toExternalForm());
        bgTrack = audio;
        bgTrackPlayer = new MediaPlayer(sound);

        // Loop until stopped
        bgTrackPlayer.setOnEndOfMedia(() -> {
            bgTrackPlayer.seek(Duration.ZERO);
            bgTrackPlayer.play();
        });

        bgTrackPlayer.play();
    }

    /**
     * Stops playing background music
     */
    public void clearBackgroundTrack() {
        if (fadeEffect != null) {
            fadeEffect.stop();
            fadeEffect = null;
        }

        if (bgTrackPlayer != null) {
            bgTrackPlayer.stop();
            bgTrackPlayer = null;
        }

        bgTrack = null;
    }

    /**
     * Smoothly fades the volume of the background track to the desired level
     * @param volume Target volume to be played
     */
    public void setBackgroundTrackVolume(double volume) {
        if (volume < 0 || volume > 1) {
            throw new IllegalArgumentException("Volume must be between 0 and 1");
        }

        if (fadeEffect != null) {
            fadeEffect.stop();
        }

        KeyValue target = new KeyValue(bgTrackPlayer.volumeProperty(), volume);
        KeyFrame frame = new KeyFrame(Duration.seconds(2), target);

        fadeEffect = new Timeline(frame);
        fadeEffect.play();
    }
}
