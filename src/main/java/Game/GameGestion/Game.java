package Game.GameGestion;

import Entities.Living.*;
import Entities.Inert.Bullet;
import Entities.Living.Enemies.Enemies;
import Entities.Living.GoodGuys.Player;
import Entities.Living.GoodGuys.Seller;
import Map.Map;
import Entities.Entity;
import Inputs.KeyboardInput;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import utils.AStar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;

import static utils.Constants.EntityConstants.AXIE;
import static utils.Constants.EntityConstants.BASE_MONKE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.FPS_TARGET;


public class Game {



    public KeyboardInput keyboardInput;
    public Double framerate;
    int frame = 0;
    long lastCheck=System.currentTimeMillis();
    long timerCheck=System.currentTimeMillis();
    double timePerFrame = 1000.0 / FPS_TARGET;
    int timeBetweenRound = 30;
    int secondLeft=30;


    public Player player;
    public Seller pnj;
    public Map map;
    public Camera camera;
    public AStar aStar;
    public Round currentRound;
    public int roundCounter = 1;
    public ArrayList<Entity> displayedEntities = new ArrayList<>();
    public ArrayList<Round> roundList = new ArrayList<>();


    public Game() throws IOException, URISyntaxException {



        //set a canva for the player


        this.player=new Player();
        this.pnj = new Seller();


        this.map = new Map();

        this.camera= new Camera(this);
        this.keyboardInput = new KeyboardInput(this);
        this.aStar = new AStar(map.getMapMatrice());
        this.roundList = new ArrayList<>();
        this.roundList.add(new Round(new int[]{
                        BASE_MONKE,
                        BASE_MONKE,
                        BASE_MONKE,
                        BASE_MONKE,
                        BASE_MONKE,
                        BASE_MONKE,
                },
                map.getSpwanCoords(),80));
        this.roundList.add(new Round(new int[]{
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,

        },
                map.getSpwanCoords(),80));
        this.currentRound= roundList.get(0);

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
                if(EntityGestion.detectWallCollision(bullet,map)){
                    bullet.status=STATIC;
                }
                bullet.getHitbox().updateHitbox();
            }
        }
    }



    public void playerUpdate(){

        player.updatePos();

        while(EntityGestion.detectWallCollision(player,map)){
            player.cancelCollision();
        }



        player.updateShootingDirection();
        player.updateStatus();
        player.updateAnimationIndex();

        EntityGestion.interactWithPnj(camera,player,pnj);
    }





    public void updateEnemies(){
        Enemies currentEnemy;

        for(int i = 0; i< currentRound.getIngameEnnemyList().size(); i++){
            currentEnemy = currentRound.getIngameEnnemyList().get(i);


            for(int j=0;j<player.getWeapon().getBullets().size();j++){
                currentEnemy.gotHitByBullet(player.getWeapon().getBullets().get(j));
            }


            if(currentEnemy.status==DEAD){
                currentEnemy.updateStatus();
                currentEnemy.updateAnimationIndex();
                if(!currentEnemy.isAlive){
                    currentRound.getIngameEnnemyList().remove(currentEnemy);
                }
                continue;
            }

            //if the enemy is dead, launch the death animation
            if(currentEnemy.checkIfDied()){
                continue;
            }


            currentEnemy.updatePos(aStar);

            //check collision with other enemies
            for(int j = 0; j< currentRound.getIngameEnnemyList().size(); j++){
                if(i==j){
                    continue;
                }
                currentEnemy.updateEntityCollisions(currentRound.getIngameEnnemyList().get(j));
            }

            //cancel wall collision
            while(EntityGestion.detectWallCollision(currentEnemy,map)){
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
        displayedEntities.add(pnj);
        displayedEntities.addAll(currentRound.getIngameEnnemyList());
        displayedEntities.addAll(player.getWeapon().getBullets());
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

    public void updateRounds(){
        if(!currentRound.update()){
            if(roundList.size()==0 || roundCounter>=roundList.size()){
                return;
            }
            else {
                if(secondLeft == 0){
                    currentRound = roundList.get(roundCounter);
                    roundCounter++;
                    secondLeft=timeBetweenRound;
                    HUD.timerValue.setText("");
                }
                else if(System.currentTimeMillis()-timerCheck>=1000){
                    timerCheck=System.currentTimeMillis();
                    secondLeft-=1;
                    HUD.timerValue.setText(String.valueOf(secondLeft));
                }
            }
        }
    }

    private void updatePnj() {
        pnj.updateAnimationIndex();
    }




    public void updateAll(){
        updateRounds();
        updateBullets(player);
        updatePnj();
        playerUpdate();
        updateEnemies();
        updateDisplayedEntitiesList();
    }




    public void gameUpdateAndRender(){
        updateAll();
        camera.renderAll();
    }



    public void startGame(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timePerFrame), event -> {


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                framerate= (double) (frame);
                System.out.println("fps " + framerate);
                frame=0;
            }

            gameUpdateAndRender();
            frame++;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}

