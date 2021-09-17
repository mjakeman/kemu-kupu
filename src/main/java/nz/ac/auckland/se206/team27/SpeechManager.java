package nz.ac.auckland.se206.team27;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.concurrent.Task;

public class SpeechManager {

    /**
     * A single thread executor which will execute tasks in
     * sequential order. We can submit tasks to this at any
     * time to be executed.
     */
    private final ExecutorService executor;

    /**
     * Create a new SpeechManager instance for handling
     * text-to-speech.
     */
    public SpeechManager() {
        executor = Executors.newSingleThreadExecutor();
    }

    public void stop() {
        executor.shutdownNow();
        // TODO: This shouldn't be so permanent?
    }

    /**
     * Speak text out loud
     * @param text The text to speak
     * @param speed Speed multiplier (default = 1.0f)
     * @return A Task representing the asynchronous call to festival
     */
    public Task<Void> talk(String text, float speed) {
        System.out.println("Saying [" + text + "] at speed " + speed);

        FestivalTask festivalTask = new FestivalTask(text, speed);
        executor.submit(festivalTask);
        return festivalTask;
    }

    /**
     * Speak text out loud
     * @param text The text to speak
     * @return A Task representing the asynchronous call to festival
     */
    public Task<Void> talk(String text) {
        return talk(text, 1.0f);
    }
}
