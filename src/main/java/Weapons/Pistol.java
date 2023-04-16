package Weapons;

import Entities.Entity;

public class Pistol extends Weapon{
    public Pistol(Entity owner){
        this.owner = owner;
        this.damage = 10;
        this.range = 10;
        this.cooldown = 60;
        this.currentCooldown = 60;
    }




}
