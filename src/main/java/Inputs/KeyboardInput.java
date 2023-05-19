package Inputs;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Game.GameGestion.Game;

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
                game.EntityGestion.getPlayer().movementKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.Z) {
                game.EntityGestion.getPlayer().movementKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.D) {
                game.EntityGestion.getPlayer().movementKeyPressed[RIGHT]=true;
            }
            if(event.getCode() == KeyCode.S) {
                game.EntityGestion.getPlayer().movementKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=true;
            }
            if(event.getCode() == KeyCode.UP){
                game.EntityGestion.getPlayer().shootKeyPressed[UP]=true;
            }
            if(event.getCode() == KeyCode.DOWN){
                game.EntityGestion.getPlayer().shootKeyPressed[DOWN]=true;
            }
            if(event.getCode() == KeyCode.LEFT){
                game.EntityGestion.getPlayer().shootKeyPressed[LEFT]=true;
            }
            if(event.getCode() == KeyCode.RIGHT){
                game.EntityGestion.getPlayer().shootKeyPressed[RIGHT]=true;
            }
            if(event.getCode() == KeyCode.E){
                for(int i=0;i<game.EntityGestion.getPnjList().size();i++){
                    game.EntityGestion.interactWithPnj(game.camera,game.EntityGestion.getPlayer(),game.EntityGestion.getPnjList().get(i));
                }

            }
            if(event.getCode() == KeyCode.DIGIT1){
                game.EntityGestion.getPlayer().itemUseKeyPressed[0]=true;
            }
            if(event.getCode() == KeyCode.DIGIT2){
                game.EntityGestion.getPlayer().itemUseKeyPressed[1]=true;
            }
            if(event.getCode() == KeyCode.DIGIT3){
                game.EntityGestion.getPlayer().itemUseKeyPressed[2]=true;
            }





        });

        game.camera.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if(event.getCode() == KeyCode.Q) {
                game.EntityGestion.getPlayer().movementKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.Z) {
                game.EntityGestion.getPlayer().movementKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.D) {
                game.EntityGestion.getPlayer().movementKeyPressed[RIGHT]=false;
            }
            if(event.getCode() == KeyCode.S) {
                game.EntityGestion.getPlayer().movementKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.T) {
                testKey=false;
            }
            if(event.getCode() == KeyCode.UP){
                game.EntityGestion.getPlayer().shootKeyPressed[UP]=false;
            }
            if(event.getCode() == KeyCode.DOWN){
                game.EntityGestion.getPlayer().shootKeyPressed[DOWN]=false;
            }
            if(event.getCode() == KeyCode.LEFT){
                game.EntityGestion.getPlayer().shootKeyPressed[LEFT]=false;
            }
            if(event.getCode() == KeyCode.RIGHT){
                game.EntityGestion.getPlayer().shootKeyPressed[RIGHT]=false;
            }
            if(event.getCode() == KeyCode.SPACE){
                game.EntityGestion.getPlayer().actionKeyPressed=false;
            }
            if(event.getCode() == KeyCode.DIGIT1){
                game.EntityGestion.getPlayer().itemUseKeyPressed[0]=false;
            }
            if(event.getCode() == KeyCode.DIGIT2){
                game.EntityGestion.getPlayer().itemUseKeyPressed[1]=false;
            }
            if(event.getCode() == KeyCode.DIGIT3){
                game.EntityGestion.getPlayer().itemUseKeyPressed[2]=false;
            }
        });




    }




}