package Entities.Living;

import Entities.Entity;
import Entities.Inert.Bullet;
import Entities.Living.GoodGuys.Player;
import Items.AbstractItem;
import Items.Weapons.Weapon;
import javafx.scene.image.Image;

import java.util.ArrayList;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;


public abstract class LivingEntity extends Entity {
    protected Weapon weapon;
    public AbstractItem[] inventory;
    public ArrayList<AbstractItem> temporaryBonus = new ArrayList<>();

    protected int HP;
    protected int maxHP;
    public double dmgMultiplier;
    public boolean isAlive= true;
    public boolean isInvincible = false;
    protected int animationSpeedFPS = 18;
    protected int animationspd = FPS_TARGET/ animationSpeedFPS;
    public int money;
    public boolean cantMove = false;



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
            if (animationIndex+1 >= getSpriteAmount(HIT)&& animationTick+1>=animationspd ) {
                animationHitIndex = 0;
                status = STATIC;
                isInvincible = false;
                cantMove = false;

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
            if(this instanceof Player){
                ((Player) this).isInvincible=true;
            }
            HP -= attacker.getWeapon().getDmg() * attacker.dmgMultiplier;
            if(attacker instanceof Player && 0>=this.HP){
                ((Player) attacker).money+=this.money;
            }
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

    public void heal( int healAmount ) {
        HP += healAmount;
        if( HP > maxHP ) HP = maxHP;
    }

    public int getMaxHp() {
        return maxHP;
    }


    public boolean inventoryIsFull() {
        for (AbstractItem item : inventory) {
            if (item == null) return false;
        }
        return true;
    }

    public void updateBonus(){
        for(AbstractItem item : temporaryBonus){
            item.bonusTimer.updateTimer();
            if(item.bonusTimer.isFinished()){
                item.removeBonus(this);
                temporaryBonus.remove(item);
            }
        }
    }
}
