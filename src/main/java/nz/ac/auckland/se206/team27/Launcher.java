package nz.ac.auckland.se206.team27;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class Launcher {

    public static void main(String[] args) {
        App.startApplication(args);

        // Add GTK version 2 flag if we are on a Linux distribution
        if (System.getProperty("os.name").toLowerCase().contains("linux")) {
            System.setProperty("jdk.gtk.version", "2");
        }
    }

}
