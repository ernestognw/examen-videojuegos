package videogame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class Assets {
    public static BufferedImage BG;           // to store background image
    public static BufferedImage Planets;           // to store background image
    public static BufferedImage Stars;           // to store background image
    public static BufferedImage Meteors;      // to store background image
    public static BufferedImage[] player;     // to store the player images

    /**
     * initializing the images of the game
     */
    public static void init() {
        BG = ImageLoader.loadImage("/images/BG.png");
        Planets = ImageLoader.loadImage("/images/Planets.png");
        Stars = ImageLoader.loadImage("/images/Stars.png");
        Meteors = ImageLoader.loadImage("/images/Meteors.png");

        loadPlayer();
    }


    /**
     * To load the player spritesheet for animations
     */
    private static void loadPlayer(){
        int framesInSprite = 9;
        BufferedImage playerSprites;
        playerSprites = ImageLoader.loadImage("/images/sprites/player.png");

        player = new BufferedImage[9];

        SpriteSheet playerSpriteSheets = new SpriteSheet(playerSprites);

        for (int i = 0; i < framesInSprite; i++) {
            player[i] = playerSpriteSheets.crop(i * 64, 0, 64, 64);
        }
    }
}
