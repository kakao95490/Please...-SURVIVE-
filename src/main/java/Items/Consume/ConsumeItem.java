package Items.Consume;

import Entities.Living.LivingEntity;
import utils.Timer;

public abstract class ConsumeItem extends Items.AbstractItem {

    public Timer bonusTimer;

    public abstract void use(LivingEntity entity);
    public abstract void setBonus(LivingEntity entity);
    public abstract void removeBonus(LivingEntity entity);
}
