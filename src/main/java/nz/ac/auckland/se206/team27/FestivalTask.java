package nz.ac.auckland.se206.team27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;

/**
 * This background task runs the festival text-to-speech
 * engine asynchronously as to not freeze the GUI. It can
 * be polled and cancelled midway through using the standard
 * JavaFX Task API.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class FestivalTask extends Task<Void> {

    private final String text;
    private final float speed;

    /**
     * Create a new background task that runs the festival
     * text-to-speech engine.
     * 
     * @param text The text to be spoken
     * @param speed Speed multiplier (1.0 is normal speed)
     */
    public FestivalTask(String text, float speed) {
        this.text = text;
        this.speed = speed;
    }

    /**
     * Override the Task's call method and run festival here. If
     * the task is cancelled while festival is running, an
     * InterruptedException will be thrown and the speech will
     * be cut-off.
     *
     * @throws IOException Thrown if an I/O error occurs
     */
    @Override
    protected Void call() throws IOException {

        updateMessage("Running");

        float multiplier = 1 / speed;

        // Festival uses Scheme for commands. To say 'Boo!' at half the speed, we can write:
        // > (begin (Parameter.set 'Duration_Stretch' 2) (SayText "Boo!"))

        // Interpolate text and the duration multiplier into the command string
        String command = "(begin (Parameter.set 'Duration_Stretch' " + multiplier + ") (SayText \"" + text + "\"))";

        // Run with '-b' = batch mode (non-interactive)
        ProcessBuilder builder = new ProcessBuilder("festival", "-b", command);

        // We need to consume output/error from the process in order
        // for proc.waitFor() to work correctly. We first redirect
        // the error stream to output here, then consume stdout below.
        builder.redirectErrorStream(true);
        Process proc = builder.start();

        // Consume stdout
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        while (reader.readLine() != null) {}

        // Wait for festival to complete
        try {
            // Set a timeout of one minute in case festival does not return
            proc.waitFor(1, TimeUnit.MINUTES);

            // Handle failure case (err code not 0)
            if (proc.exitValue() != 0) {
                updateMessage("Failed");
                return null;
            }

        } catch (InterruptedException e) {
            // Handle cancellation case
            if (isCancelled()) {
                proc.destroy();
                updateMessage("Cancelled");
                return null;
            }
        }

        // Task succeeded
        updateMessage("Success");
        return null;
    }
}
