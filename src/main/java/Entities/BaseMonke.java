package Entities;

import utils.Coord;
import utils.Hitbox;

import static utils.Constants.MapConstants.TILE;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class BaseMonke extends Entity {
    public BaseMonke() {
        this.entityName = "BaseMonke";
        this.speed = (int) (3*SCALE);
        this.coord = new Coord(12, 9);
        this.movement = new Coord(0, 0);
        this.size = TILE_SIZE;
        this.hitbox = new Hitbox(coord,TILE_SIZE/2,size/4,size/2);
        this.status = WALKING;
        generateAnimationLib(); //generate the animation library


    }

    @Override
    public void updatePos() {

    }

    @Override
    public void updateStatus() {

    }
}
