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
    public static BufferedImage BG;             // to store background image
    public static BufferedImage Planets;        // to store background image
    public static BufferedImage Stars;          // to store background image
    public static BufferedImage Meteors;        // to store background image
    public static BufferedImage[] player;       // to store the player images
    public static BufferedImage[] alien;        // to store the alien images
    public static BufferedImage[] bomb;         // to store the bomb images
    public static BufferedImage[] shot;         // to store the bomb images

    /**
     * initializing the images of the game
     */
    public static void init() {
        BG = ImageLoader.loadImage("/images/BG.png");
        Planets = ImageLoader.loadImage("/images/Planets.png");
        Stars = ImageLoader.loadImage("/images/Stars.png");
        Meteors = ImageLoader.loadImage("/images/Meteors.png");

        player = loadSprites(10, 57, 64, "/images/sprites/player.png");
        alien = loadSprites(10, 39, 64, "/images/sprites/alien.png");
        bomb = loadSprites(10, 88, 64, "/images/sprites/bomb.png");
        shot = loadSprites(10, 24, 64, "/images/sprites/shot.png");
    }

    private static BufferedImage[] loadSprites(int framesInSprite, int frameWidth, int frameHeight, String path){
        BufferedImage spriteSheet;
        spriteSheet = ImageLoader.loadImage(path);
        BufferedImage[] sprites = new BufferedImage[framesInSprite];
        SpriteSheet sheet = new SpriteSheet(spriteSheet);

        for (int i = 0; i < framesInSprite; i++) {
            sprites[i] = sheet.crop(i * frameWidth, 0, frameWidth, frameHeight);
        }

        return sprites;
    }
}
