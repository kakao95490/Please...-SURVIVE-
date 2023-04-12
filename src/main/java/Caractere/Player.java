package Caractere;

import Inputs.KeyboardInput;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Game.Game;


import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {

    public KeyboardInput keyboardInput;


    public Game game;

    public final int speed=700;


    public int previousPlayerStatus=STATIC;

    public Player(Game game){

        this.game=game;
        this.entityName = "Player";
        animationLib=new Image[3][];
        generateAnimationLib();

        keyboardInput=game.keyboardInput;
        this.status=STATIC;

    }

    public void generateAnimationLib() {
        animationLib[STATIC] = new Image[getSpriteAmount(STATIC)];
        animationLib[WALKING] = new Image[getSpriteAmount(WALKING)];
        animationLib[HIT] = new Image[getSpriteAmount(HIT)];

        animationLib[STATIC][0] = new Image(pathSource+entityName+"Walk0.png");

        for(int i=0; i<getSpriteAmount(WALKING);i++){
            animationLib[WALKING][i]=new Image(pathSource+entityName+"Walk"+i+".png");
        }
        for(int j=0; j<getSpriteAmount(HIT);j++){
            animationLib[HIT][j]=new Image(pathSource+entityName+"Hit"+j+".png");
        }

    }

    public void updateAnimationIndex(Image[] lib){
        animationTick++;
        int animationSpd= (game.framerate/animationSpeedFPS);
        if(animationTick>=animationSpd){
            animationTick=0;
            animationIndex++;
            if(animationIndex>=getSpriteAmount(status)){
                animationIndex=0;
            }
        }
    }

    public void updatePos(){
        int spd= speed;
        spd= (int) (spd*game.deltaTime);

        if(keyboardInput.directionDiagonal()){
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                this.X += spd * 0.80;
                this.Y -= spd * 0.80;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                this.X += spd * 0.80;
                this.Y += spd * 0.80;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                this.X -= spd * 0.80;
                this.Y -= spd * 0.80;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                this.X -= spd * 0.80;
                this.Y += spd * 0.80;
            }
        }
        else {
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP)) {
                this.Y -= spd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN)) {
                this.Y += spd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                this.X -= spd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                this.X += spd;
            }
        }

    }





    public void updateStatus() {
        if(previousPlayerStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
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
        if(previousPlayerStatus!=status){
            animationIndex=0;
        }
        previousPlayerStatus=status;

    }



    public void reload(GraphicsContext gc){
        updateAnimationIndex(animationLib[status]);
        updatePos();
        updateStatus();

    }

}
