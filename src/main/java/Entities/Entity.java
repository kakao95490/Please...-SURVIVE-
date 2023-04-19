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

    public void directionCalcul(){
        Xdirection=-1;
        Ydirection=-1;
        if(coord.centeredCoord().getX()==destCoord.getX() && coord.centeredCoord().getY()==destCoord.getY()){
            return;
        }
        if(destCoord.getX()==0 && destCoord.getY()==0){
            return;
        }
        if(destCoord.getX() == 0){
            if(destCoord.getY()>0){
                Ydirection=DOWN;
                movement.setXY(0,speed);
            } else if (destCoord.getY()<0){
                Ydirection=UP;
                movement.setXY(0,-speed);
            }
            return;
        }
        if(destCoord.getY() == 0){
            if(destCoord.getX()>0){
                Xdirection=RIGHT;
                movement.setXY(speed,0);
            } else if (destCoord.getX()<0){
                Xdirection=LEFT;
                movement.setXY(-speed,0);
            }
            return;
        }
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
