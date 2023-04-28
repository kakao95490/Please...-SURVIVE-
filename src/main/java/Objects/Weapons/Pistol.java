package Objects.Weapons;


import Entities.Living.LivingEntity;

public class Pistol extends Weapon{
    public Pistol(LivingEntity owner){
        super();
        this.damage = 10;
        this.cooldown = 30;
        this.currentCooldown = this.cooldown;
        this.range=60;
        this.bulletSize=15;
        this.owner=owner;
        this.name="Pistol";
        this.price=50;
    }








}
