package Entities.Living.Enemies;

import Weapons.Fist;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class BaseMonke extends Enemies {
    public BaseMonke(int x, int y) {
        this.coord.setXY(x*TILE_SIZE, y*TILE_SIZE);
        this.prevCoord.setXY(coord.getX(),coord.getY());
        this.entityName = "BaseMonke";

        this.spriteSize = 64;
        this.size = TILE_SIZE;
        this.hitbox.setHitboxSize(size/3, size-size/3);
        this.hitbox.setHitboxOffset(size/3,size/3);
        this.hitbox.updateHitbox();
        this.status = WALKING;
        this.spriteSheet =new Image(Objects.requireNonNull(getClass().getResource("/Sprites/BaseMonkeSPRITESHEET.png")).toExternalForm());


        this.speed = (int) (2*SCALE);
        this.maxHP = 50;
        this.HP = maxHP;
        this.dmgMultiplier = 1;
        this.weapon = new Fist(this);
    }

}
