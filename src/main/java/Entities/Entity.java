package Entities;


import static utils.Constants.Directions.*;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.PlayerConstants.*;

import utils.Coord;
import utils.Hitbox;

public abstract class Entity {
    protected Coord coord;
    protected Coord destCoord;
    public static Coord playerCoord;
    protected Coord spawnPoint;
    protected Coord movement;
    protected Hitbox hitbox;
    protected int speed;

    protected int Xdirection=-1;
    protected int Ydirection=-1;


    Boolean[] wallCollision = {false, false, false, false};

    public int size;
    public int status;
    protected int previousStatus=STATIC;

    public String entityName;

    public Entity() {
        this.coord = new Coord(0,0);
        this.spawnPoint = new Coord(0,0);
        this.destCoord = new Coord(0,0);
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

    /**
     * This method is used to calculate the angle between the entity and the distination
     * and then calculate the x and y movement based on the angle and the speed
     *
     */
    public void directionCalcul(){
        Xdirection=-1;
        Ydirection=-1;
        int xdif = destCoord.getX()-coord.centeredCoord().getX();
        int ydif = destCoord.getY()-coord.centeredCoord().getY();
        double angle = Math.atan2(ydif,xdif);
        double px =  Math.cos(angle);
        double py =  Math.sin(angle);
        double x = px*speed;
        double y = py*speed;
        if(x>0){
            Xdirection=RIGHT;
        } else if (x<0){
            Xdirection=LEFT;
        }
        if(y>0){
            Ydirection=DOWN;
        } else if (y<0){
            Ydirection=UP;
        }
        movement.setXY((int)x,(int)y);
    }

    public int getEntityCollisionDirection(Entity entity) {
        if (hitbox.isCollidingFromLeft(entity.getHitbox())) {
            return LEFT;

        }
        if (hitbox.isCollidingFromRight(entity.getHitbox())) {
            return RIGHT;
        }
        return -1;
    }

    public void cancelEntityCollision(Entity entity){
        if(getEntityCollisionDirection(entity)==LEFT){
            while(hitbox.isCollidingFromLeft(entity.getHitbox())){
                coord.addXY(-1,0);
                hitbox.updateHitbox();
            }
        }
        if(getEntityCollisionDirection(entity)==RIGHT){
            while(hitbox.isCollidingFromRight(entity.getHitbox())){
                coord.addXY(1,0);
                hitbox.updateHitbox();
            }
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
