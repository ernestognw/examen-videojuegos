/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ernesto García and Oscar Rodriguez
 */
public class Game implements Runnable {
    String title;                           // title of the window
    private BufferStrategy bs;              // to have several buffers when displaying
    private Graphics g;                     // to paint objects
    private Display display;                // to display in the game
    private int width;                      // width of the window
    private int height;                     // height of the window
    private Thread thread;                  // thread to create the game
    private boolean running;                // to set the game
    private SpaceShip player;               // to use a player
    private KeyManager keyManager;          // to manage the keyboard
    private List<Alien> aliens;             // to store aliens
    private int aliensDirection;            // to store the alien direction

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title  to set the title of the window
     * @param width  to set the width of the window
     * @param height to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        keyManager = new KeyManager();
        aliensDirection = 1;
    }

    /**
     * To get the key manager
     *
     * @return <code>KeyManager</code>
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }


    /**
     * initializing the display window of the game
     */
    private void init() {
        display = new Display(title, width, height);
        Assets.init();
        Sounds.init();
        display.getJframe().addKeyListener(keyManager);
        player = new SpaceShip(width / 2, Commons.GROUND - Commons.PLAYER_HEIGHT, Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT, this);
        aliens = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                Alien alien = new Alien(Commons.ALIEN_INIT_X + Commons.ALIEN_WIDTH * j, Commons.ALIEN_INIT_Y + Commons.ALIEN_HEIGHT * i, Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT);
                aliens.add(alien);
            }
        }
    }

    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano seconds
        double timeTick = Commons.FPS / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanoseconds
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // Accumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;

            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
    }

    private void tick() {
        player.tick();
        for (Alien alien : aliens) {
            if (alien.getX() <= Commons.BORDER_LEFT || alien.getX() >= Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT) {
                aliensDirection *= -1;
                break;
            }
        }
        for (Alien alien : aliens) {
            alien.act(aliensDirection);
        }
        keyManager.tick();
    }

    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectangle, getting the graphic object from the
        buffer strategy element.
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            g = bs.getDrawGraphics();

            // Background
            g.drawImage(Assets.BG, 0, 0, width, height, null);
            g.drawImage(Assets.Meteors, 0, 0, width, height, null);
            g.drawImage(Assets.Planets, 0, 0, width, height, null);
            g.drawImage(Assets.Stars, 0, 0, width, height, null);

            // Player
            player.render(g);

            // Alien
            for (Alien alien : aliens) {
                alien.render(g);
            }

            bs.show();
            g.dispose();
        }
    }

    /**
     * setting the thead for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
        }
    }
}
