package Weapons;
import Entities.Living.LivingEntity;
public class Fist extends Weapon{
    public Fist(LivingEntity owner){
        this.damage = (int) (5*owner.dmgMultiplier);
        this.owner=owner;
    }
}
