package Game.GameGestion;

import Entities.Entity;
import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;

import static utils.Constants.Directions.RIGHT;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Coord;

public class Camera {
    Game game;
    public int decalageCameraX;
    public int decalageCameraY;
    public HUD HUD = new HUD();
    private final Canvas canvas;
    private final Canvas bgCanvas;
    private static Scene scene;
    private final Stage stage;
    private final StackPane root;
    public GraphicsContext gc;
    public GraphicsContext bgc;


    public Camera(Game game){
        this.game = game;
        Color BACKGROUND_COLOR = Color.BLACK;

        this.stage = new Stage();
        this.root = new StackPane();
        this.canvas = new Canvas(WIDTH,HEIGHT);
        this.bgCanvas = new Canvas(WIDTH,HEIGHT);

        this.gc = canvas.getGraphicsContext2D();
        this.bgc = bgCanvas.getGraphicsContext2D();
        gc.setImageSmoothing(false);
        bgc.setImageSmoothing(false);
        scene = new Scene(root,WIDTH, HEIGHT);




        //fond noir
        Rectangle background = new Rectangle(WIDTH, HEIGHT, Color.BLACK);

        this.stage.setScene(scene);
        root.getChildren().add(background);
        this.root.getChildren().add(bgCanvas);
        this.root.getChildren().add(canvas);
        this.root.getChildren().add(HUD.HUDLayer);


        this.stage.setTitle("Please....SURVIVE!");

        this.stage.setResizable(false);
        this.stage.setFullScreen(true);
        this.stage.setFullScreenExitHint("");


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
        gc.drawImage(game.player.spriteSheet,
                game.player.animationIndex*game.player.spriteSize,
                game.player.status*game.player.spriteSize,
                game.player.spriteSize,
                game.player.spriteSize,
                SPRITE_COORD.getX(),
                SPRITE_COORD.getY() ,
                game.player.size,
                game.player.size);
    }

    //render bullets shot by an entity
    public void renderBullets(Bullet bullet, GraphicsContext g){
        g.drawImage(bullet.sprite, bullet.getCoord().getX()+decalageCameraX, bullet.getCoord().getY()+decalageCameraY,bullet.size,bullet.size);
    }

    //render an entity
    void renderEntity(LivingEntity entity, GraphicsContext g, Coord entityCoord){
        if(entity.getXLookingDirection()==RIGHT){
            g.save();
            g.scale(-1,1);
            g.drawImage(entity.spriteSheet,
                    entity.animationIndex*entity.spriteSize,
                    entity.status*entity.spriteSize,
                    entity.spriteSize,
                    entity.spriteSize,
                    -(entityCoord.getX()+(decalageCameraX))-entity.size,
                    entityCoord.getY()+(decalageCameraY),
                    entity.size,
                    entity.size);
            g.restore();
        }
        else {
            g.drawImage(entity.spriteSheet,
                    entity.animationIndex*entity.spriteSize,
                    entity.status*entity.spriteSize,
                    entity.spriteSize,
                    entity.spriteSize,
                    entityCoord.getX()+(decalageCameraX),
                    entityCoord.getY()+(decalageCameraY),
                    entity.size,
                    entity.size);
        }


    }

    public void renderAll(){
        //clear all
        bgc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.clearRect(0,0,WIDTH,HEIGHT);

        //redraw the map with the movement of the player
        drawMapMatrice();

        //render entities in order of priority (height) to simulate 3D
        for(Entity entity: game.displayedEntities){
            if(entity == game.player){
                playerRender();
            }
            else if(entity instanceof LivingEntity){
                renderEntity((LivingEntity) entity, gc, entity.getCoord());
            }
            else if(entity instanceof Bullet){
                renderBullets((Bullet) entity, bgc);
            }
        }

    }
    public void updateCameraOffset(){
        decalageCameraX=SPRITE_COORD.getX() - game.player.getCoord().getX();
        decalageCameraY=SPRITE_COORD.getY() - game.player.getCoord().getY();
    }






    public void updateHUD(){
        HUD.HPValue.setText(String.valueOf(game.player.getHP()));
        HUD.roundValue.setText(String.valueOf(game.roundCounter));
        HUD.enemiesLeftValue.setText(String.valueOf(game.currentRound.getIngameEnnemyList().size()));






    }


    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }
}
