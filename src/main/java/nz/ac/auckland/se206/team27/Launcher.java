package nz.ac.auckland.se206.team27;

/**
 * The entrypoint to the application, as specified in {@code module-info.java}.
 *
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class Launcher {

    public static void main(String[] args) {
        // Add GTK version 2 flag if we are on a Linux distribution
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("jdk.gtk.version", "2");
        }

        App.startApplication(args);
    }

}
