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



public class Main extends Application {
    private Game game;
    private double FPS_SET=120.0;
    private double timePerFrame=1000000000.0/FPS_SET;
    private long nowTime=System.nanoTime();
    private long lastFrame=System.nanoTime();
    private int frame=0;
    private long lastCheck=System.currentTimeMillis();

    @Override
    public void start(Stage stage) throws Exception {
        game = new Game();
        stage = game.getStage();
        WritableImage writableImage = new WritableImage(100,100);
        writableImage
        PixelReader pixelReader = writableImage.getPixelReader();


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



    }







    public static void main(String[] args) {
        launch(args);


    }


}
