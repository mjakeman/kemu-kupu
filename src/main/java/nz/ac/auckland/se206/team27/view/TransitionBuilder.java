package nz.ac.auckland.se206.team27.view;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionBuilder {
    public static Transition buildFadeTransition(Node container, Duration duration) {
        // Fade In
        FadeTransition fade = new FadeTransition(duration, container);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.setCycleCount(1);

        return fade;
    }

    public static Transition buildSlideAndFadeTransition(Node container) {

        // Fade In
        Transition fade = buildFadeTransition(container, Duration.seconds(0.6));

        // Slide Up
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), container);
        translate.setFromY(10);
        translate.setToY(0);

        return new ParallelTransition(fade, translate);
    }

    public static Transition buildZoomAndFadeTransition(Node root, Node container) {

        // Fade In
        Transition fade = buildFadeTransition(root, Duration.seconds(1.0));

        // Zoom
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), container);
        scale.setFromX(1.05);
        scale.setFromY(1.05);
        scale.setToX(1.0);
        scale.setToY(1.0);

        return new ParallelTransition(fade, scale);
    }

}
