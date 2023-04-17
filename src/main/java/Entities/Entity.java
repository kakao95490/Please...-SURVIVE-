package Entities;

import Weapons.Weapon;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;

import utils.Coord;
import utils.Hitbox;

public abstract class Entity implements InterfaceEntity {
    protected Coord coord;
    protected Coord movement;
    protected Hitbox hitbox;

    protected int Xdirection=-1;
    protected int Ydirection=-1;
    protected int XlookingDirection=-1;
    protected int YlookingDirection=-1;

    protected int speed;

    Boolean[] wallCollision = {false, false, false, false};


    protected Weapon weapon;




    protected int animationTick = 0;

    public int animationIndex;
    public Image[][] animationLib;

    public int status;
    protected int previousStatus=STATIC;

    public String entityName;


    @Override
    public void updateAnimationIndex(Image[] lib){
        animationTick++;
        int animationSpeedFPS = 16;
        int animationspd= FPS_TARGET/ animationSpeedFPS;
        if(animationTick>=animationspd){
            animationTick=0;
            animationIndex++;
            if(animationIndex>=getSpriteAmount(status)){
                animationIndex=0;
            }
        }
    }

    @Override
    public void generateAnimationLib() {
        animationLib=new Image[3][]; //3 status
        animationLib[STATIC] = new Image[getSpriteAmount(STATIC)];
        animationLib[WALKING] = new Image[getSpriteAmount(WALKING)];
        animationLib[HIT] = new Image[getSpriteAmount(HIT)];

        animationLib[STATIC][0] = new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Walk0.png")).toExternalForm());

        for(int i=0; i<getSpriteAmount(WALKING);i++){
            animationLib[WALKING][i]=new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Walk" + i + ".png")).toExternalForm());
        }
        for(int j=0; j<getSpriteAmount(HIT);j++){
            animationLib[HIT][j]=new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Hit" + j + ".png")).toExternalForm());
        }
    }

    public Coord getCoord() {
        return coord;
    }


    public void updateDirection(){
        Xdirection=-1;
        Ydirection=-1;
        if(movement.getY()>0){
            Ydirection=DOWN;
        } else if (movement.getY()<0){
            Ydirection=UP;
        }
        if(movement.getX()>0){
            Xdirection=RIGHT;
        } else if (movement.getX()<0){
            Xdirection=LEFT;
        }

    }





    public Hitbox getHitbox() {
        return hitbox;
    }
    public int getXDirection() {
        return Xdirection;
    }
    public int getYDirection() {
        return Ydirection;
    }
    public int getXLookingDirection() {
        return XlookingDirection;
    }
    public int getYLookingDirection() {
        return YlookingDirection;
    }

    public Boolean[] getWallCollision() {
        return wallCollision;
    }

    public void setWallCollision(int i,Boolean b) {
        this.wallCollision[i] = b;
    }

    public void resetWallCollision(){
        for(int i=0;i<4;i++){
            wallCollision[i]=false;
        }
    }

    public Coord getMovement(){
        return movement;
    }


    public int getSpeed() {
        return speed;
    }


    public Weapon getWeapon(){
        return weapon;
    }
}
