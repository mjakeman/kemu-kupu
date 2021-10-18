package nz.ac.auckland.se206.team27;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;
import nz.ac.auckland.se206.team27.resource.AudioResource;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SoundManager {

    private MediaPlayer bgTrackPlayer;
    private AudioResource bgTrack;

    /**
     * Returns a single instance of Prefs Manager to be used throughout the application.
     */
    private static SoundManager _instance;
    public static SoundManager getInstance() {
        _instance = _instance == null ? new SoundManager() : _instance;
        return _instance;
    }

    public SoundManager() {
    }

    public void playClip(AudioResource soundClip) {
        AudioClip sound = new AudioClip(soundClip.getResourceUrl().toExternalForm());
        sound.play();
    }

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

    public void clearBackgroundTrack() {
        if (bgTrackPlayer != null) {
            bgTrackPlayer.stop();
        }
        bgTrack = null;
    }
}
