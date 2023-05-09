package Items.Consume;

import Entities.Living.LivingEntity;
import Items.AbstractItem;

public class HealPotion extends AbstractItem {
    public HealPotion(){
        this.name="Heal Potion";
        this.price=50;
    }

    @Override
    public void use(LivingEntity entity){
        entity.heal(entity.getMaxHp()/2);
    }

}
