package Caractere;

import Inputs.KeyboardInput;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Game.Game;
import javafx.scene.input.KeyCode;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class Player extends Entity {

    public KeyboardInput keyboardInput;


    public Game game;


    public int previousPlayerStatus=STATIC;



    public int accelerationIndex=maxDiagSpd;
    public int spd=0;
    public int diagSpd=0;
    public GraphicsContext caractereSprite;


    public Player(Game game){


        this.entityName = "Player";
        this.animationLib=new Image[3][];
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
        this.animationTick++;
        if(this.animationTick>=this.animationSpeed){
            this.animationTick=0;
            this.animationIndex++;
            if(this.animationIndex>=getSpriteAmount(status)){
                this.animationIndex=0;
            }
        }
    }

    public void updatePos(){
        if(keyboardInput.directionDiagonal()){
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                this.X += diagSpd;
                this.Y -= diagSpd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, RIGHT)) {
                this.X += diagSpd;
                this.Y += diagSpd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, UP) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                this.X -= diagSpd;
                this.Y -= diagSpd;
            }
            if (keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, DOWN) && keyboardInput.isKeyPressed(keyboardInput.movementKeyPressed, LEFT)) {
                this.X -= diagSpd;
                this.Y += diagSpd;
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
        updateSpeed();

    }

    public void updateSpeed(){
        if(accelerationIndex>0) {
            accelerationIndex--;
            spd = maxSpd - accelerationIndex;
            diagSpd = maxDiagSpd - accelerationIndex;
        }
    }



    public void updateStatus() {
        if(previousPlayerStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
            status=HIT;
        }
        else{
            if(keyboardInput.isEmpty(keyboardInput.movementKeyPressed)) {
                accelerationIndex = maxDiagSpd;
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
