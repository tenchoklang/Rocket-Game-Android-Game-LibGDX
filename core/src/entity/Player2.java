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

public class Player2 {

    private float playerSizeX = 1.5f;
    private float playerSizeY = 1.5f;

    private float startPositionX = GameConfig.WORLD_WIDTH/2 - playerSizeX/2;
    private float startPostionY = GameConfig.WORLD_HEIGHT - playerSizeY/2;

    private float positionX;
    private float positionY;

    private float moveSpeedX = .15f;

    private SpriteBatch batch;
    private Texture texture;

    public Player2(SpriteBatch batch, Texture texture){
        this.batch = batch;
        this.texture = texture;

        positionX = startPositionX;
        positionY = startPostionY;
    }


    private void draw(){
        //update();
       // oldColor.set(batch.getColor());//store previous batches color

        batch.setColor(Color.BLUE);//set it to a new color
        batch.draw(texture,
                positionX,positionY,
                playerSizeX,playerSizeY,
                0,0,
                texture.getWidth(), texture.getHeight(),
                false, true);

       // batch.setColor(oldColor);//set it back to old color

    }



    public void update(){
        draw();
        float tempSpeedX = 0;
        float tempSpeedY = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            tempSpeedX = -moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            tempSpeedX = moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            tempSpeedY = moveSpeedX;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
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
