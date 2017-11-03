package com.rocketgame.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

import entity.Obstacle;
import entity.Player2;
import entity.Player;
import com.rocketgame.util.debug.debugCameraController;

/**
 * Created by tench on 10/14/2017.
 */

public class GameScreen implements Screen {


    private static final Logger log = new Logger(GameScreen.class.getName(), Logger.DEBUG);
    private debugCameraController debugCameraController;


    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;
    private Texture texture;
    private Texture texture2;
    private Texture obstacleTexture;

    private SpriteBatch batch;

    private Player player;
    private Player2 player2;

    private Array<Obstacle> obstacleArray = new Array<Obstacle>();
    private Obstacle obstacle;

    private float obstacleTimer;//0 by default

    @Override
    public void show () {//same as create, used to initialize game and load resources
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        texture = new Texture(Gdx.files.internal("Jet.png"));
        //texture2 = new Texture(Gdx.files.internal("Jet.png"));
        obstacleTexture = new Texture(Gdx.files.internal("character.png"));

        batch = new SpriteBatch();

        player = new Player(batch,texture);
        player2 = new Player2(batch, texture2);
        obstacle = new Obstacle(batch, obstacleTexture);

        //DEBUG CAMERA
        debugCameraController = new debugCameraController();
        debugCameraController.setStartPosition(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y);

    }

    @Override
    public void render (float delta) {
        //DEBUG CAMERA
        debugCameraController.handleDebugInput(delta);
        debugCameraController.applyTo(camera);

        GdxUtils.clearScreen();
        worldBoundry();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawPlayer(delta);
        batch.end();

        renderDebug();//draws world grid

    }

    private void drawPlayer(float delta){
        update(delta);
        updateObstacle(delta);
    }

    private void worldBoundry(){
        //(value, clamp value minimum, clamp value max)basically value must be between minimum and maximum
        float playerX = MathUtils.clamp(player.getX(), 0, GameConfig.WORLD_WIDTH - player.getDiameter() );
        float playerY = MathUtils.clamp(player.getY(), 0, (GameConfig.WORLD_HEIGHT) - player.getDiameter());
        player.setPosition(playerX, playerY);

        //(value, clamp value minimum, clamp value max) basically value must be between minimum and maximum
//        float player2X = MathUtils.clamp(player2.getX(), 0, GameConfig.WORLD_WIDTH - player2.getDiameter() );
//        float player2Y = MathUtils.clamp(player2.getY(), (GameConfig.WORLD_HEIGHT/2),GameConfig.WORLD_HEIGHT - player2.getDiameter());
//        player2.setPosition(player2X, player2Y);
    }

    private void update(float delta){
        //player2.update();
        player.update();
    }

    private void updateObstacle(float delta){
        for(Obstacle obstacle: obstacleArray){
            obstacle.update();
        }

        spawnObstacle(delta);
    }

    private void spawnObstacle(float delta){
        obstacleTimer += delta; //total time passed so far before last obstacle spawned

        if(obstacleTimer > GameConfig.OBSTACLE_SPAWN_TIME){
            obstacleArray.add(new Obstacle(batch, obstacleTexture));
            obstacleTimer = 0;//reset the time
        }

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
