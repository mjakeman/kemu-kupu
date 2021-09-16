package nz.ac.auckland.se206.team27;

public class SpeechManager {

    /**
     * Speak text out loud
     * @param text The text to speak
     * @param speed Speed multiplier (default = 1.0f)
     */
    public void talk(String text, float speed) {
        System.out.println("Saying [" + text + "] at speed " + speed);

        String args = "(SayText \"" + text + "\")";

        try {
            ProcessBuilder builder = new ProcessBuilder("festival", args);
            Process proc = builder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Speak text out loud
     * @param text The text to speak
     */
    public void talk(String text) {
        talk(text, 1.0f);
    }
}
