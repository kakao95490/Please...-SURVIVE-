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
    private final double FPS_SET=60.0;
    private final double timePerFrame=1000.0/FPS_SET;
    private long nowTime=System.nanoTime();
    private long lastFrame=System.nanoTime();
    private Integer frame=0;
    private long lastCheck=System.currentTimeMillis();
    private Player player;

    private GraphicsContext fpsDisplay;


    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        game.getStage().show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timePerFrame), event ->{
            update();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
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
