package nz.ac.auckland.se206.team27.view;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationBuilder {
    public static Animation buildFadeTransition(Node container, Duration duration) {
        // Fade In
        FadeTransition fade = new FadeTransition(duration, container);
        fade.setFromValue(0);
        fade.setToValue(1.0);
        fade.setCycleCount(1);

        return fade;
    }

    public static Animation buildSlideAndFadeTransition(Node container) {

        // Fade In
        Animation fade = buildFadeTransition(container, Duration.seconds(0.4));

        // Slide Up
        TranslateTransition translate = new TranslateTransition(Duration.seconds(0.2), container);
        translate.setFromY(10);
        translate.setToY(0);

        return new ParallelTransition(fade, translate);
    }

    public static Animation buildZoomAndFadeTransition(Node root, Node container) {

        // Fade In
        Animation fade = buildFadeTransition(root, Duration.seconds(1.0));

        // Zoom
        ScaleTransition scale = new ScaleTransition(Duration.seconds(0.5), container);
        scale.setFromX(1.05);
        scale.setFromY(1.05);
        scale.setToX(1.0);
        scale.setToY(1.0);

        return new ParallelTransition(fade, scale);
    }

    public static Animation buildShakeTransition(Node element) {
        KeyFrame frame1 = new KeyFrame(Duration.ZERO, new KeyValue(element.translateXProperty(), 0));
        KeyFrame frame2 = new KeyFrame(Duration.millis(100), new KeyValue(element.translateXProperty(), 10));
        KeyFrame frame3 = new KeyFrame(Duration.millis(200), new KeyValue(element.translateXProperty(), -10));
        KeyFrame frame4 = new KeyFrame(Duration.millis(300), new KeyValue(element.translateXProperty(), 0));

        return new Timeline(frame1, frame2, frame3, frame4);
    }

}
