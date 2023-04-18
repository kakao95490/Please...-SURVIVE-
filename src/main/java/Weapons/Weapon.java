package Weapons;

import Entities.Inert.Bullet;
import Entities.Entity;
import Entities.Living.LivingEntity;

import java.util.ArrayList;

public abstract class Weapon {
    protected int damage;
    protected int range;
    public int bulletSize;
    protected int cooldown;
    protected int currentCooldown;
    protected ArrayList<Bullet> bullets = new ArrayList<>();




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





    public void updateCooldown(){
        if(currentCooldown>0 && currentCooldown!=cooldown){
            currentCooldown--;
        } else if (currentCooldown<=0 ) {
            currentCooldown=cooldown;
        }
    }


}
