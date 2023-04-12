package Game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Caractere.Player;



public class Main extends Application {
    private Game game;
    private final double FPS_SET=120.0;
    private final double timePerFrame=1000000000.0/FPS_SET;
    private long nowTime=System.nanoTime();
    private long lastFrame=System.nanoTime();
    private Integer frame=0;
    private long lastCheck=System.currentTimeMillis();
    private Player player;

    private GraphicsContext fpsDisplay;


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        stage = game.getStage();
        player = new Player(game);
        stage.show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now - lastFrame >= timePerFrame ){
                    update();
                    lastFrame=now;
                    frame++;
                }

                //FPS counter
                if(now - lastCheck >= 1000000000){
                    lastCheck=System.nanoTime();
                    System.out.println("FPS : " + frame);
                    frame=0;
                }

            }

        }.start();
    }


    private void update() {
        player.reload(game.gc);


    }







    public static void main(String[] args) {
        launch(args);


    }


}
