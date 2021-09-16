package nz.ac.auckland.se206.team27;

public class SpeechManager {

    /**
     * Speak text out loud
     * @param text The text to speak
     * @param speed Speed multiplier (default = 1.0f)
     */
    public void talk(String text, float speed) {
        System.out.println("Saying [" + text + "] at speed " + speed);

        FestivalTask festivalTask = new FestivalTask(text, speed);
        Thread bgThread = new Thread(festivalTask);
        bgThread.start();
    }

    /**
     * Speak text out loud
     * @param text The text to speak
     */
    public void talk(String text) {
        talk(text, 1.0f);
    }
}
