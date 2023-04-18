package Game;

import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;
import Map.Map;
import Entities.Entity;
import Entities.Living.Player;
import Inputs.KeyboardInput;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.STATIC;
import static utils.Constants.WindowConstants.*;


public class Game {

    public int decalageCameraX;
    public int decalageCameraY;
    private final Canvas canvas;
    private final Canvas bgCanvas;
    private static Scene scene;
    private final Stage stage;
    private final Group root;
    public GraphicsContext gc;
    public GraphicsContext bgc;

    public KeyboardInput keyboardInput;
    public Double framerate;

    public List<Entity> entities;
    public Player player;
    public Map map;
    public Camera camera;




    public Game() throws IOException, URISyntaxException {
        this.camera= new Camera(this);
        this.stage = new Stage();
        this.root = new Group();

        //set a canva for the player
        this.canvas = new Canvas(WIDTH,HEIGHT);
        this.bgCanvas = new Canvas(WIDTH,HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.bgc = bgCanvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        bgc.setImageSmoothing(false);




        scene = new Scene(root,WIDTH, HEIGHT);
        Color BACKGROUND_COLOR = Color.BLACK;
        scene.setFill(BACKGROUND_COLOR);
        this.stage.setScene(scene);

        this.root.getChildren().add(bgCanvas);
        this.root.getChildren().add(canvas);
        this.stage.setTitle("Please....SURVIVE!");

        this.stage.setResizable(true);
        this.stage.setFullScreen(true);
        this.keyboardInput = new KeyboardInput();


        this.player=new Player(this);
        this.map = new Map();
        entities = new ArrayList<>();
        entities.add(player);



        this.decalageCameraX=SPRITE_COORD.getX() - player.getCoord().getX();
        this.decalageCameraY=SPRITE_COORD.getY() - player.getCoord().getY();


    }

    public void playerUpdate(){
        player.updateAnimationIndex(player.animationLib[player.status]);
        player.updatePos();
        player.updateDirection();
        cancelWallCollision(player);
        player.updateStatus();
        player.updateShootingDirection();
    }

    public void collideDirection(Entity entity){
        entity.getHitbox().updateHitbox();
        entity.resetWallCollision();
        entity.setWallCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
        entity.setWallCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
    }
    public void cancelWallCollision(Entity entity){
        collideDirection(entity);
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
            collideDirection(entity);
        }
    }



    public void updateBullets(LivingEntity entity, GraphicsContext g){
        for(int i=0; i<entity.getWeapon().getBullets().size();i++){
            Bullet bullet = entity.getWeapon().getBullets().get(i);
            if(bullet.status==STATIC){
                entity.getWeapon().getBullets().remove(bullet);
            }
            else {
                bullet.updateStatus();
                bullet.updatePos();
                bullet.getHitbox().updateHitbox();
            }
        }
    }

    public void updateCameraOffset(){
        decalageCameraX=SPRITE_COORD.getX() - player.getCoord().getX();
        decalageCameraY=SPRITE_COORD.getY() - player.getCoord().getY();
    }


    void updateAll(){
        playerUpdate();
        updateCameraOffset();
        updateBullets(player,gc);
    }






    public Canvas getCanvas() {
        return canvas;
    }

    public static Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Group getRoot() {
        return root;
    }


}

