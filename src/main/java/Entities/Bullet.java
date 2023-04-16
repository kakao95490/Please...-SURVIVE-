package Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import utils.Coord;
import utils.Hitbox;

import java.util.Objects;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.SCALE;
import static utils.Constants.WindowConstants.SPRITE_COORD;

public class Bullet extends Entity{
    private int damage;
    private int range;
    private int speed;
    Image sprite = new Image(Objects.requireNonNull(getClass().getResource("/Objects/BulletSprite.png")).toExternalForm());

    public Bullet(Coord coord, int Xdirection, int Ydirection, int damage, int range, int speed) {
        this.Xdirection = Xdirection;
        this.Ydirection = Ydirection;
        this.coord = new Coord(coord.getX(), coord.getY());
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.status = WALKING;
        this.hitbox = new Hitbox(coord, (int) (10*SCALE),0, 0);

    }




    @Override
    public void updatePos() {
        if(range>0){
            if(Xdirection==RIGHT){
                coord.addXY(speed, 0);
            }
            if(Xdirection==LEFT){
                coord.addXY(-speed, 0);
            }
            if(Ydirection==UP){
                coord.addXY(0, -speed);
            }
            if(Ydirection==DOWN){
                coord.addXY(0, speed);
            }
            range--;
        }
        else{
            status=STATIC;
        }
    }

    public Hitbox getHitbox(){
        return hitbox;
    }

    @Override
    public void updateStatus() {
    }

    public void render(GraphicsContext g){
        g.drawImage(sprite, SPRITE_COORD.getX(), SPRITE_COORD.getY(), 10*SCALE, 10*SCALE);
    }

}
