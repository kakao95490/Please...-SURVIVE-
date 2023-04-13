package Game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;


import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;


public class Main extends Application {

    private Game game;

    int frame = 0;
    long lastCheck=System.currentTimeMillis();
    int FPS_TARGET = 60;
    double timePerFrame = (double) 1000.0 / FPS_TARGET;
    private long lastTime = System.nanoTime();


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        game.getStage().show();




        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timePerFrame), event -> {


            if (System.currentTimeMillis() - lastCheck >= 500) {
                lastCheck = System.currentTimeMillis();
                game.framerate= (double) (frame*2);
                System.out.println("fps " + game.framerate);
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
