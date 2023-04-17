package Weapons;

import Entities.Bullet;
import Entities.Entity;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import static utils.Constants.Directions.RIGHT;
import static utils.Constants.PlayerConstants.STATIC;

public abstract class Weapon {
    protected int damage;
    protected int range;
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
            bullets.add(new Bullet(owner.getCoord(), owner.getXLookingDirection(), owner.getYLookingDirection(), damage, range, 8));
            currentCooldown--;
        }
    }

    public void updateBullets(GraphicsContext g){
        for(Bullet bullet : bullets){
            if(bullet.status==STATIC){
                bullets.remove(bullet);
            }
            else {
                bullet.updateStatus();
                bullet.updatePos();
                bullet.getHitbox().updateHitbox();

                bullet.render(g);
            }
        }
    }

    public void updateCooldown(){
        if(currentCooldown>0 && currentCooldown!=cooldown){
            currentCooldown--;
        } else if (currentCooldown<=0 ) {
            currentCooldown=cooldown;
        }
    }






}