package Game;

import Entities.Living.BaseMonke;
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
import java.util.ArrayList;
import java.util.Comparator;

import static utils.Constants.EntityConstants.AXIE;
import static utils.Constants.EntityConstants.BASE_MONKE;
import static utils.Constants.PlayerConstants.*;


public class Game {



    public KeyboardInput keyboardInput;
    public Double framerate;

    public Player player;
    public Map map;
    public Camera camera;
    public AStar aStar;
    public Level currentLevel;
    public ArrayList<Entity> displayedEntities = new ArrayList<>();


    public Game() throws IOException, URISyntaxException {



        //set a canva for the player


        this.player=new Player();


        this.map = new Map();

        this.camera= new Camera(this);
        this.keyboardInput = new KeyboardInput(this);
        this.aStar = new AStar(map.getMapMatrice());

        this.currentLevel = new Level(new int[]{
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,


                AXIE,
                AXIE,
                AXIE,
                AXIE,
        },
                map.getSpwanCoords());






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

        player.updatePos();

        while(detectWallCollision(player)){
            player.cancelCollision();
        }

        player.updateShootingDirection();
        player.updateStatus();
        player.updateAnimationIndex();
    }





    public void updateEnemies(){
        Enemies currentEnemy;

        for(int i =0;i<currentLevel.getIngameEnnemyList().size();i++){
            currentEnemy = currentLevel.getIngameEnnemyList().get(i);


            for(int j=0;j<player.getWeapon().getBullets().size();j++){
                currentEnemy.gotHitByBullet(player.getWeapon().getBullets().get(j));
            }


            if(currentEnemy.status==DEAD){
                currentEnemy.updateStatus();
                currentEnemy.updateAnimationIndex();
                if(!currentEnemy.isAlive){
                    currentLevel.getIngameEnnemyList().remove(currentEnemy);
                }
                continue;
            }

            //if the enemy is dead, launch the death animation
            if(currentEnemy.checkIfDied()){
                continue;
            }


            //if the enemy is hit, it will not move, be invicible for a while and his hitbox will be smaller to let the other enemies pass
            if(currentEnemy.status==HIT){
                currentEnemy.updateStatus();
                currentEnemy.updateAnimationIndex();
                continue;
            }

            currentEnemy.updatePos(aStar);

            //check collision with other enemies
            for(int j=0;j<currentLevel.getIngameEnnemyList().size();j++){
                if(i==j){
                    continue;
                }
                currentEnemy.updateEntityCollisions(currentLevel.getIngameEnnemyList().get(j));
            }

            //cancel wall collision
            while(detectWallCollision(currentEnemy)){
                if((currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2]&&currentEnemy.getCollisions()[3]) ){
                    break;
                }
                currentEnemy.cancelCollision();
            }

            currentEnemy.resetCollisions();
            checkCollideWithPlayer(currentEnemy);
            currentEnemy.updateStatus();
            currentEnemy.updateAnimationIndex();
        }

    }


    public void updateDisplayedEntitiesList(){
        displayedEntities.clear();
        displayedEntities.add(player);
        displayedEntities.addAll(currentLevel.getIngameEnnemyList());
        displayedEntities.addAll(player.getWeapon().getBullets());
        sortDisplayedEntities();
    }

    public void sortDisplayedEntities(){
        displayedEntities.sort(Comparator.comparingInt(o -> o.getHitbox().getCornerDownLeft().getY()));
    }

    public void checkCollideWithPlayer(Enemies entity){
        entity.detectEntityCollision(player);
        if(entity.getCollisions()[0] || entity.getCollisions()[1] || entity.getCollisions()[2] || entity.getCollisions()[3]){
            entity.isInCollisionWithPlayer=true;
            player.gotHit(entity);
        }
        else {
            entity.isInCollisionWithPlayer=false;
        }
        entity.resetCollisions();
    }





    void updateAll(){
        updateBullets(player);
        currentLevel.update();
        playerUpdate();
        updateEnemies();

        updateDisplayedEntitiesList();

    }




}

