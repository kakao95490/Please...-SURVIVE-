package Objects.Weapons;
import Entities.Living.LivingEntity;
public class Fist extends Weapon{
    public Fist(LivingEntity owner){
        this.damage = 6;
        this.owner=owner;
        this.name="Fist";
    }
}
