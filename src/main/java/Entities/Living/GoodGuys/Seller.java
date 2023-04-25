package Entities.Living.GoodGuys;

import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.STATIC;
import static utils.Constants.WindowConstants.SCALE;

public class Seller extends PNJ{

    public Seller(){
        super();
        this.entityName = "PNJ";
        this.spriteSize=64;
        this.size = (int) (spriteSize*SCALE);
        this.speed= (int) (3*SCALE);
        this.spriteSheet = new Image(Objects.requireNonNull(getClass().getResource("/Sprites/PnjSPRITESHEET.png")).toExternalForm());
        this.hitbox.setHitboxSize(size/3, size-size/3);
        this.hitbox.setHitboxOffset(size/3,size/3);

        this.status=STATIC;
        this.coord.setXY(18*TILE_SIZE, 11*TILE_SIZE);
        this.hitbox.updateHitbox();
    }

    @Override
    public void cancelCollision() {

    }
}
