package videogame;

import javafx.scene.media.MediaPlayer;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class Sounds {
    public static MediaPlayer shot;
    public static MediaPlayer hitAlien;
    public static MediaPlayer hitPlayer;
    public static MediaPlayer loose;
    public static MediaPlayer win;

    /**
     * initializing sounds
     */
    public static void init() {
        shot = SoundLoader.loadSound("/sounds/shot.wav");
        hitAlien = SoundLoader.loadSound("/sounds/hitAlien.wav");
        hitPlayer = SoundLoader.loadSound("/sounds/hitPlayer.wav");
        loose = SoundLoader.loadSound("/sounds/loose.wav");
        win = SoundLoader.loadSound("/sounds/win.mp3");
    }
}
