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
    int WINDOW_WIDTH = 1296;
    int WINDOW_HEIGHT = 810;
    int BORDER_RIGHT = 65;
    int BORDER_LEFT = 5;
    int GROUND = 790;

    // Player
    int PLAYER_LIVES = 8;
    int PLAYER_ARMOR = 8;
    int PLAYER_WIDTH = 57;
    int PLAYER_HEIGHT = 64;

    // Alien
    int ALIEN_ARMOR = 8;
    int ALIEN_HEIGHT = 64;
    int ALIEN_WIDTH = 39;
    int ALIEN_DESTROY_WIDTH = 80;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 60;
    int ALIEN_PADDING = 8;

    // Background
    int BACKGROUND_HEIGHT = 2304;
    int BACKGROUND_WIDTH = 1296;

    // Bomb
    int BOMB_HEIGHT = 16;
    int BOMB_WIDTH = 22;
    double BOMB_CHANCE = 0.005;

    // Shot
    int SHOT_HEIGHT = 16;
    int SHOT_WIDTH = 22;

    // GUI
    int ARMOR_BAR_WIDTH = 155;
    int ARMOR_BAR_HEIGHT = 35;
    int ARMOR_DOT_WIDTH = 15;
    int ARMOR_DOT_HEIGHT = 25;
    int HEALTH_BAR_HEIGHT = 35;
    int HEALTH_DOT_WIDTH = 15;
    int HEALTH_DOT_HEIGHT = 25;
    int STATS_BAR_WIDTH = 540;
    int STATS_BAR_HEIGHT = 40;
    int PAUSE_WIDTH = 210;
    int PAUSE_HEIGHT = 210;
    int GUI_PADDING = 20;
    int LOOSE_HEADER_WIDTH = 453;
    int LOOSE_HEADER_HEIGHT = 60;
    int WIN_HEADER_WIDTH = 453;
    int WIN_HEADER_HEIGHT = 60;
}