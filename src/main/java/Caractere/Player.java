package Caractere;

import Inputs.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Game.Game;
import utils.Coord;

import java.util.Objects;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.SCALE;

public class Player extends Entity {

    public KeyboardInput keyboardInput;

    public Game game;

    public final int speed=10;


    public Player(Game game){

        this.game=game;

        this.entityName = "Player";
        animationLib=new Image[3][];
        generateAnimationLib();

        keyboardInput=game.keyboardInput;
        this.status=STATIC;

        this.coord = new Coord(0,0);
    }

    public void updatePos(){
        if(keyboardInput.directionDiagonal()){
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                coord.addXY((int) (speed * 0.80), (int) (-speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                coord.addXY((int) (speed * 0.80), (int) (speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                coord.addXY((int) (-speed * 0.80), (int) (-speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                coord.addXY((int) (-speed * 0.80), (int) (speed * 0.80));
            }
        }
        else {
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP)) {
                coord.addY(-speed);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN)) {
                coord.addY(speed);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                coord.addX(-speed);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                coord.addX(speed);
            }
        }

    }





    public void updateStatus() {
        if(previousStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
            status=HIT;
        }
        else{
            if(keyboardInput.isEmpty(keyboardInput.movementKeyPressed)) {
                status = STATIC;
            }
            if(!keyboardInput.isEmpty(keyboardInput.movementKeyPressed)){
                status=WALKING;
            }
            if(keyboardInput.testKey){
                status=HIT;
            }
        }
        if(previousStatus!=status){
            animationIndex=0;
        }
        previousStatus=status;

    }



    public void reload(GraphicsContext gc){
        updateAnimationIndex(animationLib[status]);
        updatePos();
        updateStatus();
        gc.drawImage(animationLib[status][animationIndex],getCoord().getX(),getCoord().getY() ,sizeX*SCALE,sizeX*SCALE);


    }

}
