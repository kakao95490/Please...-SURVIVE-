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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import Caractere.Player;



public class Main extends Application {
    private Game game;
    private final double FPS_SET=120.0;
    private final double timePerFrame=1000000000.0/FPS_SET;
    private long nowTime=System.nanoTime();
    private long lastFrame=System.nanoTime();
    private int frame=0;
    private long lastCheck=System.currentTimeMillis();
    private Player player;
    private GraphicsContext caractereSprite;


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        stage = game.getStage();
        player = new Player(game);
        caractereSprite = game.getCanvas().getGraphicsContext2D();

        stage.show();




        new AnimationTimer() {
            @Override
            public void handle(long now) {
                nowTime=System.nanoTime();
                if(nowTime - lastFrame >= timePerFrame ){
                    update();
                    lastFrame=nowTime;
                    frame++;
                }

                //FPS counter
                if(System.currentTimeMillis() - lastCheck >= 1000){
                    lastCheck=System.currentTimeMillis();
                    System.out.println("FPS : " + frame);
                    frame=0;

                }
            }

        }.start();
    }


    private void update() {
        player.reload(caractereSprite);


    }







    public static void main(String[] args) {
        launch(args);


    }


}
