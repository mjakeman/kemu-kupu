package nz.ac.auckland.se206.team27.resource;

import java.net.URL;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public enum AudioResource implements Resource {

    /**
     * Background Loop
     */
    BG_TRACK("bg_loop.wav"),

    /**
     * Correct answer sound effect
     */
    CORRECT("correct.wav"),

    /**
     * Incorrect answer sound effect
     */
    INCORRECT("incorrect.wav"),

    /**
     * Try again/rejected entry sound effect
     */
    TRY_AGAIN("tryagain.wav"),

    /**
     * Skip word sound effect
     */
    SKIPPED("skipped.wav"),

    /**
     * Game ended sound effect
     */
    END_GAME("endgame.wav");


    private final URL resourceUrl;


    AudioResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("media/sounds/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
