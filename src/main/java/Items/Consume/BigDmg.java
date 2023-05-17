package Items.Consume;

import Entities.Living.LivingEntity;
import javafx.scene.image.Image;
import utils.Coord;
import utils.Timer;

public class BigDmg extends ConsumeItem {

    public BigDmg(Coord coord){
        this.name="Temporary damage +";
        this.price=150;
        this.sprite = new Image(String.valueOf(getClass().getResource("/Objects/BigDmg.png")));
        this.bonusTimer = new Timer(10);
        this.coord=coord;
        this.onGround = true;
    }

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
