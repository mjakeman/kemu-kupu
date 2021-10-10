package nz.ac.auckland.se206.team27.util;

import javafx.scene.Node;

/**
 * @author Raymond Feng (rf.raymondfeng@gmail.com)
 */
public class JavaFXUtil {

    /**
     * Sets the node equivalent of {@code display: hidden; } in normal CSS.
     */
    public static void toggleNodeVisibility(Node node, boolean isVisible) {
        node.setManaged(isVisible);
        node.setVisible(isVisible);
    }

}
