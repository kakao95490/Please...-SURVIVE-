package Items.Consume;

import Entities.Living.LivingEntity;
import utils.Timer;

/**
 * Classe abstraite des items consommables.
 */
public abstract class ConsumeItem extends Items.AbstractItem {

    public Timer bonusTimer;

    /**
     * Utilise l'item.
     * @param entity Entité qui utilise l'item.
     */
    public abstract void use(LivingEntity entity);

    /**
     * Applique le bonus de l'item.
     * @param entity
     */
    public abstract void setBonus(LivingEntity entity);

    /**
     * Retire le bonus de l'entité.
     * @param entity
     */
    public abstract void removeBonus(LivingEntity entity);
}
