package Entities.Living;

import Weapons.Pistol;
import utils.Coord;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.*;

public class Player extends LivingEntity {
    public boolean[] movementKeyPressed;
    public boolean[] shootKeyPressed;


    public Player(){
        super();
        this.entityName = "Player";
        this.speed= (int) (3*SCALE);
        this.size = TILE_SIZE;
        generateAnimationLib(); //generate the animation library
        this.status=STATIC; //set the player status
        this.coord.setXY(11*TILE_SIZE, 8*TILE_SIZE);//set the player coord spawn
        this.prevCoord.setXY(coord.getX(),coord.getY());
        playerCoord = coord;
        this.hitbox.setHitboxSize(size/2);
        this.hitbox.setHitboxOffset(size/4,size/2);
        this.hitbox.updateHitbox();
        this.weapon = new Pistol(); //set the player weapon
        this.movementKeyPressed = new boolean[4];
        this.shootKeyPressed = new boolean[4];
    }

    @Override
    public void cancelCollision() {

        updateDirection();
        int[] cancelCollision = {0, 0};
        if(wallCollision[0]){
            if(Xdirection==LEFT){
                cancelCollision[0]+=1;
            }
            if(Ydirection==UP){
                cancelCollision[1]+=1;
            }
        }
        if(wallCollision[1]){
            if(Xdirection==RIGHT){
                cancelCollision[0]-=1;
            }
            if(Ydirection==UP){
                cancelCollision[1]+=1;
            }
        }
        if(wallCollision[3]){
            if(Xdirection==RIGHT){
                cancelCollision[0]-=1;
            }
            if(Ydirection==DOWN){
                cancelCollision[1]-=1;
            }
        }
        if(wallCollision[2]){
            if(Xdirection==LEFT){
                cancelCollision[0]+=1;
            }
            if(Ydirection==DOWN){
                cancelCollision[1]-=1;
            }
        }
        coord.addXY(cancelCollision[0],cancelCollision[1]);


    }





    public void updatePos(){
        movement.setXY(0,0);
        prevCoord.setXY(coord.getX(),coord.getY());
        if(directionDiagonal()){
            if (movementKeyPressed[UP] && movementKeyPressed[RIGHT]) {
                movement.addXY((int) (speed * 0.71), (int) (-speed * 0.71));
            }
            if (movementKeyPressed[DOWN] && movementKeyPressed[RIGHT]) {
                movement.addXY((int) (speed * 0.71), (int) (speed * 0.71));
            }
            if (movementKeyPressed[UP] && movementKeyPressed[LEFT]) {
                movement.addXY((int) (-speed * 0.71), (int) (-speed * 0.71));
            }
            if (movementKeyPressed[DOWN] && movementKeyPressed[LEFT]) {
                movement.addXY((int) (-speed * 0.71), (int) (speed * 0.71));
            }
        }
        else {
            if (movementKeyPressed[UP]) {
                movement.addXY(0, -speed);
            }
            if (movementKeyPressed[DOWN]) {
                movement.addXY(0, speed);
            }
            if (movementKeyPressed[LEFT]) {
                movement.addXY(-speed, 0);
            }
            if (movementKeyPressed[RIGHT]) {
                movement.addXY(speed, 0);
            }
        }
        coord.addXY(movement.getX(),movement.getY());

        hitbox.updateHitbox();
    }







    public void updateShootingDirection(){
        XlookingDirection=-1;
        YlookingDirection=-1;
        if(shootKeyPressed[UP]){
            YlookingDirection=UP;
        }
        if(shootKeyPressed[DOWN]){
            YlookingDirection=DOWN;
        }
        if(shootKeyPressed[LEFT]){
            XlookingDirection=LEFT;
        }
        if(shootKeyPressed[RIGHT]){
            XlookingDirection=RIGHT;
        }
        shooting();
    }

    public void shooting(){
        if(XlookingDirection!=-1 || YlookingDirection!=-1){
            weapon.shoot(coord, XlookingDirection, YlookingDirection);
        }
        weapon.updateCooldown();

    }

    public void updateStatus() {
        if(previousStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
            status=HIT;
        }
        else{
            if(movement.getX()==0 && movement.getY()==0) {
                status = STATIC;
            }
            else{
                status=WALKING;
            }
        }
        if(previousStatus!=status){
            animationIndex=0;
        }
        previousStatus=status;
    }

    boolean directionDiagonal(){
        int count=0;
        for(int i=0;i<4;i++){
            if(movementKeyPressed[i]){
                count++;
            }
        }
        return count == 2;
    }







}
