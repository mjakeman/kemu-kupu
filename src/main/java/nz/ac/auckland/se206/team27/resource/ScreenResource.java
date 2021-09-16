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
     * Displayed when a user is prompted to enter the spelling of a word.
     */
    GAME("game.fxml"),

    /**
     * Displayed when the user finishes spelling a word and moves to the next word.
     */
    RESULT("result.fxml"),

    /**
     * Displayed at the end of a game.
     */
    END_GAME("endgame.fxml"),

    /**
     * Preferences menu.
     */
    PREFERENCES("preferences.fxml");


    private final URL resourceUrl;


    ScreenResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("view/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
