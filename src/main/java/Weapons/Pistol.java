package Weapons;

import Entities.Entity;

public class Pistol extends Weapon{
    public Pistol(Entity owner){
        this.owner = owner;
        this.damage = 50;
        this.cooldown = 30;
        this.currentCooldown = this.cooldown;
        this.range=60;
        this.bulletSize=15;
    }




}
