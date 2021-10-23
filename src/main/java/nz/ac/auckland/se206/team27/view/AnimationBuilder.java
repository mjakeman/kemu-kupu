package nz.ac.auckland.se206.team27.view;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * A predefined set of animations which are used throughout the application.
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class AnimationBuilder {

    /**
     * A simple fade transition which fades in an element from zero to full
     * opacity over a specified duration.
     *
     * It is the caller's responsibility to play the animation.
     *
     * @param element The element to be animated.
     * @param duration The length of the fade transition.
     * @return The constructed animation.
     */
    public static Animation buildFadeTransition(Node element, Duration duration) {
        // Fade In
        FadeTransition fade = new FadeTransition(duration, element);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.setCycleCount(1);

        return fade;
    }

    /**
     * Creates a new animation which fades in an element and all its children while sliding
     * the content upwards. It is useful for introducing a new view or switching between
     * elements in an existing view.
     *
     * It is the caller's responsibility to play the animation.
     *
     * @param container The element to be animated.
     * @return The constructed animation.
     */
    public static Animation buildSlideAndFadeTransition(Node container) {

        // Fade In
        Animation fade = buildFadeTransition(container, Duration.seconds(0.4));

        // Slide Up
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), container);
        translate.setFromY(10);
        translate.setToY(0);

        return new ParallelTransition(fade, translate);
    }

    /**
     * Creates a new animation which fades in an element and all its children while
     * simultaneously zooming in the foreground content. This is ideal for introducing
     * a view for the first time (e.g. fade in from black).
     *
     * It is the caller's responsibility to play the animation.
     *
     * @param root The root element of the view (to be faded in)
     * @param foreground The foreground contents (to be zoomed)
     * @return The constructed animation.
     */
    public static Animation buildZoomAndFadeTransition(Node root, Node foreground) {

        // Fade In
        Animation fade = buildFadeTransition(root, Duration.seconds(1.0));

        // Zoom
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), foreground);
        scale.setFromX(1.05);
        scale.setFromY(1.05);
        scale.setToX(1.0);
        scale.setToY(1.0);

        return new ParallelTransition(fade, scale);
    }

    /**
     * Creates a new animation which shakes side to side. Useful for indicating an
     * error or invalid input.
     *
     * It is the caller's responsibility to play the animation.
     *
     * @param element The element to apply the animation to.
     * @return The constructed animation.
     */
    public static Animation buildShakeTransition(Node element) {
        KeyFrame frame1 = new KeyFrame(Duration.ZERO, new KeyValue(element.translateXProperty(), 0));
        KeyFrame frame2 = new KeyFrame(Duration.millis(100), new KeyValue(element.translateXProperty(), 10));
        KeyFrame frame3 = new KeyFrame(Duration.millis(200), new KeyValue(element.translateXProperty(), -10));
        KeyFrame frame4 = new KeyFrame(Duration.millis(300), new KeyValue(element.translateXProperty(), 0));

        return new Timeline(frame1, frame2, frame3, frame4);
    }

}
