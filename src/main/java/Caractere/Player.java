package Caractere;

import Inputs.KeyboardInput;
import Game.Game;
import utils.Coord;
import utils.Hitbox;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.*;

public class Player extends Entity {

    public KeyboardInput keyboardInput;


    public int speed= (int) (3*SCALE);

    public Player(Game game){


        this.entityName = "Player";

        generateAnimationLib(); //generate the animation library

        keyboardInput=game.keyboardInput;   //get the keyboard input from the game
        this.status=STATIC; //set the player status
        this.coord = new Coord(12*TILE_SIZE, 9*TILE_SIZE); //set the player coord spawn
        this.movement = new Coord(0, 0); //vector of the player movement
        this.hitbox = new Hitbox(coord); //set the player hitbox


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
        hitbox.updateHitbox();
    }


    @Override
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
