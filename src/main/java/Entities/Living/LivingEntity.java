package Entities.Living;

import Entities.Entity;
import Entities.Inert.Bullet;
import Weapons.Weapon;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;

public abstract class LivingEntity extends Entity {
    protected Weapon weapon;
    protected int speed;

    protected int XlookingDirection=-1;
    protected int YlookingDirection=-1;
    public int getXLookingDirection() {
        return XlookingDirection;
    }
    public int getYLookingDirection() {
        return YlookingDirection;
    }

    protected int animationTick = 0;
    public int animationIndex;
    public Image[][] animationLib;

    public LivingEntity() {
        super();
    }


    public void updateAnimationIndex(){
        animationTick++;
        int animationSpeedFPS = 16;
        int animationspd= FPS_TARGET/ animationSpeedFPS;
        if(animationTick>=animationspd){
            animationTick=0;
            animationIndex++;
            if(animationIndex>=getSpriteAmount(status)){
                animationIndex=0;
            }
        }
    }

    public void generateAnimationLib() {
        animationLib=new Image[3][]; //3 status
        animationLib[STATIC] = new Image[getSpriteAmount(STATIC)];
        animationLib[WALKING] = new Image[getSpriteAmount(WALKING)];
        animationLib[HIT] = new Image[getSpriteAmount(HIT)];

        animationLib[STATIC][0] = new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Walk0.png")).toExternalForm());

        for(int i=0; i<getSpriteAmount(WALKING);i++){
            animationLib[WALKING][i]=new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Walk" + i + ".png")).toExternalForm());
        }
        for(int j=0; j<getSpriteAmount(HIT);j++){
            animationLib[HIT][j]=new Image(Objects.requireNonNull(getClass().getResource("/Sprites/" + this.entityName + "Hit" + j + ".png")).toExternalForm());
        }
    }


    public void shoot(){
        if(weapon.getCooldown() ==weapon.getCurrentCooldown()){
            weapon.getBullets().add(new Bullet(coord, XlookingDirection, YlookingDirection, weapon.getRange(), 5,weapon.bulletSize));
            weapon.setCurrentCooldown(weapon.getCurrentCooldown()-1);
        }
    }


    public Weapon getWeapon() {
        return weapon;
    }
}
