package entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketgame.config.GameConfig;

/**
 * Created by tench on 10/14/2017.
 */

public class Player {

    private float startPositionX = GameConfig.WORLD_WIDTH/2;
    private float startPostionY = 1;

    private float playerSizeX = 1.5f;
    private float playerSizeY = 1.5f;

    private float positionX = GameConfig.WORLD_WIDTH/2 - playerSizeX/2;
    private float positionY =1f - playerSizeY/2;

    private float moveSpeedX = .15f;

    private SpriteBatch batch;
    private Texture texture;

    public Player(SpriteBatch batch, Texture texture){
        this.batch = batch;
        this.texture = texture;
    }


    private void draw(){
        //update();
        //batch.setColor(Color.GREEN);
        batch.draw(texture, positionX,positionY,playerSizeX,playerSizeY);
    }

    public void update(){
        draw();
        float tempSpeedX = 0;
        float tempSpeedY = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            tempSpeedX = -moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            tempSpeedX = moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            tempSpeedY = moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            tempSpeedY = -moveSpeedX;
        }

        positionX += tempSpeedX;
        positionY += tempSpeedY;
        setPosition(positionX, positionY);
    }

    public void updatePlayer(){
    }

    public void setPosition(float x, float y){
        positionX =x;
        positionY =y;
    }


    public float getX() {
        return positionX;
    }

    public float getY() {
        return positionY;
    }

    public float getDiameter(){

        return playerSizeX;
    }



}
