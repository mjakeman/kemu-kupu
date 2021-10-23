package nz.ac.auckland.se206.team27.resource;

import java.net.URL;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public enum ScreenResource implements Resource {

    /**
     * The Home page with the main menu.
     */
    HOME("home.fxml"),

    /**
     * Allows the user to select a topic from a list of topics,
     * or pick a random topic to play with.
     */
    CHOOSE_TOPIC("choosetopic.fxml"),

    /**
     * Shows the topic title, description (blurb), and allows the
     * user to confirm or go back to the selection screen.
     */
    PREVIEW_TOPIC("previewtopic.fxml"),

    /**
     * Displayed when a user is prompted to enter the spelling of a word.
     */
    GUESS("guess.fxml"),

    /**
     * Displayed when the user finishes spelling a word and moves to the next word.
     */
    RESULT("result.fxml"),

    /**
     * Displayed at the end of a game.
     */
    REWARD("reward.fxml"),

    /**
     * Preferences menu.
     */
    PREFERENCES("preferences.fxml");


    private final URL resourceUrl;

    /**
     * For internal use by this enum. Associates the enum variant
     * with a particular Screen (FXML) resource on disk.
     * @param fileName file name/path of the resource
     */
    ScreenResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("view/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
