package nz.ac.auckland.se206.team27.resource;

import java.net.URL;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public enum FontResource implements Resource {

    /**
     * Inter font family.
     */
    INTER_REGULAR("Inter-Regular.otf"),
    INTER_BOLD("Inter-Bold.otf"),
    INTER_ITALIC("Inter-Italic.otf");

    private final URL resourceUrl;

    FontResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("font/" + fileName);
    }


    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
