package Weapons;

import Entities.Inert.Bullet;
import Entities.Living.LivingEntity;
import utils.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite qui représente une arme
 */

public abstract class Weapon {
    protected int damage;
    protected int range;
    public int bulletSize;
    protected int cooldown;
    protected int currentCooldown;
    protected LivingEntity owner;
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

    public void shoot(Coord ownerCoord, int XlookingDirection, int YlookingDirection){
        if(cooldown==currentCooldown){
            bullets.add(new Bullet(ownerCoord, XlookingDirection, YlookingDirection, range, 5,bulletSize,this));
            currentCooldown--;
        }
    }

    public LivingEntity getOwner(){
        return this.owner;
    }


    public int getDmg() {
        return damage;
    }
}
