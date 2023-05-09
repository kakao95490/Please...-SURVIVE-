package Items;

import Entities.Living.LivingEntity;

public abstract class AbstractItem {
    public String name;
    public int price;

    public abstract void use(LivingEntity entity);
}
