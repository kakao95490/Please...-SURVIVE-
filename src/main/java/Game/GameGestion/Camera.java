package Game.GameGestion;

import Entities.Entity;
import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;

import static utils.Constants.BonusConstants.*;
import static utils.Constants.Directions.RIGHT;
import static utils.Constants.GameConstants.WIN;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.Style.font;
import static utils.Constants.Style.font2;
import static utils.Constants.WindowConstants.*;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.Coord;

import java.io.IOException;
import java.net.URISyntaxException;

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

    public Scene endScene;
    public StackPane endRoot;
    public VBox endLayer;


    public VBox bonusMenu;



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

        initBonusMenu();





    }

    private void initBonusMenu(){
        bonusMenu = new VBox();
        int menuWidth = 500;
        int menuHeight = 800;
        bonusMenu.setMinSize(menuWidth,menuHeight);
        bonusMenu.setMaxSize(menuWidth,menuHeight);
        bonusMenu.setStyle("-fx-background-color: #A0A445; -fx-border-color: #40421C; -fx-border-width: 5;");
        Text title = new Text("Choose a bonus");
        title.setFont(font);
        bonusMenu.getChildren().add(title);
        Button health = new Button("Health +");
        Button damage = new Button("Damage +");
        Button speed = new Button("Speed +");
        health.setStyle("-fx-background-color: #40421C; -fx-border-color: #40421C; -fx-border-width: 5; -fx-border-radius: 8;");
        health.setTextFill(Color.YELLOW);
        damage.setStyle("-fx-background-color: #40422C; -fx-border-color: #40421C; -fx-border-width: 5; -fx-border-radius: 8;");
        damage.setTextFill(Color.YELLOW);
        speed.setStyle("-fx-background-color: #40421C; -fx-border-color: #40421C; -fx-border-width: 5; -fx-border-radius: 8;");
        speed.setTextFill(Color.YELLOW);

        health.setOnAction(e -> {
            EntityGestion.bonus(HEALTH);
            HUD.HUDLayer.getChildren().remove(bonusMenu);
            HUD.HUDLayer.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        damage.setOnAction(e -> {
            EntityGestion.bonus(DAMAGE);
            HUD.HUDLayer.getChildren().remove(bonusMenu);
            HUD.HUDLayer.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        speed.setOnAction(e -> {
            EntityGestion.bonus(SPEED);
            HUD.HUDLayer.getChildren().remove(bonusMenu);
            HUD.HUDLayer.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY)));
        });
        bonusMenu.getChildren().addAll(health, damage,speed);
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
        gc.drawImage(EntityGestion.player.spriteSheet,
                EntityGestion.player.animationIndex*EntityGestion.player.spriteSize,
                EntityGestion.player.status*EntityGestion.player.spriteSize,
                EntityGestion.player.spriteSize,
                EntityGestion.player.spriteSize,
                SPRITE_COORD.getX(),
                SPRITE_COORD.getY() ,
                EntityGestion.player.size,
                EntityGestion.player.size);
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
        updateHUD();
        bgc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.clearRect(0,0,WIDTH,HEIGHT);

        //redraw the map with the movement of the player
        drawMapMatrice();

        //render entities in order of priority (height) to simulate 3D
        for(Entity entity: game.EntityGestion.displayedEntities){
            if(entity == EntityGestion.player){
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
        decalageCameraX=SPRITE_COORD.getX() - EntityGestion.player.getCoord().getX();
        decalageCameraY=SPRITE_COORD.getY() - EntityGestion.player.getCoord().getY();
    }






    public void updateHUD(){
        HUD.HPValue.setText(String.valueOf(EntityGestion.player.getHP()));
        HUD.roundValue.setText(String.valueOf(game.roundCounter));
        HUD.enemiesLeftValue.setText(String.valueOf(game.currentRound.getIngameEnnemyList().size()));
        HUD.moneyValue.setText(String.valueOf(EntityGestion.player.money));
    }


    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void displayDialog(Node interaction) {
        HUD.HUDLayer.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        HUD.HUDLayer.setCenter(interaction);
    }

    public void hideDialog() {
        HUD.HUDLayer.setBackground(null);
        HUD.HUDLayer.getChildren().remove(HUD.HUDLayer.getCenter());
    }


    public void displayEndScreen(int end){
        endRoot = new StackPane();
        endLayer = new VBox();
        endRoot.getChildren().add(endLayer);
        endScene = new Scene(endRoot,WIDTH,HEIGHT);
        endScene.setFill(Color.BLACK);
        stage.setScene(endScene);
        endLayer.setAlignment(Pos.CENTER);
        if(end == WIN){
            Label victory = new Label("VICTORY");

            victory.setFont(font2);
            victory.setTextFill(Color.GREEN);
            victory.setAlignment(Pos.CENTER);

            endLayer.getChildren().add(victory);
            endLayer.setMinHeight(HEIGHT);
            endLayer.setMinWidth(WIDTH);
        }
        else{

            Label defeat = new Label("DEFEAT");

            defeat.setFont(font2);
            defeat.setTextFill(Color.RED);
            defeat.setAlignment(Pos.CENTER);

            endLayer.getChildren().add(defeat);
            endLayer.setMinHeight(HEIGHT);
            endLayer.setMinWidth(WIDTH);

        }
        Button quit = new Button("Quit");
        Button restart = new Button("Restart");
        restart.setFont(font2);
        restart.setTextFill(Color.WHITE);
        restart.setStyle("-fx-background-color: black;");
        quit.setFont(font2);
        quit.setTextFill(Color.WHITE);
        quit.setStyle("-fx-background-color: black;");
        endLayer.setSpacing(60);

        restart.setOnAction(e -> {
            try {
                game.cleanGame();
                game.initGame();
            } catch (URISyntaxException | IOException ex) {
                throw new RuntimeException(ex);
            }

            game.gameLoop();
            stage.setScene(scene);

        });

        quit.setOnAction(e -> {
            stage.close();
        });

        endLayer.getChildren().addAll(restart,quit);

    }


    public void bonusMenu() {
        HUD.HUDLayer.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.5), CornerRadii.EMPTY, Insets.EMPTY)));
        HUD.HUDLayer.setCenter(bonusMenu);
    }

}
