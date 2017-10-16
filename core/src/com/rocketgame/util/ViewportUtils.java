package com.rocketgame.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tench on 9/17/2017.
 */

public class ViewportUtils {

    private static final Logger log = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);
    private static final int DEFAULT_CELL_SIZE = 1;


    public static void drawGrid(Viewport viewport, ShapeRenderer renderer){
        drawGrid(viewport, renderer, DEFAULT_CELL_SIZE);
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer, int cellSize){

        if(viewport == null){ throw new IllegalArgumentException("viewport parameter is required"); }
        if(renderer == null){ throw new IllegalArgumentException("renderer parameter is required"); }

        //prevent cell size from being less than 1
        if(cellSize < DEFAULT_CELL_SIZE){
            cellSize = DEFAULT_CELL_SIZE;
        }

        //copy old color from renderer
        Color oldColor = new Color(renderer.getColor());//same as renderer.getColor().cpy();

        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeight = (int) viewport.getWorldHeight();

        int doubleWorldWidth = worldWidth *2;
        int doubleWorldHeight = worldHeight *2;

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.setColor(Color.WHITE);

        //draw vertical lines grid
        for(int x = -doubleWorldWidth; x< doubleWorldWidth; x+=cellSize){
            renderer.line(x, -doubleWorldHeight, x, doubleWorldHeight);
        }

        //draw horizontal lines grid
        for(int y = -doubleWorldHeight; y< doubleWorldHeight; y+=cellSize){
            renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y);
        }

        //draw x and y axis lines
        renderer.setColor(Color.RED);
        renderer.line(0,-doubleWorldHeight, 0, doubleWorldHeight);
        renderer.line(-doubleWorldWidth,0, doubleWorldWidth,0);

        //draw world bound
        renderer.setColor(Color.GREEN);
        renderer.line(0,worldHeight, worldWidth, worldHeight);
        renderer.line(worldWidth, 0 , worldWidth, worldHeight);


        renderer.end();

        renderer.setColor(oldColor);

    }

    public static void debugPixelPerUnit(Viewport viewport){
        if(viewport == null){
            throw new IllegalArgumentException("viewport parameter is requited");
        }

        float screenWidth = viewport.getScreenWidth();
        float screenHeight = viewport.getScreenHeight();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        float xPPU = screenWidth/worldWidth;
        float yPPU = screenHeight/worldHeight;

        log.debug("x PPU = " + xPPU + " y PPU = " + yPPU);
    }

    private ViewportUtils(){    }
}
