package Entities.Living;

import Entities.Entity;
import Entities.Inert.Bullet;
import Objects.AbstractObjects;
import Objects.Weapons.Weapon;
import javafx.scene.image.Image;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;

public abstract class LivingEntity extends Entity {
    protected Weapon weapon;

    protected int HP;
    protected int maxHP;
    public double dmgMultiplier;
    public boolean isAlive= true;
    public boolean isInvincible = false;
    protected int animationSpeedFPS = 18;
    protected int animationspd = FPS_TARGET/ animationSpeedFPS;
    public int money;



    protected int animationTick = 0;
    public int animationIndex;
    protected int animationHitIndex=0;
    public Image spriteSheet;

    public LivingEntity() {
        super();
    }

    public void updateAnimationIndex(){
        animationTick++;


        if(animationTick>=animationspd){
            animationTick=0;
            animationIndex++;
            if(animationIndex>=getSpriteAmount(status)){
                animationIndex=0;
            }
        }
    }

    public void updateStatus() {
        if(status == DEAD){
            if(animationIndex+1>=getSpriteAmount(DEAD)&& animationTick+1>=animationspd){
                isAlive=false;
            }
        }
        else if(status == HIT) {
            isInvincible=true;
            if (animationIndex+1 >= getSpriteAmount(HIT)&& animationTick+1>=animationspd ) {
                animationHitIndex = 0;
                status = STATIC;
                isInvincible = false;

            }
        }
        else{
            if(movement.getX()==0 && movement.getY()==0 && status!=STATIC){
                status = STATIC;
                animationIndex=0;
            }
            else if(movement.getX()!=0 || movement.getY()!=0){
                status=WALKING;
            }
        }
    }

    public void updateEntityCollisions(Entity entity){
        this.resetCollisions();
        this.detectEntityCollision(entity);
        while((getCollisions()[0] || getCollisions()[1] ||getCollisions()[2] || getCollisions()[3])){
            this.resetCollisions();
            this.detectEntityCollision(entity);
            this.cancelCollision();
        }
    }


    public void gotHitByBullet(Bullet bullet){
        if(bullet.getSource().getOwner()!=this){
            if(hitbox.isColliding(bullet.getHitbox())){
                if(gotHit(bullet.getSource().getOwner())) {
                    bullet.status = STATIC;
                }
            }
        }
    }

    public boolean gotHit(LivingEntity attacker) {
        if(!isInvincible){
            HP -= attacker.getWeapon().getDmg();
            isInvincible = true;
            status = HIT;
            animationIndex = 0;
            animationHitIndex = 0;

            return true;
        }
        return false;
    }


    public boolean checkIfDied(){
        if(HP<=0){
            status=DEAD;
            animationTick=0;
            animationIndex=0;
            hitbox.setHitboxSize(0,0);
            return true;
        }
        return false;
    }



    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; this.weapon.setOwner(this); }


}
