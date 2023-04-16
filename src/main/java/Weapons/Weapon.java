package Weapons;

import Entities.Bullet;
import Entities.Entity;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

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

        }
        else if(currentCooldown>0){
            currentCooldown--;
        }
        else if(currentCooldown==0){
            currentCooldown=cooldown;
        }
        System.out.println("pew");
        bullets.add(new Bullet(owner.getCoord(), owner.getXDirection(), owner.getYDirection(), damage, range, 10));
        currentCooldown--;
    }

    public void updateBullets(GraphicsContext g){
        for(Bullet bullet : bullets){
            bullet.updatePos();
            bullet.getHitbox().updateHitbox();
            bullet.render(g);
        }
    }






}
