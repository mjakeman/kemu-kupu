package nz.ac.auckland.se206.team27.confetti;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

// This is an ad-hoc particle system written for displaying confetti
// The following resources were helpful in creating this:
//  - https://www.howtosolutions.net/2016/09/javascript-canvas-simple-particle-system/

public class ParticleView extends Canvas {

    private class Particle {
        public double x;
        public double y;
        public double velX;
        public double velY;
        public Color color;
    }

    private final ArrayList<Particle> particles = new ArrayList<>();
    private final GraphicsContext gc;

    private final double width;
    private final double height;

    public ParticleView(double width, double height) {
        super(width, height);

        this.width = width;
        this.height = height;

        gc = getGraphicsContext2D();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, width, height);
                for (Particle particle : particles) {
                    updateParticle(particle);
                    drawParticle(particle);
                }
            }
        };

        timer.start();
    }

    public void emit(int numParticles, double x, double y) {
        particles.ensureCapacity(numParticles);
        double width = getWidth();
        double height = getHeight();

        for (int i = 0; i < numParticles; i++) {
            Particle particle = new Particle();
            particle.x = x;
            particle.y = y;
            particle.velX = 4 * Math.random() - 2;
            particle.velY = 4 * Math.random() - 2;
            particle.color = randomColor();

            particles.add(particle);
        }
    }

    // TODO: Change this
    private Color randomColor() {
        double r = 0, g = 0, b = 0;
        while (r < 100 && g < 100 && b < 100)
        {
            r = Math.floor(Math.random() * 256);
            g = Math.floor(Math.random() * 256);
            b = Math.floor(Math.random() * 256);
        }

        return new Color(r / 256.0, g / 256.0, b / 256.0, 1.0f);
    }

    private void updateParticle(Particle particle) {
        particle.x += particle.velX;
        particle.y += particle.velY;

        if (particle.x < 0 || particle.x > width)
            particle.velX = -particle.velX;

        if (particle.y < 0 || particle.y > height)
            particle.velY = -particle.velY;
    }

    private void drawParticle(Particle particle) {
        gc.setFill(particle.color);
        gc.fillOval(particle.x, particle.y, 5, 5);
    }
}
