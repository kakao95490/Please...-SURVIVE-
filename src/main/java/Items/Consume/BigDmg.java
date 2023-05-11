package Items.Consume;

import Entities.Living.LivingEntity;
import Items.AbstractItem;
import javafx.scene.image.Image;
import utils.Timer;

public class BigDmg extends ConsumeItem {


    public BigDmg(){
        this.name="Temporary damage +";
        this.price=150;
        this.sprite = new Image(String.valueOf(getClass().getResource("/Objects/BigDmg.png")));
        this.bonusTimer = new Timer(10);
    }

    public void setBonus(LivingEntity entity){
        entity.dmgMultiplier *= 2;
        entity.getWeapon().bulletSize *= 2;
    }

    public void removeBonus(LivingEntity entity){
        entity.dmgMultiplier /= 2;
        entity.getWeapon().bulletSize /= 2;
    }


    public void use(LivingEntity entity){
        entity.temporaryBonus.add(this);
        setBonus(entity);
        bonusTimer.updateTimer();
    }

}
