package Entities;


import static utils.Constants.Directions.*;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.PlayerConstants.*;

import utils.Coord;
import utils.Hitbox;

public abstract class Entity {
    protected Coord coord;
    protected Coord movement;
    protected Hitbox hitbox;

    protected int Xdirection=-1;
    protected int Ydirection=-1;


    Boolean[] wallCollision = {false, false, false, false};

    public int size;
    public int status;
    protected int previousStatus=STATIC;

    public String entityName;

    public Entity() {
        this.coord = new Coord(0,0);
        this.movement = new Coord(0,0);
        this.hitbox = new Hitbox(coord,0,0,0);
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

    public Coord getCoord() {
        return coord;
    }
}
