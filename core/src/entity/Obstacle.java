package entity;

/**
 * Created by tench on 11/3/2017.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketgame.config.GameConfig;

/**
 * Created by tench on 10/14/2017.
 */

public class Obstacle {

    private float playerSizeX = .8f;
    private float playerSizeY = .8f;

    private float positionX;
    private float positionY = GameConfig.WORLD_HEIGHT - playerSizeY / 2;

    private float moveSpeedY = .10f;

    private SpriteBatch batch;
    private Texture texture;

    public Obstacle(SpriteBatch batch, Texture texture) {
        //positionX = (int) (Math.random() * GameConfig.WORLD_WIDTH + 1f);//gets a random posX from obstacle to spawn
        positionX = (float)(Math.random() * (( (GameConfig.WORLD_WIDTH - playerSizeX*2)  - (0)) + 1) + (0));

        this.batch = batch;
        this.texture = texture;
    }


    private void draw() {
        //update();

        batch.draw(texture,
                positionX, positionY,
                playerSizeX, playerSizeY,
                0, 0,
                texture.getWidth(), texture.getHeight(),
                false, true);
    }

    public void update() {
        draw();

        positionY -= moveSpeedY;
        setPosition(positionX, positionY);
    }


    public void setPosition(float x, float y) {
        positionX = x;
        positionY = y;
    }


    public float getX() {
        return positionX;
    }

    public float getY() {
        return positionY;
    }

    public float getDiameter() {

        return playerSizeX;
    }


}

