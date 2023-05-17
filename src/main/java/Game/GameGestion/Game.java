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

    /**
     * Constructeur du jeu, initialise la caméra (créer une fenetre de jeu)
     * @throws URISyntaxException
     * @throws IOException
     */
    public Game() throws URISyntaxException, IOException {
        camera = new Camera(this);
    }


    /**
     * Initialise le jeu et ses composants (map, entités, etc)
     * @throws URISyntaxException
     * @throws IOException
     */
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
                BASE_MONKE,
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
                AXIE,
                AXIE,
                AXIE,
                AXIE,
                AXIE,

        }, map.getSpwanCoords(),40));

        this.currentRound= roundList.get(0);
    }


    /**
     * Clean le jeu et ses composants
     */
    public void cleanGame() {
        EntityGestion.cleanEntities();
        this.map = null;
        this.keyboardInput = null;
        this.aStar = null;
        this.roundList = null;
        this.currentRound= null;

    }






    /**
     * Update l'apparition des vagues d'ennemis et les fait spawn
     *
     */
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


    /**
     * Update toutes les entités du jeu (joueur, ennemis, pnj, balles) et les fait interagir entre elles et avec la map
     * @see EntityGestion#updateBullets(Map)
     * @see EntityGestion#updatePnj()
     * @see EntityGestion#playerUpdate(Camera, Map)
     * @see EntityGestion#updateEnemies(Round, AStar, Map)
     * @see EntityGestion#updateDisplayedEntitiesList(ArrayList)
     * @see #updateRounds()
     */
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



    /**
     * Update et render le jeu
     * @see #updateAll()
     * @see Camera#renderAll()
     */
    public void gameUpdateAndRender(){
        for(Timer timer : timerList){
            timer.updateTimer();
        }
        updateAll();
        camera.renderAll();
    }

    /**
     * Lance la boucle de jeu
     * @see #gameUpdateAndRender()
     */
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

            //si le jeu tourne toujours, on update et on render
            if(status==RUNNING){
                gameUpdateAndRender();
            }
            //si le jeu est en GAME_OVER ou WIN, on stop le jeu et on affiche l'écran de fin
            else if(status==GAME_OVER){
                timeline.stop();
                cleanGame();
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

