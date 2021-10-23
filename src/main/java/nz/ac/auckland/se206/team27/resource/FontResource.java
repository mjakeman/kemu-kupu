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

    /**
     * For internal use by this enum. Associates the enum variant
     * with a particular Font resource on disk.
     * @param fileName file name/path of the resource
     */
    FontResource(String fileName) {
        this.resourceUrl = ResourceUtil.getResourceUrl("font/" + fileName);
    }

    @Override
    public URL getResourceUrl() {
        return resourceUrl;
    }

}
