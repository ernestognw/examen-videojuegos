/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * @author Ernesto García and Oscar Rodriguez
 */
public class Game implements Runnable {
    String title;                           // title of the window
    public static final String saveFileName = "latestState.txt"; // Save state to file
    private Display display;                // to display in the game
    private int width;                      // width of the window
    private int height;                     // height of the window
    private boolean running;                // to set the game
    private SpaceShip player;               // to use a player
    private KeyManager keyManager;          // to manage the keyboard
    private List<Alien> aliens;             // to store aliens
    private List<Bomb> bombs;               // to store bombs
    private List<Shot> shots;               // to store shots
    private int aliensDirection;            // to store the alien direction
    private int shotDelay;                  // to store shot delay

    // BG Variables
    private int BGPosition;
    private int MeteorsPosition;
    private int PlanetsPosition;
    private int StarsPosition;

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
        shotDelay = 0;

        // BG
        BGPosition = Commons.WINDOW_HEIGHT - Commons.BACKGROUND_HEIGHT;
        MeteorsPosition = Commons.WINDOW_HEIGHT - Commons.BACKGROUND_HEIGHT;
        PlanetsPosition = Commons.WINDOW_HEIGHT - Commons.BACKGROUND_HEIGHT;
        StarsPosition = Commons.WINDOW_HEIGHT - Commons.BACKGROUND_HEIGHT;
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
        bombs = new ArrayList<>();
        shots = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                Alien alien = new Alien(Commons.ALIEN_INIT_X + ((Commons.ALIEN_WIDTH + Commons.ALIEN_PADDING) * j), Commons.ALIEN_INIT_Y + ((Commons.ALIEN_HEIGHT + Commons.ALIEN_PADDING) * i), Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT);
                aliens.add(alien);
            }
        }
    }

    @Override
    public void run() {
        init();
        // time for each tick in nano seconds
        double timeTick = 1000000000 / Commons.FPS;
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
        int initialDirection = aliensDirection;
        shotDelay--;

        if (keyManager.SPACE && shotDelay < 0){
            Shot shot = new Shot(player.getX(), player.getY(), Commons.SHOT_WIDTH, Commons.SHOT_HEIGHT);
            shots.add(shot);
            shotDelay = 20;
        }

        if (keyManager.S) { // Save game state
            saveState();
        }

        if (keyManager.L) { // Load game state
            loadState();
        }

        // Check if any alien reach borders
        for (Alien alien : aliens) {
            if (alien.getX() <= Commons.BORDER_LEFT || alien.getX() >= Commons.WINDOW_WIDTH - Commons.BORDER_RIGHT) {
                aliensDirection *= -1;
                break;
            }
        }

        for (Alien alien : aliens) {
            if (aliensDirection != initialDirection) alien.setY(alien.getY() + 10);
            alien.act(aliensDirection);
            if(Math.random() < Commons.BOMB_CHANCE){
                Bomb bomb = new Bomb(alien.getX(), alien.getY(), Commons.BOMB_WIDTH, Commons.BOMB_HEIGHT);
                bombs.add(bomb);
            }
        }

        for (Iterator<Bomb> bombIter = bombs.iterator(); bombIter.hasNext();) {
            Bomb bomb = bombIter.next();
            bomb.tick();

            if (player.collision(bomb)) {
                bombIter.remove();
                player.setLives(player.getLives() - 1);
            }
        }

        for (Alien alien : aliens) {
            alien.tick();
        }

        for (Shot shot : shots) {
            shot.tick();
        }

        for (Iterator<Alien> alienIter = aliens.iterator(); alienIter.hasNext();) {
            Alien alien = alienIter.next();

            for (Iterator<Shot> shotIter = shots.iterator(); shotIter.hasNext();) {
                Shot shot = shotIter.next();

                if (alien.collision(shot)) {
                    alienIter.remove();
                    shotIter.remove();
                    player.setPoints(player.getPoints() + 100);
                }
            }
        }

        keyManager.tick();
    }

    private void render() {
        // get the buffer strategy from the display
        // to have several buffers when displaying
        BufferStrategy bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectangle, getting the graphic object from the
        buffer strategy element.
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        } else {
            // to paint objects
            Graphics g = bs.getDrawGraphics();

            // Background
            BGPosition += 5;
            StarsPosition += 4;
            PlanetsPosition += 3;
            MeteorsPosition += 2;


            g.drawImage(Assets.BG, 0, BGPosition, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);
            g.drawImage(Assets.BG, 0, BGPosition - Commons.BACKGROUND_HEIGHT, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);

            g.drawImage(Assets.Stars, 0, StarsPosition, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);
            g.drawImage(Assets.Stars, 0, StarsPosition - Commons.BACKGROUND_HEIGHT, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);

            g.drawImage(Assets.Planets, 0, PlanetsPosition, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);
            g.drawImage(Assets.Planets, 0, PlanetsPosition - Commons.BACKGROUND_HEIGHT, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);

            g.drawImage(Assets.Meteors, 0, MeteorsPosition, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);
            g.drawImage(Assets.Meteors, 0, MeteorsPosition - Commons.BACKGROUND_HEIGHT, Commons.BACKGROUND_WIDTH, Commons.BACKGROUND_HEIGHT, null);

            g.setColor(Color.WHITE);
            Font font = new Font("Agency FB", Font.BOLD, 22);
            g.setFont(font);
            g.drawString("Vidas: " + player.getLives(), 50, height - 20);
            g.drawString("Puntos: " + player.getPoints(), 200, height - 20);

            if(BGPosition > Commons.WINDOW_HEIGHT) BGPosition -= Commons.BACKGROUND_HEIGHT;
            if(MeteorsPosition > Commons.WINDOW_HEIGHT) MeteorsPosition -= Commons.BACKGROUND_HEIGHT;
            if(PlanetsPosition > Commons.WINDOW_HEIGHT) PlanetsPosition -= Commons.BACKGROUND_HEIGHT;
            if(StarsPosition > Commons.WINDOW_HEIGHT) StarsPosition -= Commons.BACKGROUND_HEIGHT;

            // Player
            player.render(g);

            // Alien
            for (Alien alien : aliens) {
                alien.render(g);
            }

            // Bomb
            for (Bomb bomb : bombs) {
                bomb.render(g);
            }

            // Shot
            for (Shot shot : shots) {
                shot.render(g);
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
            // thread to create the game
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Save the current game state
     */
    public void saveState() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(saveFileName));
            writer.println("[g] aliensDirection:" + aliensDirection);
            writer.println("[g] shotDelay:" + shotDelay);
            writer.println(player.toString());

            for (Alien alien : aliens) {
                writer.println(alien.toString());
            }

            for (Bomb bomb : bombs) {
                writer.println(bomb.toString());
            }

            for (Shot shot : shots) {
                writer.println(shot.toString());
            }

            writer.close();
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * Load the most recent game state
     */
    public void loadState() {
        try {
            FileReader file = new FileReader(saveFileName);
            BufferedReader reader = new BufferedReader(file);
            String line, attribute, typeofObject;
            int alienI = 0, bombI = 0, shotI = 0, x, y, lives, points;
            String[] attributes;

            while ((line = reader.readLine()) != null) {
                attributes = line.split(" ");
                typeofObject = attributes[0];

                if (typeofObject.equals("[g]")) {
                    attribute = attributes[1].substring(0, attributes[1].indexOf(":"));
                    switch (attribute) {
                        case "aliensDirection":
                            aliensDirection = parseInt(attributes[1].substring(attributes[1].indexOf(":") + 1));
                            break;
                        case "shotDelay":
                            shotDelay = parseInt(attributes[1].substring(attributes[1].indexOf(":") + 1));
                            break;
                    }
                } else {
                    x = parseInt(attributes[1].substring(attributes[1].indexOf(":") + 1));
                    y = parseInt(attributes[2].substring(attributes[2].indexOf(":") + 1));

                    switch (typeofObject) {
                        case "[p]": // Reload player state
                            lives = parseInt(attributes[3].substring(attributes[3].indexOf(":") + 1));
                            points = parseInt(attributes[4].substring(attributes[4].indexOf(":") + 1));

                            player = new SpaceShip(x, y, Commons.PLAYER_WIDTH, Commons.PLAYER_HEIGHT, this);
                            player.setPoints(points);
                            player.setLives(lives);
                            break;
                        case "[a]":
                            aliens.set(alienI, new Alien(x, y, Commons.ALIEN_WIDTH, Commons.ALIEN_HEIGHT));
                            alienI++;
                            break;
                        case "[b]":
                            bombs.set(bombI, new Bomb(x, y, Commons.SHOT_WIDTH, Commons.SHOT_HEIGHT));
                            bombI++;
                            break;
                        case "[s]":
                            shots.set(shotI, new Shot(x, y, Commons.BOMB_WIDTH, Commons.BOMB_HEIGHT));
                            shotI++;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
