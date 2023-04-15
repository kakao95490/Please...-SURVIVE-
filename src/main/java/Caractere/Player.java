package Caractere;

import Inputs.KeyboardInput;
import Game.Game;
import javafx.scene.shape.Rectangle;
import utils.Coord;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.*;

public class Player extends Entity {

    public KeyboardInput keyboardInput;

    public Game game;

    public int speed= (int) (3*SCALE);

    public Player(Game game){

        this.game=game;

        this.entityName = "Player";

        generateAnimationLib(); //generate the animation library

        keyboardInput=game.keyboardInput;   //get the keyboard input from the game
        this.status=STATIC; //set the player status
        this.coord = new Coord(8*TILE_SIZE, 6*TILE_SIZE); //set the player coord spawn
        this.movement = new Coord(0, 0); //vector of the player movement
        this.tileCoord = coord.tileCoord(); //get the tile coord of the player


    }

    public void updatePos(){

        movement.setXY(0,0);
        if(keyboardInput.directionDiagonal()){
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY((int) (speed * 0.80), (int) (-speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY((int) (speed * 0.80), (int) (speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY((int) (-speed * 0.80), (int) (-speed * 0.80));
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY((int) (-speed * 0.80), (int) (speed * 0.80));
            }
        }
        else {
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP)) {
                movement.addXY(0, -speed);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN)) {
                movement.addXY(0, speed);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY(-speed, 0);
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY(speed, 0);
            }
        }
        coord.addXY(movement.getX(),movement.getY());
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





}
