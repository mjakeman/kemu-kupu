package nz.ac.auckland.se206.team27.speech;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class SpeedUtil {

    public static float getSpeedFromString(String speed) {
        switch (speed.toUpperCase()) {
            case "SLOW": return 0.5f;
            case "FAST": return 1.5f;
            case "NORMAL":
            default: return 1f;
        }
    }

}
