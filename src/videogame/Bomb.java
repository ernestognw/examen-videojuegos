package videogame;

import java.awt.*;

public class Bomb extends Item {
    private boolean destroyed;
    private int speed = 5;
    private Animation animation;

    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public Bomb(int x, int y, int width, int height) {
        super(x, y, width, height);
        animation = new Animation(Assets.bomb, 100);
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void tick() {
        animation.tick();
        setY(getY() + speed);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}