package caractere;

import utils.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;

public class Player implements InterfaceCaractere {
    private int hp;
    private int dmg;
    private final int spd = 5;
    private int dmgspd;
    private int xPos = 0;
    private int yPos = 0;
    private BufferedImage sprite;
    public BufferedImage[][] animationLib;
    private int animationTick;
    private int animationIndex;
    private final int animationSpeed=5;

    private int playerAction = STATIC;
    private int playerDir = -1;
    private boolean moving = false;


    public Player(){
        this.animationLib = new BufferedImage[3][];
        loadAnimLib("/resources/Images/");
    }


    //load image lib for walk animation
    @Override
    public void loadAnimLib(String str) {
        this.animationLib[STATIC] = new BufferedImage[Constants.PlayerConstants.getSpriteAmount(STATIC)];
        this.animationLib[WALKING] = new BufferedImage[Constants.PlayerConstants.getSpriteAmount(WALKING)];
        this.animationLib[HIT] = new BufferedImage[Constants.PlayerConstants.getSpriteAmount(HIT)];
        for(int i = 0; i < Constants.PlayerConstants.getSpriteAmount(STATIC); i++){
            this.animationLib[STATIC][i]=importImg(str+"PlayerWalk"+i+".png");
        }
        for(int i = 0; i < Constants.PlayerConstants.getSpriteAmount(WALKING); i++){
            this.animationLib[WALKING][i]=importImg(str+"PlayerWalk"+i+".png");
        }
    }

    @Override
    public void updateAnimation(BufferedImage[] animLib){
        this.animationTick++;
        if(this.animationTick >=this.animationSpeed){
            this.animationTick=0;
            this.animationIndex++;
            if(this.animationIndex >= getSpriteAmount(playerAction)){
                this.animationIndex = 0;
            }
        }

    }

    @Override
    public BufferedImage importImg(String str) {
        InputStream is = getClass().getResourceAsStream(str);
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void setPlayerAction(int playerAction) {
        if(playerAction!=this.playerAction){
            this.animationIndex=0;
        }
        this.playerAction = playerAction;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDirection(int direction){
        this.playerDir = direction;
        if(direction!=-1){
            moving=true;
        }
    }

    private void setAnimation(){
        if(moving){
            playerAction=WALKING;
        }
        else{
            playerAction=STATIC;
        }
    }

    private void updatePos(){
        if (moving) {
            switch (playerDir) {
                case LEFT -> xPos -= spd;
                case RIGHT -> xPos += spd;
                case UP -> yPos -= spd;
                case DOWN -> yPos += spd;
            }
        }
    }

    public void reload(Graphics g){
        updateAnimation(this.animationLib[playerAction]);
        setAnimation();
        updatePos();
        g.drawImage(this.animationLib[playerAction][this.animationIndex],this.xPos,this.yPos,200,200,null);

    }




}
