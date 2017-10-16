package com.rocketgame.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketgame.config.GameConfig;
import com.rocketgame.util.GdxUtils;
import com.rocketgame.util.ViewportUtils;

import entity.Player;

/**
 * Created by tench on 10/14/2017.
 */

public class GameScreen implements Screen {


    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private Texture texture;
    private SpriteBatch batch;

    private Player player;

    private float obstacleTimer;//0 by default

    @Override
    public void show () {//same as create, used to initialize game and load resources
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        texture = new Texture(Gdx.files.internal("character.png"));
        batch = new SpriteBatch();

        player = new Player(batch,texture);

    }

    @Override
    public void render (float delta) {

        GdxUtils.clearScreen();
        worldBoundry();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawPlayer();
        batch.end();

        renderDebug();

    }

    private void drawPlayer(){
        update();
    }

    private void worldBoundry(){
        float playerX = MathUtils.clamp(player.getX(), 0, GameConfig.WORLD_WIDTH - player.getDiameter() );//(value, clamp value minimum, clamp value max)
        //basically value must be between minimum and maximum

        float playerY = MathUtils.clamp(player.getY(), 0,GameConfig.WORLD_HEIGHT - player.getDiameter());
        player.setPosition(playerX, playerY);
    }

    private void update(){
        player.update();
    }


    public void renderDebug(){
        renderer.setProjectionMatrix(camera.combined);
        ViewportUtils.drawGrid(viewport, renderer);
    }


    @Override
    public void dispose () {//dispose is not called automatically inside Screen
        renderer.dispose();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
        ViewportUtils.debugPixelPerUnit(viewport);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
    //called when we hide screen, when we go from one screen to another, going from game screen to main menu screen
    //dipose() called inside hide
    @Override
    public void hide() {
        dispose();
    }
}
