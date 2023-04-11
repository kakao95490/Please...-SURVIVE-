package Caractere;

import Inputs.KeyboardInput;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Game.Game;
import javafx.scene.input.KeyCode;

import static utils.Constants.PlayerConstants.*;

public class Player {
    private String pathSource="file:C:\\Users\\lucas\\Documents\\GitHub\\Please...-SURVIVE-\\resources\\Images\\";
    private static Image[][] animationLib;
    public Image currentSprite;

    private int animationTick = 0;
    private int animationIndex;
    private final int animationSpeed=5;

    private KeyboardInput keyboardInput;

    private int X=0,Y=0;

    private Game game;

    private int playerStatus = WALKING;


    private int spd=5;

    public Player(Game game){
        animationLib=new Image[3][];
        generateAnimationLib();
        this.game=game;
    }

    private void generateAnimationLib() {
        animationLib[STATIC] = new Image[getSpriteAmount(STATIC)];
        animationLib[WALKING] = new Image[getSpriteAmount(WALKING)];
        animationLib[HIT] = new Image[getSpriteAmount(HIT)];

        animationLib[STATIC][0] = new Image(pathSource+"PlayerWalk0.png");

        for(int i=0; i<getSpriteAmount(WALKING);i++){
            animationLib[WALKING][i]=new Image(pathSource+"PlayerWalk"+i+".png");
        }

    }

    private void updateAnimation(Image[] lib){
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
        if(game.keyboardInput.isKeyPressed(KeyCode.Z)){
            this.Y-=spd;
        }
        if(game.keyboardInput.isKeyPressed(KeyCode.S)){
            this.Y+=spd;
        }
        if(game.keyboardInput.isKeyPressed(KeyCode.Q)){
            this.X-=spd;
        }
        if(game.keyboardInput.isKeyPressed(KeyCode.D)){
            this.X+=spd;
        }


    }


    public void reload(GraphicsContext caractere){
        updateAnimation(animationLib[playerStatus]);
        updatePos();
        caractere.clearRect(0,0,1920,1080);
        caractere.setImageSmoothing(false);
        caractere.drawImage(animationLib[playerStatus][animationIndex],X,Y,200,200);


    }

}
