package Game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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



import javafx.util.Duration;


public class Main extends Application {

    private Game game;

    int frame = 0;
    long lastCheck=System.currentTimeMillis();

    private long lastTime = System.nanoTime();
    private long timePerFrame;


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        game.getStage().show();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(System.currentTimeMillis() - lastCheck >= 1000){
                    lastCheck=System.currentTimeMillis();
                    System.out.println(frame);
                    frame=0;
                    System.out.println(game.deltaTime);

                }
                game.deltaTime =(now - lastTime) / 1000000000.0;
                lastTime = now;

                update();
                frame++;


            }
        }.start();

    }


    private void update() {
        game.player.reload(game.gc);
        game.reloadCanvas();
    }







    public static void main(String[] args) {
        launch(args);
    }


}
