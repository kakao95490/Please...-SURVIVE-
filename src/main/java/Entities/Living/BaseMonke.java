package Entities.Living;

import utils.Coord;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class BaseMonke extends Enemies {
    public BaseMonke(int x, int y) {
        this.coord.setXY(x*TILE_SIZE, y*TILE_SIZE);
        this.prevCoord.setXY(coord.getX(),coord.getY());
        this.entityName = "BaseMonke";
        this.speed = (int) (2*SCALE);
        this.size = TILE_SIZE;
        this.hitbox.setHitboxSize(size/2);
        this.hitbox.setHitboxOffset(size/4,size/2);
        this.hitbox.updateHitbox();
        this.status = WALKING;
        generateAnimationLib(); //generate the animation library
        System.out.println("basemonke spawn");
        System.out.println(coord.tileCoord());
    }

}
