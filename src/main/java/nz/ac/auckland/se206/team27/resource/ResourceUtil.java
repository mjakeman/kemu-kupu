package nz.ac.auckland.se206.team27.resource;

import java.net.URL;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ResourceUtil {

    /**
     * Returns the {@link URL} based on the path to the file, assuming it is located in the {@code resources} directory.
     */
    public static URL getResourceUrl(String filePath) {
        URL url = ResourceUtil.class.getResource("/" + filePath);

        if (url == null) {
            System.err.println("[Error] Failed to load resource: " + filePath);
        }

        return url;
    }

}
