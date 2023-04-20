package Entities;


import static utils.Constants.Directions.*;
import static utils.Constants.Directions.LEFT;
import static utils.Constants.PlayerConstants.*;

import utils.Coord;
import utils.Hitbox;

public abstract class Entity {
    protected Coord coord;
    protected Coord prevCoord;
    protected Coord destCoord;
    public static Coord playerCoord;
    protected Coord spawnPoint;
    protected Coord movement;
    protected Hitbox hitbox;
    protected int speed;

    protected int Xdirection=-1;
    protected int Ydirection=-1;


    protected Boolean[] collisions = {false, false, false, false};

    public int size;
    public int status;
    protected int previousStatus=STATIC;

    public String entityName;

    public Entity() {
        this.coord = new Coord(0,0);
        this.prevCoord = new Coord(0,0);
        this.spawnPoint = new Coord(0,0);
        this.destCoord = new Coord(0,0);
        this.movement = new Coord(0,0);
        this.hitbox = new Hitbox(coord,0,0,0);
    }



    public abstract void cancelCollision();






    public void updateDirection(){
        if(prevCoord.getX()<coord.getX()){
            Xdirection=RIGHT;
        } else if (prevCoord.getX()>coord.getX()){
            Xdirection=LEFT;
        }
        if(prevCoord.getY()<coord.getY()){
            Ydirection=DOWN;
        } else if (prevCoord.getY()>coord.getY()){
            Ydirection=UP;
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
        movement.setXY((int)x,(int)y);
    }



    public void detectEntityCollision(Entity entity){
        if(entity.getHitbox().isCollidingFromTopLeft(this.getHitbox())){
            collisions[0]=true;
        }
        if(entity.getHitbox().isCollidingFromTopRight(this.getHitbox())){
            collisions[1]=true;
        }
        if(entity.getHitbox().isCollidingFromBottomLeft(this.getHitbox())){
            collisions[2]=true;
        }
        if(entity.getHitbox().isCollidingFromBottomRight(this.getHitbox())){
            collisions[3]=true;
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


    public Boolean[] getCollisions() {
        return collisions;
    }

    public void setCollision(int i,Boolean b) {
        this.collisions[i] = b;
    }

    public void resetCollisions(){
        for(int i=0;i<4;i++){
            collisions[i]=false;
        }
    }

    public Coord getCoord() {
        return coord;
    }
}
