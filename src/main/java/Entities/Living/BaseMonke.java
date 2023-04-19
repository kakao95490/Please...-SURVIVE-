package Entities.Living;

import utils.Coord;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class BaseMonke extends Enemies {
    public Coord spriteCoord;
    public BaseMonke(int x, int y) {
        this.coord.setXY((int) (x*TILE_SIZE), (int) (y*TILE_SIZE));
        this.entityName = "BaseMonke";
        this.speed = (int) (2*SCALE);
        System.out.println(coord);
        this.size = TILE_SIZE;
        this.hitbox.setHitboxSize(size);
        //this.hitbox.setHitboxOffset(size/4,size/2);
        this.hitbox.updateHitbox();
        this.status = WALKING;
        generateAnimationLib(); //generate the animation library
    }


}
