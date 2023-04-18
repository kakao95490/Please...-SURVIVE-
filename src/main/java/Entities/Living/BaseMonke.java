package Entities.Living;

import Entities.Entity;
import utils.Coord;
import utils.Hitbox;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class BaseMonke extends Ennemies {
    public BaseMonke() {
        this.entityName = "BaseMonke";
        this.speed = (int) (3*SCALE);
        this.size = TILE_SIZE;
        this.hitbox.updateHitbox();
        this.status = WALKING;
        generateAnimationLib(); //generate the animation library
    }

}
