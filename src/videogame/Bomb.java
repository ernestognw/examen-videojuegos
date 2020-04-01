package videogame;

import java.awt.*;

public class Bomb extends Item {
    private boolean destroyed;

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
        setDestroyed(true);
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void tick() {
        setY(getY() + 5);
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.Bomb, getX(), getY(), getWidth(), getHeight(), null);
    }
}