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
    public static BufferedImage Planets;        // to store planets image
    public static BufferedImage Stars;          // to store starts image
    public static BufferedImage Meteors;        // to store meteors image
    public static BufferedImage ArmorBar;       // to store armor dot image
    public static BufferedImage ArmorDot;       // to store armor bar image
    public static BufferedImage HealthBar;      // to store health dot image
    public static BufferedImage HealthDot;      // to store health bar image
    public static BufferedImage StatsBar;          // to store stats bar image
    public static BufferedImage loose;          // to store stats bar image
    public static BufferedImage win;          // to store stats bar image
    public static BufferedImage pause;          // to store stats bar image
    public static BufferedImage[] player;       // to store the player images
    public static BufferedImage[] alien;        // to store the alien images
    public static BufferedImage[] alienDamage;  // to store the alienDamage images
    public static BufferedImage[] alienDestroy;  // to store the alienDamage images
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
        ArmorBar = ImageLoader.loadImage("/images/gui/ArmorBar.png");
        ArmorDot = ImageLoader.loadImage("/images/gui/ArmorDot.png");
        HealthBar = ImageLoader.loadImage("/images/gui/HealthBar.png");
        HealthDot = ImageLoader.loadImage("/images/gui/HealthDot.png");
        StatsBar = ImageLoader.loadImage("/images/gui/StatsBar.png");
        loose = ImageLoader.loadImage("/images/gui/loose.png");
        win = ImageLoader.loadImage("/images/gui/win.png");
        pause = ImageLoader.loadImage("/images/gui/pause.png");

        player = loadSprites(10, 57, 64, "/images/sprites/player.png");
        alien = loadSprites(10, 39, 64, "/images/sprites/alien.png");
        alienDamage = loadSprites(10, 39, 64, "/images/sprites/alienDamage.png");
        bomb = loadSprites(10, 88, 64, "/images/sprites/bomb.png");
        shot = loadSprites(10, 24, 64, "/images/sprites/shot.png");
        alienDestroy = loadSprites(9, 80, 64, "/images/sprites/alienDestroy.png");
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
