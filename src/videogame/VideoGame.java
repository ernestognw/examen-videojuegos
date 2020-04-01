/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 * @author Ernesto García and Oscar Rodríguez
 */
public class VideoGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game g = new Game("Mejorado Invaders", Commons.WINDOW_WIDTH, Commons.WINDOW_HEIGHT);
        g.start();
    }
}
