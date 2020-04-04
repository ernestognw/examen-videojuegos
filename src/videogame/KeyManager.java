package videogame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class KeyManager implements KeyListener {

    public boolean RIGHT;
    public boolean LEFT;
    public boolean UP;
    public boolean DOWN;
    public boolean SPACE;
    public boolean G;
    public boolean C;
    public boolean P;
    public boolean R;

    private boolean[] keys;  // to store all the flags for every key

    public KeyManager() {
        keys = new boolean[256];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        RIGHT = keys[KeyEvent.VK_RIGHT];
        LEFT = keys[KeyEvent.VK_LEFT];
        UP = keys[KeyEvent.VK_UP];
        DOWN = keys[KeyEvent.VK_DOWN];
        SPACE = keys[KeyEvent.VK_SPACE];
        G = keys[KeyEvent.VK_G];
        C = keys[KeyEvent.VK_C];
        P = keys[KeyEvent.VK_P];
        R = keys[KeyEvent.VK_R];
    }
}
