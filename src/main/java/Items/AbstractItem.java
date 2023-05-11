package Items;

import Entities.Living.LivingEntity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Timer;

public abstract class AbstractItem {
    public String name;
    public int price;
    public Image sprite;
    public Timer bonusTimer;

    public abstract void use(LivingEntity entity);
    public abstract void setBonus(LivingEntity entity);
    public abstract void removeBonus(LivingEntity entity);
}
