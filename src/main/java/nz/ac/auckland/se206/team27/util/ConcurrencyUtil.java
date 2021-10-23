package nz.ac.auckland.se206.team27.util;

import javafx.application.Platform;

/**
 * Utils relating to concurrency and delay.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ConcurrencyUtil {

    /**
     * Runs a {@link Runnable} on a different thread after {@code delayMs} milliseconds.
     */
    public static void runAfterDelay(Runnable runnable, long delayMs) {
        new Thread(() -> {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(runnable);
        }).start();
    }

}
