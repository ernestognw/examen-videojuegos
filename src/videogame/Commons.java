package videogame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto García and Oscar Rodríguez
 */
public interface Commons {
    // Game
    int FPS = 50;
    int WINDOW_WIDTH = 1080;
    int WINDOW_HEIGHT = 675;
    int BORDER_RIGHT = 65;
    int BORDER_LEFT = 5;
    int GROUND = 665;

    // Player
    int PLAYER_WIDTH = 57;
    int PLAYER_HEIGHT = 64;

    // Alien
    int ALIEN_HEIGHT = 64;
    int ALIEN_WIDTH = 39;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 5;
    int ALIEN_PADDING = 5;

    // Background
    int BACKGROUND_HEIGHT = 1920;
    int BACKGROUND_WIDTH = 1080;

    // Bomb
    int BOMB_HEIGHT = 16;
    int BOMB_WIDTH = 22;
    double BOMB_CHANCE = 0.005;

    // Shot
    int SHOT_HEIGHT = 16;
    int SHOT_WIDTH = 22;
}