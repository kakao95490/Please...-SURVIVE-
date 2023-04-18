package Entities.Inert;

import Entities.Entity;
import Weapons.Weapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Coord;
import utils.Hitbox;

import java.util.Objects;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.SCALE;

public class Bullet extends InertEntity {
    private int range;
    private int speed;

    public Bullet(Coord coord, int Xdirection, int Ydirection, int range, int speed,int bulletSize) {
        this.Xdirection = Xdirection;
        this.Ydirection = Ydirection;
        this.coord.setXY(coord.getX()+TILE_SIZE/2-bulletSize, coord.getY()+TILE_SIZE/2-bulletSize/2);
        this.range = range;
        this.speed = (int) (speed*SCALE);
        this.status = WALKING;
        this.size= (int) (15*SCALE);
        this.hitbox.updateHitbox();
        this.sprite = new Image(Objects.requireNonNull(getClass().getResource("/Objects/BulletSprite.png")).toExternalForm());
    }



    public void updatePos() {
        int spd = speed;
        if(Xdirection!=-1 && Ydirection!=-1){
            spd = (int) (speed/Math.sqrt(2));
        }
        if(status == WALKING){
            if(Xdirection==RIGHT){
                coord.addXY(spd, 0);
            }
            if(Xdirection==LEFT){
                coord.addXY(-spd, 0);
            }
            if(Ydirection==UP){
                coord.addXY(0, -spd);
            }
            if(Ydirection==DOWN){
                coord.addXY(0, spd);
            }
            range-=1;
        }

    }


    public void updateStatus() {
        if(range<=0){
            status=STATIC;
        }
    }



}