package videogame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class ImageLoader {
    /**
     * to get an image from the file path
     *
     * @param path it is the path of the file
     * @return the <bold>BufferedImage</bold> object
     */
    public static BufferedImage loadImage(String path) {
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException ioe) {
            System.out.println("Error loading image " + path + ioe.toString());
            System.exit(1);
        }
        return bi;
    }

}
