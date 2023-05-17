package Entities.Living.GoodGuys;

import Entities.Living.LivingEntity;
import Items.AbstractItem;
import Items.Consume.BigDmg;
import Items.Consume.ConsumeItem;
import Items.Consume.HealPotion;
import Items.Weapons.Pistol;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.*;

public class Player extends LivingEntity {
    public boolean[] movementKeyPressed = new boolean[4];
    public boolean[] shootKeyPressed = new boolean[4];
    public boolean[] itemUseKeyPressed = new boolean[3];
    public boolean actionKeyPressed;
    public boolean isOnMenu;
    public ArrayList<AbstractItem> temporaryBonus = new ArrayList<>();

    public Player(){
        super();

        this.entityName = "Player";
        this.spriteSize=64;
        this.size = TILE_SIZE;

        this.money=0;
        this.speed= (int) (3*SCALE);
        this.HP=100;
        this.maxHP=100;
        this.dmgMultiplier=1;

        this.weapon = new Pistol(this); //set the player weapon

        this.status=STATIC; //set the player status
        this.coord.setXY(17*TILE_SIZE, 11*TILE_SIZE);//set the player coord spawn
        this.prevCoord.setXY(coord.getX(),coord.getY());
        playerCoord = coord;

        this.hitbox.setHitboxSize(size/2,size/2);
        this.hitbox.setHitboxOffset(size/4,size/2);

        this.isOnMenu = false;

        this.spriteSheet = new Image(Objects.requireNonNull(getClass().getResource("/Sprites/PlayerSPRITESHEET.png")).toExternalForm());

        this.inventory = new ConsumeItem[3];
    }


    @Override
    public void cancelCollision() {

        updateDirection();
        int[] cancelCollision = {0, 0};
        if(collisions[0]){
            if(Xdirection==LEFT){
                cancelCollision[0]+=1;
            }
            if(Ydirection==UP){
                cancelCollision[1]+=1;
            }
        }
        if(collisions[1]){
            if(Xdirection==RIGHT){
                cancelCollision[0]-=1;
            }
            if(Ydirection==UP){
                cancelCollision[1]+=1;
            }
        }
        if(collisions[3]){
            if(Xdirection==RIGHT){
                cancelCollision[0]-=1;
            }
            if(Ydirection==DOWN){
                cancelCollision[1]-=1;
            }
        }
        if(collisions[2]){
            if(Xdirection==LEFT){
                cancelCollision[0]+=1;
            }
            if(Ydirection==DOWN){
                cancelCollision[1]-=1;
            }
        }
        coord.addXY(cancelCollision[0],cancelCollision[1]);
        hitbox.updateHitbox();


    }

    public void useItem(){
        for(int i=0;i<3;i++){
            if(itemUseKeyPressed[i]){
                if(inventory[i]!=null){
                    inventory[i].use(this);
                    inventory[i]=null;
                }
            }
        }
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
            weapon.shoot();
        }
        weapon.updateCooldown();

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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void addSpeed(int speed){
        System.out.println(this.speed);
        this.speed += (speed*SCALE)/2 + 1;
    }

    public void addDmg(double multiplicator){
        this.dmgMultiplier += multiplicator;
    }

    public boolean isNearEntity( LivingEntity entity){
        return this.getCoord().distance(entity.getCoord()) < entity.getDetectionRange();
    }


    public void addItem(ConsumeItem consumeItem) {
        if(inventory[0]==null){
            inventory[0]=consumeItem;
        }
        else if(inventory[1]==null){
            inventory[1]=consumeItem;
        }
        else if(inventory[2]==null){
            inventory[2]=consumeItem;
        }
    }
}
