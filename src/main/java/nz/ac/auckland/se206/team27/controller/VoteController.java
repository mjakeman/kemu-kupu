package nz.ac.auckland.se206.team27.controller;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.resource.ResourceUtil;
import nz.ac.auckland.se206.team27.resource.ScreenResource;
import nz.ac.auckland.se206.team27.view.AnimationBuilder;
import nz.ac.auckland.se206.team27.view.SceneLoader;
import nz.ac.auckland.se206.team27.view.ViewConfig;
import nz.ac.auckland.se206.team27.view.controls.ParticleView;

import java.util.*;

import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class VoteController extends BaseController {

    @FXML
    public ParticleView particleView;

    @FXML
    public Label labelOne;

    @FXML
    public Label labelTwo;

    @FXML
    public Label labelThree;

    @FXML
    public StackPane parallax;

    @FXML
    public VBox container;

    @Override
    public void transitionOnEnter() {
        AnimationBuilder.buildZoomAndFadeTransition(parallax, container).play();
    }

    /**
     * Initialise particle system and randomly emit in the background.
     */
    public void initialize() {
        // Set team member names (randomly)
        List<String> names = Arrays.asList("Matthew Jakeman", "Jordan York", "Raymond Feng");
        Collections.shuffle(names);

        labelOne.setText(names.get(0));
        labelTwo.setText(names.get(1));
        labelThree.setText(names.get(2));

        // Only proceed if effects are enabled
        PreferencesManager prefsManager = PreferencesManager.getInstance();
        if (!prefsManager.getUseEffects())
            return;

        // Simple parallax effect
        parallax.setOnMouseMoved(event -> {
            double offsetX = event.getX() - (parallax.getWidth() / 2);
            double offsetY = event.getY() - (parallax.getHeight() / 2);

            double scaleFactor = 0.015f;
            int index = 0;

            for (Node node : parallax.getChildren()) {
                node.setTranslateX(offsetX * (10-index) * scaleFactor);
                node.setTranslateY(offsetY * (10-index) * scaleFactor);
                index++;
            }
        });

        // Setup Particle View
        double width = ViewConfig.WIDTH;
        double height = ViewConfig.HEIGHT;
        particleView.setWidth(width);
        particleView.setHeight(height);

        // Emit confetti on mouse click
        particleView.setOnMouseClicked(event -> {
            particleView.emit(80, event.getX(), event.getY());
        });

        // Initial confetti bursts
        runAfterDelay(() -> {
            particleView.emit(200, width * 1/2, height/2);
        }, 500L);

        // Confetti Timer Task
        TimerTask confettiTask = new TimerTask() {

            @Override
            public void run() {
                double x = 0.8 * Math.random() + 0.1;
                double y = 0.4 * Math.random();
                particleView.emit(200, x * ViewConfig.WIDTH, y * ViewConfig.HEIGHT);
            }
        };

        Timer nextScreenTimer = new Timer();
        nextScreenTimer.schedule(confettiTask, 5000L,3000L);
    }

    /**
     * Returns to home screen when button is clicked.
     */
    public void clickBack() {
        sceneLoader.loadScreen(ScreenResource.HOME, c -> {
            // Play special zoom and fade transition
            HomeController controller = (HomeController)c;
            Animation anim = AnimationBuilder.buildZoomAndFadeTransition(controller.root, controller.container);
            anim.play();
        });
    }
}
