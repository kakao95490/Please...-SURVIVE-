package Game;

import javafx.animation.Animation;
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


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        game.getStage().show();

        int FPS_TARGET = 120;
        double timePerFrame = (double) 1000.0 / FPS_TARGET;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timePerFrame), event -> {

            game.deltaTime = (System.nanoTime() - lastTime) / 1000000000.0;
            lastTime = System.nanoTime();

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                game.framerate = (int) (1.0 / game.deltaTime);
                System.out.println(game.framerate);
                System.out.println(frame);
                frame=0;
            }

            update();
            frame++;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


    private void update() {
        game.player.reload(game.gc);
        game.reloadCanvas();
    }







    public static void main(String[] args) {
        launch(args);
    }


}
