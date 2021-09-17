package nz.ac.auckland.se206.team27;

import java.util.concurrent.*;

import javafx.concurrent.Task;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class SpeechManager {

    /**
     * A single thread executor which will execute tasks in
     * sequential order. We can submit tasks to this at any
     * time to be executed.
     */
    private final ExecutorService executor;

    /**
     * Thread-safe task queue used by the above executor. We
     * can clear this queue to remove all pending tasks.
     */
    private final BlockingQueue<Runnable> queue;

    /**
     * Create a new SpeechManager instance for handling
     * text-to-speech.
     */
    public SpeechManager() {
        // Create a Single-Thread Executor instance which will process
        // tasks sequentially in another thread. This is created manually
        // so that we can manipulate/clear the queue as necessary.
        queue = new LinkedBlockingQueue<Runnable>();
        executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, queue);
    }

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

    /**
     * Clears all queued phrases but allows the current text-to-speech
     * task to complete.
     */
    public void silence() {
        queue.clear();
    }
}
