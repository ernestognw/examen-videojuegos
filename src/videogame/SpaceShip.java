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

    /**
     * Set the initial values to create the item
     *
     * @param x      <b>x</b> position of the object
     * @param y      <b>y</b> position of the object
     * @param width
     * @param height
     */
    public SpaceShip(int x, int y, int width, int height) {
        super(x, y, width, height);

        setX(270);
        setY(280);
        setWidth(width);
        setHeight(height);
    }

    public int getdx() {
        return dx;
    }

    public void setdx(int dx) {
        this.dx = dx;
    }

    public void tick() {
        setX(getX() + getdx());

        if (getX() <= 2) {
            setX(2);
        } else if (getX() >= Commons.BOARD_WIDTH - 2 * width) {
            setX(Commons.BOARD_WIDTH - 2 * width);
        }
    }

    @Override
    public void render(Graphics g) {
//        g.drawImage(Assets.SpaceShip, getX(), getY(), getWidth(), getHeight(), null);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            setdx(-2);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setdx(2);
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setdx(0);
        }
    }
}