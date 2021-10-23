package nz.ac.auckland.se206.team27.view.controls;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This is a particle system written for displaying confetti. It is
 * loosely inspired by the following resource, but heavily modified
 * for this project:
 *  - https://www.howtosolutions.net/2016/09/javascript-canvas-simple-particle-system/
 *
 * @author Matthew Jakeman (mjakeman26@outlook.co.nz)
 */
public class ParticleView extends Canvas {

    /**
     * A collection of properties which define a single
     * particle's behaviour and style.
     */
    private static class Particle {
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

    /**
     * List of all active particles.
     */
    private final LinkedList<Particle> particles = new LinkedList<>();

    /**
     * Reference to the Canvas' graphics context for drawing.
     */
    private final GraphicsContext gc;

    /**
     * Maximum number of particles which can be displayed on screen
     * at one time.
     */
    private final int maxParticles = 10000;

    /**
     * Create a new {@link ParticleView} control for displaying confetti.
     */
    public ParticleView() {
        gc = getGraphicsContext2D();

        // This will be called by JavaFX to update and
        // draw the confetti at a consistent rate.
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, getWidth(), getHeight());
                for (Particle particle : particles) {
                    updateParticle(particle);
                    drawParticle(particle);
                }

                // Cleanup Dead Particles
                particles.removeIf(particle -> particle.isDead);
            }
        };

        timer.start();
    }

    /**
     * Emit a burst of confetti at the given coordinates.
     *
     * @param numParticles The number of particles to emit (e.g. 80)
     * @param x Emit particles at this x-coordinate.
     * @param y Emit particles at this y-coordinate.
     */
    public void emit(int numParticles, double x, double y) {

        // Limit particles for performance reasons
        if (particles.size() > maxParticles)
            return;

        for (int i = 0; i < numParticles; i++) {

            // Generate a velocity magnitude and angle
            double velMagnitude = Math.random() * 12;
            double velAngle = Math.random() * 360;

            // Decompose into x/y velocity
            double velX = velMagnitude * Math.cos(velAngle);
            double velY = velMagnitude * Math.sin(velAngle);

            // Create particles
            Particle particle = new Particle();
            particle.sizeFactor = 0.5 + (0.5 * Math.random());
            particle.x = x;
            particle.y = y;
            particle.velX = velX * (2.0 - particle.sizeFactor); // adjust for size
            particle.velY = velY * (2.0 - particle.sizeFactor);
            particle.color = randomColor();
            particle.lifetime = 400;
            particle.drag = 0.98f;
            particle.rotation = Math.random() * 360;
            particle.velRot = 4 * (Math.random() - 0.5);

            particles.add(particle);
        }
    }

    /**
     * Randomly generate a "bright" colour.
     *
     * @return The generated RGB value.
     */
    private Color randomColor() {

        // Colour Generation method adapted from:
        // https://www.howtosolutions.net/2016/09/javascript-canvas-simple-particle-system/
        // This ensures that colours are fairly vibrant by excluding dark
        // colours (e.g. black and grey).

        double red = 0;
        double green = 0;
        double blue = 0;

        while (red < 0.3 && green < 0.3 && blue < 0.3)
        {
            red = Math.random();
            green = Math.random();
            blue = Math.random();
        }

        return new Color(red, green, blue, 1.0f);
    }

    /**
     * Update particle physics and flag dead particles.
     *
     * @param particle The particle to update.
     */
    private void updateParticle(Particle particle) {
        if (particle.isDead)
            return;

        // Position/Rotation
        particle.x += particle.velX;
        particle.y += particle.velY;
        particle.rotation += particle.velRot;

        // Velocity
        particle.velX *= particle.drag;
        particle.velY *= particle.drag;

        // Gravity
        particle.velY += 0.04f;

        // Wind/Sway
        particle.velX += 0.02f * (Math.random() - 0.5);

        // Flag dead particles now, remove later
        if (particle.lifetime-- <= 0)
            particle.isDead = true;
    }

    /**
     * Draw a particular particle on screen, respecting its size,
     * rotation, and colour attributes.
     *
     * @param particle The particle to draw.
     */
    private void drawParticle(Particle particle) {
        if (particle.isDead)
            return;

        gc.save();

        // Thanks to: https://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
        Rotate r = new Rotate(particle.rotation, particle.x, particle.y);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());

        // Draw rectangle
        gc.setFill(particle.color);
        gc.fillRect(particle.x, particle.y, 12 * particle.sizeFactor, 6 * particle.sizeFactor);

        gc.restore();
    }
}
