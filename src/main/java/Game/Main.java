package Game;

import Game.GameGestion.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.stage.Stage;


import javafx.util.Duration;

import static utils.Constants.WindowConstants.FPS_TARGET;


public class  Main extends Application {

    private Game game;
    private Menu menu;




    @Override
    public void start(Stage stage) throws Exception {


        game = new Game();
        game.startGame();



    }







    public static void main(String[] args) {
        launch(args);
    }


}
