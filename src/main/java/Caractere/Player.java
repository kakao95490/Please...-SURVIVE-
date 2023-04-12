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

    private KeyboardInput keyboardInput;


    private Game game;

    private int playerStatus = STATIC;
    private int previousPlayerStatus=STATIC;



    private int accelerationIndex=maxDiagSpd;
    private int spd=0;
    private int diagSpd=0;
    private GraphicsContext caractereSprite;


    public Player(Game game){
        this.animationLib=new Image[3][];
        generateAnimationLib();
        this.game=game;
        keyboardInput=game.keyboardInput;
        caractereSprite = game.getCanvas().getGraphicsContext2D();
        caractereSprite.setImageSmoothing(false);


    }

    private void generateAnimationLib() {
        animationLib[STATIC] = new Image[getSpriteAmount(STATIC)];
        animationLib[WALKING] = new Image[getSpriteAmount(WALKING)];
        animationLib[HIT] = new Image[getSpriteAmount(HIT)];

        animationLib[STATIC][0] = new Image(pathSource+"PlayerWalk0.png");

        for(int i=0; i<getSpriteAmount(WALKING);i++){
            animationLib[WALKING][i]=new Image(pathSource+"PlayerWalk"+i+".png");
        }
        for(int j=0; j<getSpriteAmount(HIT);j++){
            animationLib[HIT][j]=new Image(pathSource+"PlayerHit"+j+".png");
        }

    }

    private void updateAnimationIndex(Image[] lib){
        this.animationTick++;
        if(this.animationTick>=this.animationSpeed){
            this.animationTick=0;
            this.animationIndex++;
            if(this.animationIndex>=getSpriteAmount(playerStatus)){
                this.animationIndex=0;
            }
        }
    }

    void updatePos(){
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

    void updateSpeed(){
        if(accelerationIndex>0) {
            accelerationIndex--;
            spd = maxSpd - accelerationIndex;
            diagSpd = maxDiagSpd - accelerationIndex;
        }
    }



    void updateStatus() {
        if(previousPlayerStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
            playerStatus=HIT;
        }
        else{
            if(keyboardInput.isEmpty(keyboardInput.movementKeyPressed)) {
                accelerationIndex = maxDiagSpd;
                playerStatus = STATIC;
            }
            if(!keyboardInput.isEmpty(keyboardInput.movementKeyPressed)){
                playerStatus=WALKING;
            }
            if(keyboardInput.testKey){
                playerStatus=HIT;
            }
        }
        if(previousPlayerStatus!=playerStatus){
            animationIndex=0;
        }
        previousPlayerStatus=playerStatus;

    }



    public void reload(GraphicsContext gc){
        updateAnimationIndex(animationLib[playerStatus]);
        updatePos();
        updateStatus();
        gc.clearRect(0,0,1920,1080);
        gc.drawImage(animationLib[playerStatus][animationIndex],X,Y,200,200);
    }

}
