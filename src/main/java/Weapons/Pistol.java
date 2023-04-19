package Weapons;


import Entities.Inert.Bullet;
import Entities.Living.LivingEntity;

public class Pistol extends Weapon{
    public Pistol(){
        this.damage = 50;
        this.cooldown = 30;
        this.currentCooldown = this.cooldown;
        this.range=60;
        this.bulletSize=15;
    }







}
