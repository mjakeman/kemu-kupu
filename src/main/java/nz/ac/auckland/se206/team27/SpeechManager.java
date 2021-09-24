package nz.ac.auckland.se206.team27;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SpeechManager {

    /**
     * Thread-safe task queue used by the executor below. We
     * can clear this queue to remove all pending tasks.
     */
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    /**
     * A Single-Thread Executor instance which will process tasks in
     * sequential order in another thread. We can submit tasks to this at
     * any time to be executed, and manipulate/clear the queue as necessary.
     */
    private final ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, queue);


    /**
     * Returns a single instance of Speech Manager to be used throughout the application.
     */
    private static SpeechManager _instance;
    public static SpeechManager getInstance() {
        _instance = _instance == null ? new SpeechManager() : _instance;
        return _instance;
    }

    private SpeechManager() {}

    /**
     * Shutdown the SpeechManager and free the thread pool. Submitting
     * and new text after this point is an error and an exception will
     * be thrown.
     */
    public void shutdown() {
        queue.clear();
        executor.shutdownNow();
    }

    /**
     * Speak text out loud
     * @param text The text to speak
     * @param speed Speed multiplier (default = 1.0f)
     * @return A Task representing the asynchronous call to festival
     */
    public Task<Void> talk(String text, float speed) {
        // Create new task and add it to the queue
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

    /**
     * Clears all queued phrases but allows the current text-to-speech
     * task to complete.
     */
    public void silence() {
        queue.clear();
    }
}
