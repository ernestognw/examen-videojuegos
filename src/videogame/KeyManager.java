package videogame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Ernesto García
 */
public class KeyManager implements KeyListener {

    public boolean RIGHT;
    public boolean LEFT;
    public boolean UP;
    public boolean DOWN;

    private boolean[] keys;  // to store all the flags for every key

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed
        if (e.getKeyCode() != KeyEvent.VK_P) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
        if (e.getKeyCode() == KeyEvent.VK_P) {
            keys[e.getKeyCode()] = !keys[e.getKeyCode()];
        } else {
            keys[e.getKeyCode()] = false;
        }
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        RIGHT = keys[KeyEvent.VK_RIGHT];
        LEFT = keys[KeyEvent.VK_LEFT];
        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
    }
}
