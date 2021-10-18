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
     * Click sound effect
     */
    CLICK("click.wav");


    private final URL resourceUrl;


    AudioResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("media/sounds/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
