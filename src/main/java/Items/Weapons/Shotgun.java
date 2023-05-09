package Items.Weapons;

import Entities.Inert.Bullet;
import Entities.Living.LivingEntity;
import utils.Coord;

import java.util.Random;

import static utils.Constants.WindowConstants.SCALE;

public class Shotgun extends Weapon {
    public Shotgun(LivingEntity owner){
        this.damage = 15;
        this.cooldown = 45;
        this.currentCooldown = this.cooldown;
        this.range*=10;
        this.bulletSize*=4;
        this.owner=owner;
        this.name="Shotgun";
        this.price=200;
    }

    @Override
    public void shoot(){
        if(currentCooldown==cooldown){
            Random random = new Random();

            bullets.add(new Bullet(new Coord(owner.getCoord().getX(), owner.getCoord().getY()), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));
            bullets.add(new Bullet(new Coord(owner.getCoord().getX() +random2(), owner.getCoord().getY() +random2()), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));
            bullets.add(new Bullet(new Coord(owner.getCoord().getX() -random2(), owner.getCoord().getY() +random2()), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));
            bullets.add(new Bullet(new Coord(owner.getCoord().getX() +random2(), owner.getCoord().getY() -random2()), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));
            bullets.add(new Bullet(new Coord(owner.getCoord().getX() -random2(), owner.getCoord().getY() -random2()), owner.getXLookingDirection(), owner.getYLookingDirection(), range, 5,bulletSize,this));


            currentCooldown--;
        }
    }


    public int random2(){
        Random random = new Random();
        return (int) ((random.nextInt(10) + bulletSize/2) * SCALE);
    }

}
