package com.rocketgame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by tench on 9/7/2017.
 */

public class GdxUtils {
    //private constructor means non instantiable
    //this makes sense since our class will have utility methods
    //all methods will be static, so we can access them directly
    private GdxUtils(){}

    public static void clearScreen(){//default clearScreen()
        //first clear screen
        //these two lines are needed to clear the screen
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1.0f);//makes screen black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    public static void clearScreen(Color color){
        //first clear screen
        //these two lines are needed to clear the screen
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);//makes screen color based on the parameter passed
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}


