package Items.Weapons;

import Entities.Inert.Bullet;
import Entities.Living.LivingEntity;
import Items.AbstractItem;

import java.util.ArrayList;

import static utils.Constants.WindowConstants.SCALE;

/**
 * Classe abstraite qui représente une arme
 */

public abstract class Weapon extends AbstractItem {
    protected int damage;
    protected int range= (int) SCALE;
    public int bulletSize= (int) SCALE;
    protected int cooldown;
    protected int currentCooldown;
    protected LivingEntity owner;
    protected ArrayList<Bullet> bullets = new ArrayList<>();


    public Weapon(){
        super();
    }



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


    /**
     * Met à jour le cooldown de l'arme
     */
    public void updateCooldown(){
        if(currentCooldown>0 && currentCooldown!=cooldown){
            currentCooldown--;
        } else if (currentCooldown<=0 ) {
            currentCooldown=cooldown;
        }
    }

    /**
     * Tire un projectile, créer une entité bullet.
     * @see Bullet
     */
    public void shoot(){
        if(cooldown==currentCooldown){
            bullets.add(new Bullet(owner.getCoord(), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));
            currentCooldown--;
        }
    }

    public LivingEntity getOwner(){
        return this.owner;
    }
    public void setOwner(LivingEntity owner){
        this.owner=owner;
    }


    public int getDmg() {
        return damage;
    }




}
