package nz.ac.auckland.se206.team27.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import nz.ac.auckland.se206.team27.PreferencesManager;
import nz.ac.auckland.se206.team27.controller.base.BaseController;
import nz.ac.auckland.se206.team27.view.ViewConfig;
import nz.ac.auckland.se206.team27.view.controls.ParticleView;

import java.util.Timer;
import java.util.TimerTask;

import static nz.ac.auckland.se206.team27.util.ConcurrencyUtil.runAfterDelay;

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class VoteController extends BaseController {

    @FXML
    public ParticleView particleView;

    public void initialize() {
        // Only proceed if effects are enabled
        PreferencesManager prefsManager = PreferencesManager.getInstance();
        if (!prefsManager.getUseEffects())
            return;

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
                double y = 0.4 * Math.random() + 0.2;
                particleView.emit(200, x * ViewConfig.WIDTH, y * ViewConfig.HEIGHT);
            }
        };

        Timer nextScreenTimer = new Timer();
        nextScreenTimer.schedule(confettiTask, 5000L,3000L);
    }
}
