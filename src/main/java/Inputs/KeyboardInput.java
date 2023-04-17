package Inputs;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Game.Game;

import static utils.Constants.Directions.*;

public class KeyboardInput {
    public boolean[] movementKeyPressed;
    public boolean[] shootKeyPressed;
    public boolean testKey;


    public KeyboardInput() {
        movementKeyPressed = new boolean[4];
        shootKeyPressed = new boolean[4];

        Game.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.Q) {
                movementKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.Z) {
                movementKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.D) {
                movementKeyPressed[RIGHT]=true;
            }
            if(event.getCode() == KeyCode.S) {
                movementKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=true;
            }
            if(event.getCode() == KeyCode.UP){
                shootKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.DOWN){
                shootKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.LEFT){
                shootKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.RIGHT){
                shootKeyPressed[RIGHT]=true;
            }




        });

        Game.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.Q) {
                movementKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.Z) {
                movementKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.D) {
                movementKeyPressed[RIGHT]=false;
            }
            if(event.getCode() == KeyCode.S) {
                movementKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=false;
            }
            if(event.getCode() == KeyCode.UP){
                shootKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.DOWN){
                shootKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.LEFT){
                shootKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.RIGHT){
                shootKeyPressed[RIGHT]=false;
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