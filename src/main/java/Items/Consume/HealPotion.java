package Items.Consume;

import Entities.Living.LivingEntity;
import Items.AbstractItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Coord;

public class HealPotion extends ConsumeItem {
    public HealPotion(Coord coord){
        this.name="Heal Potion";
        this.price=50;
        this.sprite = new Image(String.valueOf(getClass().getResource("/Objects/HealPotion.png")));
        this.coord=coord;
        this.onGround = true;
    }

    public HealPotion(){
        this.name="Heal Potion";
        this.price=50;
        this.sprite = new Image(String.valueOf(getClass().getResource("/Objects/HealPotion.png")));
    }


    @Override
    public void use(LivingEntity entity){
        entity.heal(entity.getMaxHp()/2);
    }

    @Override
    public void setBonus(LivingEntity entity) {

    }

    @Override
    public void removeBonus(LivingEntity entity) {

    }

}
