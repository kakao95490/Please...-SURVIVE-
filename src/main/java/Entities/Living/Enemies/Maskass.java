package Entities.Living.Enemies;

import Items.Weapons.Fist;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.WALKING;
import static utils.Constants.WindowConstants.SCALE;

public class Maskass extends Enemies {
    public Maskass(int x, int y) {
        this.coord.setXY(x*TILE_SIZE, y*TILE_SIZE);
        this.prevCoord.setXY(coord.getX(),coord.getY());
        this.entityName = "Masskass";

        this.spriteSize = 100;
        this.size = (int) (this.spriteSize*SCALE);
        this.hitbox.setHitboxSize((int) (size/1.8), (int) (size/1.8));
        this.hitbox.setHitboxOffset((int) (size/3.8),(int) (size/3.8));
        this.hitbox.updateHitbox();
        this.status = WALKING;
        this.spriteSheet =new Image(Objects.requireNonNull(getClass().getResource("/Sprites/MaskassSPRITESHEET.png")).toExternalForm());


        this.speed = (int) (2*SCALE);
        this.maxHP = 200;
        this.HP = maxHP;
        this.dmgMultiplier = 4;
        this.weapon = new Fist(this);
        this.money = 10;
    }

}
