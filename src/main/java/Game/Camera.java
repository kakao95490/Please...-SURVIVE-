package Game;

import Entities.Living.Ennemies;
import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Camera {
    Game game;
    public int decalageCameraX;
    public int decalageCameraY;
    private final Canvas canvas;
    private final Canvas bgCanvas;
    private static Scene scene;
    private final Stage stage;
    private final Group root;
    public GraphicsContext gc;
    public GraphicsContext bgc;


    public Camera(Game game){
        this.game = game;


        this.stage = new Stage();
        this.root = new Group();
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
        this.stage.show();

    }



    //draw the map with the movement of the player (decalageCamera)
    public void drawMapMatrice(){
        updateCameraOffset();
        for (int i = 0; i < game.map.getMapMatrice().length; i++) {
            for (int j = 0; j < game.map.getMapMatrice()[i].length; j++) {
                if(game.map.getMapMatrice()[i][j] != -1) {
                    bgc.drawImage(game.map.textureLib[game.map.getMapMatrice()[i][j]], (j * TILE_SIZE) + decalageCameraX , (i * TILE_SIZE)+ decalageCameraY,TILE_SIZE,TILE_SIZE);
                }
            }
        }
    }


    //render the player
    public void playerRender(){
        gc.drawImage(game.player.animationLib[game.player.status][game.player.animationIndex], SPRITE_COORD.getX(), SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);
    }

    //render bullets shot by an entity
    public void renderBullets(LivingEntity entity, GraphicsContext g){
        for(Bullet bullet : entity.getWeapon().getBullets()){
            bgc.drawImage(bullet.sprite, bullet.getCoord().getX()+decalageCameraX, bullet.getCoord().getY()+decalageCameraY,bullet.size,bullet.size);
        }
    }

    void renderEnnemies(){
        for(Ennemies entity : game.level1.getIngameEnnemyList()){
            entity.updateAnimationIndex();
            renderEntity(entity, bgc);
        }
    }

    //render an entity
    void renderEntity(LivingEntity entity, GraphicsContext g){
        g.drawImage(entity.animationLib[entity.status][entity.animationIndex], entity.getCoord().getX()+(decalageCameraX), entity.getCoord().getY()+(decalageCameraY), TILE_SIZE, TILE_SIZE);
    }

    void renderAll(){
        //clear all
        bgc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.clearRect(0,0,WIDTH,HEIGHT);

        //redraw the map with the movement of the player
        drawMapMatrice();

        //render entities
        playerRender();
        renderEnnemies();
        renderBullets(game.player, gc);

    }
    public void updateCameraOffset(){
        decalageCameraX=SPRITE_COORD.getX() - game.player.getCoord().getX();
        decalageCameraY=SPRITE_COORD.getY() - game.player.getCoord().getY();
    }


    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }
}
