package Weapons;
import Entities.Living.LivingEntity;
public class Uzi extends Weapon{
    public Uzi(LivingEntity owner){
        this.damage = 20;
        this.cooldown = 15;
        this.currentCooldown = this.cooldown;
        this.range=40;
        this.bulletSize=5;
        this.owner=owner;
    }
}
