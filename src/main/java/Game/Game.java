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
        this.level1 = new Level(new int[]{BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,},map.getSpwanCoords());
        this.aStar = new AStar(map.getMapMatrice());






    }

    public void playerUpdate(){
        player.updateAnimationIndex();
        player.updatePos();
        wallCollision(player);
        player.updateStatus();
        player.updateShootingDirection();
    }

    public boolean wallCollision(Entity entity){

        boolean collide = true;
        while(collide){
            collide = false;
            entity.getHitbox().updateHitbox();
            entity.resetWallCollision();
            entity.setWallCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
            entity.setWallCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
            entity.setWallCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
            entity.setWallCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
            if(entity.getWallCollision()[0] && entity.getWallCollision()[1] && entity.getWallCollision()[2] && entity.getWallCollision()[3]){
                return true;
            }
            if(entity.getWallCollision()[0] || entity.getWallCollision()[1] || entity.getWallCollision()[2] || entity.getWallCollision()[3]){
                collide = true;
                entity.cancelCollision();
            }
        }

        return collide;
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
            for(int j =0;j<level1.getIngameEnnemyList().size();j++){
                if(i!=j){
                    level1.getIngameEnnemyList().get(i).cancelEntityCollision(level1.getIngameEnnemyList().get(j));
                }
            }

            level1.getIngameEnnemyList().get(i).cancelEntityCollision(player);
            wallCollision(level1.getIngameEnnemyList().get(i));
            level1.getIngameEnnemyList().get(i).updatePos(aStar);





        }
    }





    void updateAll(){

        playerUpdate();
        level1.update();
        updateEnemies();


        updateBullets(player);
    }




}

