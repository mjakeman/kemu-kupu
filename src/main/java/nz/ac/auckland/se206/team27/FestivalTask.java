package nz.ac.auckland.se206.team27;

import javafx.concurrent.Task;

public class FestivalTask extends Task<Void> {
    String text;
    float speed;

    public FestivalTask(String text, float speed) {
        this.text = text;
        this.speed = speed;
    }

    /**
     * Invoked when the Task is executed, the call method must be overridden and
     * implemented by subclasses. The call method actually performs the
     * background thread logic. Only the updateProgress, updateMessage, updateValue and
     * updateTitle methods of Task may be called from code within this method.
     * Any other interaction with the Task from the background thread will result
     * in runtime exceptions.
     *
     * @throws Exception an unhandled exception which occurred during the
     *                   background operation
     */
    @Override
    protected Void call() throws Exception {

        String args = "(SayText \"" + text + "\")";

        try {
            ProcessBuilder builder = new ProcessBuilder("festival", args);
            Process proc = builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
