package Entities.Living;

import Weapons.Fist;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class Axie extends Enemies {
    public Axie(int x, int y) {
        this.coord.setXY(x*TILE_SIZE, y*TILE_SIZE);
        this.prevCoord.setXY(coord.getX(),coord.getY());
        this.entityName = "Axie";

        this.spriteSize = 32;
        this.size = (int) (spriteSize*SCALE);
        this.hitbox.setHitboxSize(size, size);
        this.hitbox.setHitboxOffset(0,0);
        this.hitbox.updateHitbox();
        this.status = WALKING;
        this.spriteSheet =new Image(Objects.requireNonNull(getClass().getResource("/Sprites/AxieSPRITESHEET.png")).toExternalForm());

        this.speed = (int) (3*SCALE);
        this.HP = 30;
        this.maxHP = 30;
        this.dmgMultiplier = 0.5;
        this.weapon = new Fist(this);

    }

}
