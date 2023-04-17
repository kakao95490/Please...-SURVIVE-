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
        this.speed = (int) (speed*SCALE);
        this.status = WALKING;
        this.hitbox = new Hitbox(coord, (int) (10*SCALE),0, 0);
        this.shotBy = weapon;
        this.movement= new Coord(0,0);
    }




    @Override
    public void updatePos() {
        int spd = speed;
        if(Xdirection!=-1 && Ydirection!=-1){
            spd = (int) (speed/Math.sqrt(2));
        }
        if(status == WALKING){
            if(Xdirection==RIGHT){
                baseCoord.addXY(spd, 0);
            }
            if(Xdirection==LEFT){
                baseCoord.addXY(-spd, 0);
            }
            if(Ydirection==UP){
                baseCoord.addXY(0, -spd);
            }
            if(Ydirection==DOWN){
                baseCoord.addXY(0, spd);
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
        g.drawImage(sprite, baseCoord.getX()+movement.getX()-coord.getX()+ SPRITE_COORD.getX()+TILE_SIZE/2, baseCoord.getY()+movement.getY()-coord.getY()+ SPRITE_COORD.getY()+TILE_SIZE/2, 15*SCALE, 15*SCALE);
    }

}
