package Items.Weapons;
import Entities.Living.LivingEntity;
public class Uzi extends Weapon{
    public Uzi(LivingEntity owner){
        this.damage = 6;
        this.cooldown = 5;
        this.currentCooldown = this.cooldown;
        this.range*=15;
        this.bulletSize*=3;
        this.owner=owner;
        this.name="Uzi";
        this.price=100;
    }

}
