package nz.ac.auckland.se206.team27.confetti;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.LinkedList;

// This is a particle system written for displaying confetti. It is
// loosely inspired by the following resource, but heavily modified
// for this project:
//  - https://www.howtosolutions.net/2016/09/javascript-canvas-simple-particle-system/

/**
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ParticleView extends Canvas {

    private class Particle {
        // Position
        public double x;
        public double y;

        // Velocity
        public double velX;
        public double velY;
        public double drag;

        // Rotation
        public double rotation;
        public double velRot;

        // Display
        public Color color;
        public int lifetime;
        public double sizeFactor;

        public boolean isDead;
    }

    private final LinkedList<Particle> particles = new LinkedList<>();
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
        // particles.ensureCapacity(numParticles);

        for (int i = 0; i < numParticles; i++) {
            Particle particle = new Particle();
            particle.x = x;
            particle.y = y;
            particle.velX = 16 * (Math.random() - 0.5);
            particle.velY = 16 * (Math.random() - 0.5);
            particle.color = randomColor();
            particle.lifetime = 400;
            particle.drag = 0.98f;
            particle.rotation = Math.random() * 360;
            particle.velRot = 4 * (Math.random() - 0.5);
            particle.sizeFactor = 0.5 + (0.5 * Math.random());

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
        if (particle.isDead)
            return;

        particle.x += particle.velX;
        particle.y += particle.velY;

        particle.velX *= particle.drag;
        particle.velY *= particle.drag;
        // particle.velRot *= (particle.drag / 100);

        particle.rotation += particle.velRot;

        // Gravity
        particle.velY += 0.04f;

        // Wind/Sway
        particle.velX += 0.02f * (Math.random() - 0.5);

        if (particle.lifetime-- <= 0)
            particle.isDead = true;
    }

    private void drawParticle(Particle particle) {
        if (particle.isDead)
            return;

        gc.save();

        // Thanks to: https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
        Rotate r = new Rotate(particle.rotation, particle.x, particle.y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // Draw rectangle
        gc.setFill(particle.color);
        gc.fillRect(particle.x, particle.y, 8 * particle.sizeFactor, 4 * particle.sizeFactor);

        gc.restore();
    }
}
