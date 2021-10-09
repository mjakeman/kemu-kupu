package nz.ac.auckland.se206.team27.resource;

import java.net.URL;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public enum ColourProfileResource implements Resource {

    /**
     * Default colour scheme
     */
    DEFAULT("default.css"),

    /**
     * Colour scheme for addressing deuteranopia, protanopia, and tritanopia.
     */
    SAFE("safe.css");


    private final URL resourceUrl;


    ColourProfileResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("style/colours/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
