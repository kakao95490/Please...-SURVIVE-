package Game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;


import javafx.util.Duration;

import static utils.Constants.WindowConstants.FPS_TARGET;


public class  Main extends Application {

    private Game game;

    int frame = 0;
    long lastCheck=System.currentTimeMillis();
    double timePerFrame = 1000.0 / FPS_TARGET;


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
        game.gc.clearRect(0, 0, game.getCanvas().getWidth(), game.getCanvas().getHeight());
        game.camera.playerReload();
        game.camera.drawMapMatrice();
        game.camera.playerRender();


    }







    public static void main(String[] args) {
        launch(args);
    }


}
