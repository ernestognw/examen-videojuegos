package videogame;

import javafx.scene.media.MediaPlayer;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class Sounds {
    public static MediaPlayer shot;

    /**
     * initializing sounds
     */
    public static void init() {
        shot = SoundLoader.loadSound("/sounds/shot.wav");
    }
}
