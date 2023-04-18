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
    public Coord baseCoord;
    public Image sprite = new Image(Objects.requireNonNull(getClass().getResource("/Objects/BulletSprite.png")).toExternalForm());

    public Bullet(Weapon weapon, Coord coord, int Xdirection, int Ydirection, int damage, int range, int speed,int bulletSize) {
        this.Xdirection = Xdirection;
        this.Ydirection = Ydirection;
        this.coord = new Coord(coord.getX()+TILE_SIZE/2-bulletSize, coord.getY()+TILE_SIZE/2-bulletSize/2);
        this.damage = damage;
        this.range = range;
        this.speed = (int) (speed*SCALE);
        this.status = WALKING;
        this.shotBy = weapon;
        this.size= (int) (15*SCALE);
        this.hitbox = new Hitbox(coord, size,0, 0);
    }





    @Override
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

    public Hitbox getHitbox(){
        return hitbox;
    }
    public int getSize(){
        return size;
    }

    @Override
    public void updateStatus() {
        if(range<=0){
            status=STATIC;
        }
    }

    public void render(GraphicsContext g,int decalageX,int decalageY){
        g.drawImage(sprite, coord.getX()+decalageX, coord.getY()+decalageY,size,size);

    }



}
