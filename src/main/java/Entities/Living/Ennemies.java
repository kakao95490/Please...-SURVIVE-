package Entities.Living;

import utils.Coord;

import static utils.Constants.EntityConstants.getSpawnPoint;

public abstract class Ennemies extends LivingEntity{


    public Ennemies() {
        super();
        setSpawnPoint();
    }

    public void setSpawnPoint() {
        int max = 10;
        int min = 0;
        int nbRand = (int) (Math.random() * ( max - min ));
        this.coord=getSpawnPoint(nbRand);
    }
}
