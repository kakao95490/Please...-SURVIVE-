package Items.Weapons;


import Entities.Living.LivingEntity;

public class Pistol extends Weapon{
    public Pistol(LivingEntity owner){
        super();
        this.damage = 15;
        this.cooldown = 30;
        this.currentCooldown = this.cooldown;
        this.range*=20;
        this.bulletSize*=5;
        this.owner=owner;
        this.name="Pistol";
        this.price=50;
    }
}
