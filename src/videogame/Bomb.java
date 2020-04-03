package videogame;

import java.awt.*;

public class Bomb extends Item {
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

    @Override
    public void tick() {
        int speed = 5;
        setY(getY() + speed);
    }

    @Override
    public String toString() {
        return "[b] x:" + x + " y:" + y;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}