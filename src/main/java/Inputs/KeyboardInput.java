package Inputs;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Game.Game;

import static utils.Constants.Directions.*;

public class KeyboardInput {
    public boolean[] movementKeyPressed;
    public boolean[] shootKeyPressed;
    public boolean testKey;


    public KeyboardInput(Game game) {
        movementKeyPressed = new boolean[4];
        shootKeyPressed = new boolean[4];

        game.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.Q) {
                game.player.movementKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.Z) {
                game.player.movementKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.D) {
                game.player.movementKeyPressed[RIGHT]=true;
            }
            if(event.getCode() == KeyCode.S) {
                game.player.movementKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=true;
            }
            if(event.getCode() == KeyCode.UP){
                game.player.shootKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.DOWN){
                game.player.shootKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.LEFT){
                game.player.shootKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.RIGHT){
                game.player.shootKeyPressed[RIGHT]=true;
            }




        });

        game.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.Q) {
                game.player.movementKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.Z) {
                game.player.movementKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.D) {
                game.player.movementKeyPressed[RIGHT]=false;
            }
            if(event.getCode() == KeyCode.S) {
                game.player.movementKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=false;
            }
            if(event.getCode() == KeyCode.UP){
                game.player.shootKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.DOWN){
                game.player.shootKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.LEFT){
                game.player.shootKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.RIGHT){
                game.player.shootKeyPressed[RIGHT]=false;
            }
        });

    }

    public boolean isEmpty(boolean[] inputTab){
        for (boolean b : inputTab) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    public boolean directionDiagonal(){
        int inputNbr = 0;
        for (boolean b : movementKeyPressed) {
            if (b) {
                inputNbr += 1;
            }
        }
        return inputNbr == 2 ;
    }


    public boolean isKeyPressed(boolean[] inputTab,int key) {
        return inputTab[key];
    }

}