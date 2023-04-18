package Weapons;

import Entities.Bullet;
import Entities.Entity;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import static utils.Constants.Directions.RIGHT;
import static utils.Constants.PlayerConstants.STATIC;

public abstract class Weapon {
    protected int damage;
    protected int range;
    protected int bulletSize;
    protected int cooldown;
    protected int currentCooldown;
    protected ArrayList<Bullet> bullets = new ArrayList<>();
    protected Entity owner;




    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public void updateCoolDown() {
        this.currentCooldown--;
    }


    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void shoot(){
        if(cooldown==currentCooldown){
            bullets.add(new Bullet(this,owner.getCoord(), owner.getXLookingDirection(), owner.getYLookingDirection(), damage, range, 5,bulletSize));
            currentCooldown--;
        }
    }



    public void updateCooldown(){
        if(currentCooldown>0 && currentCooldown!=cooldown){
            currentCooldown--;
        } else if (currentCooldown<=0 ) {
            currentCooldown=cooldown;
        }
    }

    public Entity getOwner(){
        return owner;
    }





}
