package Weapons;


import Entities.Living.LivingEntity;

public class Pistol extends Weapon{
    public Pistol(LivingEntity owner){
        this.damage = (int) (10*owner.dmgMultiplier);
        this.cooldown = 30;
        this.currentCooldown = this.cooldown;
        this.range=60;
        this.bulletSize=15;
        this.owner=owner;
    }







}
