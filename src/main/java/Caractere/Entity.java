package Caractere;

import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;


import utils.Coord;

public abstract class Entity implements InterfaceEntity {
    protected Coord coord;
    protected Coord tileCoord;
    protected Coord movement;


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

    public Coord getTileCoord() {
        return tileCoord;
    }
    public void setTileCoord(){
        tileCoord.setX(coord.getX()/TILE_SIZE);
        tileCoord.setY(coord.getY()/TILE_SIZE);
    }
}
