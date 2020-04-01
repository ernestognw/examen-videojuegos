package videogame;

import java.awt.*;

public class Alien extends Item {
    private int direction;
    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public Alien(int x, int y, int width, int height, int direction) {
        super(x, y, width, height);

        setDirection(direction);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection () {
        return direction;
    }

    @Override
    public void tick() {
        setX(getX() + getDirection());
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.Alien, getX(), getY(), getWidth(), getHeight(), null);
    }
}
