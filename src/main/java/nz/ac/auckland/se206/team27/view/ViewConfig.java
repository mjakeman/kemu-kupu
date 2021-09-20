package nz.ac.auckland.se206.team27.view;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class ViewConfig {

    public static final String TITLE = "Kēmu Kupu";

    /**
     * The version from the build implementation (set in gradle.properties), or 'DEV' if in development.
     */
    public static final String VERSION = getVersion();
    private static String getVersion() {
        String version = ViewConfig.class.getPackage().getImplementationVersion();
        return version != null ? version : "DEV";
    }

    public static final int WIDTH = 960;

    public static final int HEIGHT = 540;

}
