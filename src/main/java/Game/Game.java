package Game;

import Entities.Living.Enemies;
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
        this.level1 = new Level(new int[]{BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,BASE_MONKE,},map.getSpwanCoords());
        this.aStar = new AStar(map.getMapMatrice());






    }


    public boolean detectWallCollision(Entity entity){

            entity.getHitbox().updateHitbox();
            entity.resetCollisions();
            entity.setCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
            entity.setCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
            entity.setCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
            entity.setCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
            return entity.getCollisions()[0] || entity.getCollisions()[1] || entity.getCollisions()[2] || entity.getCollisions()[3];
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
                if(detectWallCollision(bullet)){
                    bullet.status=STATIC;
                }
                bullet.getHitbox().updateHitbox();
            }
        }
    }

    public void playerUpdate(){
        player.updateAnimationIndex();
        player.updatePos();
        while(detectWallCollision(player)){
            player.cancelCollision();
        }
        player.updateStatus();
        player.updateShootingDirection();
    }


    public void updateEnemies(){
        Enemies currentEnemy;
        for(int i =0;i<level1.getIngameEnnemyList().size();i++){
            currentEnemy = level1.getIngameEnnemyList().get(i);

            currentEnemy.updatePos(aStar);

            detectWallCollision(currentEnemy);

            int compteur=0;
            while((currentEnemy.getCollisions()[0] || currentEnemy.getCollisions()[1] || currentEnemy.getCollisions()[2] || currentEnemy.getCollisions()[3])){
                if((currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2]&&currentEnemy.getCollisions()[3])||(currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[3]) || (currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2])){
                    break;
                }
                detectWallCollision(currentEnemy);
                currentEnemy.cancelCollision();
            }
            currentEnemy.resetCollisions();
            currentEnemy.detectEntityCollision(player);
            while((currentEnemy.getCollisions()[0] || currentEnemy.getCollisions()[1] || currentEnemy.getCollisions()[2] || currentEnemy.getCollisions()[3])){
                if((currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2]&&currentEnemy.getCollisions()[3])||(currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[3]) || (currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2])){
                    break;
                }
                currentEnemy.resetCollisions();
                currentEnemy.detectEntityCollision(player);
                currentEnemy.cancelCollision();
            }

            for(int j=0;j<level1.getIngameEnnemyList().size();j++){
                if(i==j){
                    continue;
                }
                Entity tempEnemy = level1.getIngameEnnemyList().get(j);

                currentEnemy.resetCollisions();
                currentEnemy.detectEntityCollision(tempEnemy);
                while((currentEnemy.getCollisions()[0] || currentEnemy.getCollisions()[1] || currentEnemy.getCollisions()[2] || currentEnemy.getCollisions()[3])){
                    if((currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2]&&currentEnemy.getCollisions()[3])||(currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[3]) || (currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2])){
                        break;
                    }
                    currentEnemy.resetCollisions();
                    currentEnemy.detectEntityCollision(tempEnemy);
                    currentEnemy.cancelCollision();
                }

            }





        }
    }





    void updateAll(){

        playerUpdate();
        level1.update();
        updateEnemies();


        updateBullets(player);
    }




}

