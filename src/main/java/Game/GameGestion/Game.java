package Game.GameGestion;

import Map.Map;
import Inputs.KeyboardInput;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import utils.AStar;
import utils.Timer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static utils.Constants.EntityConstants.*;
import static utils.Constants.GameConstants.*;
import static utils.Constants.WindowConstants.FPS_TARGET;


public class Game {

    public ArrayList<Timer> timerList = new ArrayList<>();

    public KeyboardInput keyboardInput;
    public Double framerate;
    public EntityGestion EntityGestion = new EntityGestion();
    int frame = 0;
    long lastCheck=System.currentTimeMillis();
    double timePerFrame = 1000.0 / FPS_TARGET;

    private Timer timerBeetweenRound = null;



    public Map map;
    public Camera camera;
    public AStar aStar;
    public Round currentRound;
    public int roundCounter;
    public ArrayList<Round> roundList;

    public boolean hasTakenBonus;

    public Timeline timeline = null;

    public int status = PAUSED;

    public Game() throws URISyntaxException, IOException {
        camera = new Camera(this);
    }


    public void initGame() throws URISyntaxException, IOException {
        EntityGestion.initEntities();
        camera.initBonusMenu();
        this.map = new Map();
        this.keyboardInput = new KeyboardInput(this);
        this.roundCounter=0;
        this.aStar = new AStar(map.getMapMatrice());
        this.roundList = new ArrayList<>();
        this.hasTakenBonus=false;
        this.roundList.add(new Round(new int[]{
                BASE_MONKE,
        },
                map.getSpwanCoords(),80));

        this.roundList.add(new Round(new int[]{
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
        },
                map.getSpwanCoords(),30));

        this.roundList.add(new Round(new int[]{
                AXIE,
                AXIE,
                AXIE,

        }, map.getSpwanCoords(),60));

        this.roundList.add(new Round(new int[]{
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                BASE_MONKE,
                AXIE,
                BASE_MONKE,
                AXIE,
                BASE_MONKE,

        }, map.getSpwanCoords(),60));
        this.roundList.add(new Round(new int[]{
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                AXIE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,


        }, map.getSpwanCoords(),60));
        this.roundList.add(new Round(new int[]{
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                AXIE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
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
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,
                AXIE,
                AXIE,
                AXIE,
                BASE_MONKE,
                BASE_MONKE,
                BASE_MONKE,



        }, map.getSpwanCoords(),50));
        this.roundList.add(new Round(new int[]{
                MASKASS,
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,
        }, map.getSpwanCoords(),200));

        this.currentRound= roundList.get(0);
    }


    public void cleanGame() {
        EntityGestion.cleanEntities();
        this.map = null;
        this.keyboardInput = null;
        this.aStar = null;
        this.roundList = null;
        this.currentRound= null;

    }







    public void updateRounds(){
        if(!currentRound.update()){
            if(roundCounter >= roundList.size()){
                status=WIN;
                return;
            }
            else {
                if(!hasTakenBonus && !EntityGestion.getPlayer().isOnMenu){
                    camera.bonusMenu();
                }


                if(timerBeetweenRound == null){
                    timerBeetweenRound = new Timer(15);
                    timerList.add(timerBeetweenRound);
                }
                if(timerBeetweenRound.isFinished()){
                    roundCounter++;
                    currentRound = roundList.get(roundCounter);

                    hasTakenBonus =false;
                    HUD.timerValue.setText("");
                    timerBeetweenRound=null;
                }
                else{
                    HUD.timerValue.setText(String.valueOf(timerBeetweenRound.getSecondLeft()));
                }


            }
        }
    }




    public void updateAll(){
        updateRounds();
        EntityGestion.updateBullets(map);
        EntityGestion.updatePnj();
        EntityGestion.playerUpdate(camera,map);
        EntityGestion.updateEnemies(currentRound,aStar,map);
        EntityGestion.updateDisplayedEntitiesList(currentRound.getIngameEnnemyList());
        if(EntityGestion.player.getHP()<=0){
            status=GAME_OVER;
        }

    }




    public void gameUpdateAndRender(){
        for(Timer timer : timerList){
            timer.updateTimer();
        }
        updateAll();
        camera.renderAll();
    }


    public void gameLoop(){
        status = RUNNING;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(timePerFrame), event -> {



            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                framerate= (double) (frame);
                System.out.println("fps " + framerate);
                frame=0;
            }
            frame++;

            if(status==RUNNING){
                gameUpdateAndRender();
            }
            else if(status==GAME_OVER){
                timeline.stop();
                cleanGame();
                System.out.println("game over");
                camera.displayEndScreen(GAME_OVER);

            }
            else if(status==WIN){
                timeline.stop();
                cleanGame();
                camera.displayEndScreen(WIN);

            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }




}

