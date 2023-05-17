package Items.Consume;

import Entities.Living.LivingEntity;
import utils.Coord;
import utils.Timer;

import static utils.Constants.WindowConstants.SCALE;

/**
 * Classe abstraite des items consommables.
 */
public abstract class ConsumeItem extends Items.AbstractItem {
    public boolean onGround;
    protected Coord coord;
    public Timer bonusTimer;
    protected int size;

    public ConsumeItem() {
        super();
        this.onGround = false;
        this.coord = new Coord(0,0);
        this.bonusTimer = new Timer(0);
        this.size = (int) (16*SCALE);
    }


    public int getSize() {
        return size;
    }

    public Coord getCoord() {
        return coord;
    }


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
