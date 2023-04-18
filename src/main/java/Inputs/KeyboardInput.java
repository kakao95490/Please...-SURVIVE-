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

        game.camera.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
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

        game.camera.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
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




}