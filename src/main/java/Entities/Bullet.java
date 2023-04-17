package Entities;

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
import static utils.Constants.WindowConstants.SPRITE_COORD;

public class Bullet extends Entity{
    private int damage;
    private int range;
    private int speed;
    private Weapon shotBy;
    private Coord baseCoord;
    Image sprite = new Image(Objects.requireNonNull(getClass().getResource("/Objects/BulletSprite.png")).toExternalForm());

    public Bullet(Weapon weapon, Coord coord, int Xdirection, int Ydirection, int damage, int range, int speed) {
        this.Xdirection = Xdirection;
        this.Ydirection = Ydirection;
        this.baseCoord = new Coord(coord.getX(), coord.getY());
        this.coord=coord;
        this.damage = damage;
        this.range = range;
        this.speed =speed;
        this.status = WALKING;
        this.hitbox = new Hitbox(coord, 10,0, 0);
        this.shotBy = weapon;
        this.movement= new Coord(0,0);
    }




    @Override
    public void updatePos() {
        if(status == WALKING){
            if(Xdirection==RIGHT){
                baseCoord.addXY(speed, 0);
            }
            if(Xdirection==LEFT){
                baseCoord.addXY(-speed, 0);
            }
            if(Ydirection==UP){
                baseCoord.addXY(0, -speed);
            }
            if(Ydirection==DOWN){
                baseCoord.addXY(0, speed);
            }
            range-=1;
        }

    }

    public Hitbox getHitbox(){
        return hitbox;
    }

    @Override
    public void updateStatus() {
        if(range<=0){
            status=STATIC;
        }
    }

    public void render(GraphicsContext g){
        g.drawImage(sprite, baseCoord.getX()+TILE_SIZE/2, baseCoord.getY()+TILE_SIZE/2, 10, 10);
    }

}
