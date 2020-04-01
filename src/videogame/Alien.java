package videogame;

import java.awt.*;

public class Alien extends Item {
    private Animation animation;
    private int speed;

    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        speed = 5;
        animation = new Animation(Assets.alien, 100);
    }

    public void act(int direction) {
        animation.tick();
        x += direction * speed;
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
