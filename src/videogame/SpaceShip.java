package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.KeyEvent;

public class SpaceShip extends Item {
    private int dx;
    private Animation animation;
    private Game game;

    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public SpaceShip(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);

        dx = 2;
        this.game = game;
        animation = new Animation(Assets.player, 100);
    }

    public void tick() {
        animation.tick();
        if (game.getKeyManager().RIGHT) {
            setX(getX() + dx);
        }

        if (game.getKeyManager().LEFT) {
            setX(getX() - dx);
        }

        if (getX() <= Commons.BORDER_LEFT) {
            setX(Commons.BORDER_LEFT);
        }

        if (getX() >= Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT) {
            setX(Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}