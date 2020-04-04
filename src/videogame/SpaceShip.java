package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.KeyEvent;

public class SpaceShip extends Item {
    private int lives;
    private int armor;
    private int points;
    private int speed;
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

        lives = Commons.PLAYER_LIVES;
        armor = Commons.PLAYER_ARMOR;
        points = 0;
        speed = 15;
        this.game = game;
        animation = new Animation(Assets.player, 100);
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getLives() {
        return lives;
    }

    public int getArmor() {
        return armor;
    }

    public void reduceArmor() {
        armor--;
        if(armor == 0){
            lives--;
            points -= 500;
            armor = Commons.PLAYER_ARMOR;
        } else {
            points -= 10;
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void tick() {
        animation.tick();
        if (game.getKeyManager().RIGHT) {
            setX(getX() + speed);
        }

        if (game.getKeyManager().LEFT) {
            setX(getX() - speed);
        }

        if (getX() <= Commons.BORDER_LEFT) {
            setX(Commons.BORDER_LEFT);
        }

        if (getX() >= Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT) {
            setX(Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT);
        }
    }

    @Override
    public String toString() {
        return "[p] x:" + x + " y:" + y + " lives:" + lives + " points:" + points + " armor:" + armor;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animation.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}