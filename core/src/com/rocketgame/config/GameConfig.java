package com.rocketgame.config;

/**
 * Created by tench on 9/13/2017.
 */

//since this class will only contain constants, it is a good idea to not allow
//instantiation of this class, so we make constructor private
public class GameConfig {

    public static final float WIDTH = 480f;//pixels
    public static final float HEIGHT = 800f;//pixels

    public static final float WORLD_WIDTH = 6.0f;//world units
    public static final float WORLD_HEIGHT = 10.0f;//world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH/2f;//world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT/2f;//world units

    public static final float OBSTACLE_SPAWN_TIME = 0.25f;

    private GameConfig(){//non instantiable

    }
}
