package Game;

import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;
import Map.Map;
import Entities.Entity;
import Entities.Living.Player;
import Inputs.KeyboardInput;
import utils.AStar;

import java.io.IOException;
import java.net.URISyntaxException;

import static utils.Constants.Directions.*;
import static utils.Constants.EntityConstants.BASE_MONKE;
import static utils.Constants.PlayerConstants.STATIC;


public class Game {



    public KeyboardInput keyboardInput;
    public Double framerate;

    public Player player;
    public Map map;
    public Camera camera;
    public Level level1;
    public AStar aStar;




    public Game() throws IOException, URISyntaxException {



        //set a canva for the player


        this.player=new Player();


        this.map = new Map();

        this.camera= new Camera(this);
        this.keyboardInput = new KeyboardInput(this);
        this.level1 = new Level(new int[]{BASE_MONKE},map.getSpwanCoords());
        this.aStar = new AStar(map.getMapMatrice());






    }

    public void playerUpdate(){
        player.updateAnimationIndex();
        player.updatePos();
        player.updateDirection();
        cancelWallCollision(player);
        player.updateStatus();
        player.updateShootingDirection();
    }

    public boolean wallCollision(Entity entity){
        boolean collide = false;
        entity.getHitbox().updateHitbox();
        entity.resetWallCollision();
        entity.setWallCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
        entity.setWallCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
        if(entity.getWallCollision()[0] || entity.getWallCollision()[1] || entity.getWallCollision()[2] || entity.getWallCollision()[3]){
            collide = true;
        }
        return collide;
    }
    public void cancelWallCollision(Entity entity){
        if(!wallCollision(entity)){
            return;
        };
        //int[0] = up and down, int[1] = left and right
        int[] direction = new int[]{0,0};
        if(entity.getWallCollision()[0]) {
            if (entity.getYDirection() == UP){
                direction[0] += 1;
            }
            if (entity.getXDirection() == LEFT){
                direction[1] += 1;
            }
        }
        if(entity.getWallCollision()[1]){
            if (entity.getYDirection() == UP){
                direction[0] += 1;
            }
            if (entity.getXDirection() == RIGHT){
                direction[1] -= 1;
            }
        }
        if(entity.getWallCollision()[2]){
            if (entity.getYDirection() == DOWN){
                direction[0] -= 1;
            }
            if (entity.getXDirection() == LEFT){
                direction[1] += 1;
            }
        }
        if(entity.getWallCollision()[3]) {
            if (entity.getYDirection() == DOWN){
                direction[0] -= 1;
            }
            if (entity.getXDirection() == RIGHT){
                direction[1] -= 1;
            }
        }
        while(entity.getWallCollision()[0] || entity.getWallCollision()[1] || entity.getWallCollision()[2] || entity.getWallCollision()[3]) {

            entity.getCoord().addXY(direction[1], direction[0]);
            entity.getHitbox().updateHitbox();
            wallCollision(entity);
        }
    }



    public void updateBullets(LivingEntity entity){
        for(int i=0; i<entity.getWeapon().getBullets().size();i++){
            Bullet bullet = entity.getWeapon().getBullets().get(i);
            if(bullet.status==STATIC){

                entity.getWeapon().getBullets().remove(bullet);
            }
            else {

                bullet.updateStatus();
                bullet.updatePos();
                if(wallCollision(bullet)){
                    bullet.status=STATIC;
                }
                bullet.getHitbox().updateHitbox();
            }
        }
    }

    public void updateEnemies(){
        for(int i =0;i<level1.getIngameEnnemyList().size();i++){
            level1.getIngameEnnemyList().get(i).updatePos(aStar);
            level1.getIngameEnnemyList().get(i).cancelEntityCollision(player);
            cancelWallCollision(level1.getIngameEnnemyList().get(i));

        }
    }





    void updateAll(){

        playerUpdate();
        level1.update();
        updateEnemies();


        updateBullets(player);
    }




}

