package Game;

import Game.GameGestion.Game;
import javafx.application.Application;

import javafx.stage.Stage;



public class  Main extends Application {

    private Game game;




    @Override
    public void start(Stage stage) throws Exception {

        game = new Game();
        game.initGame();
        game.gameLoop();

    }







    public static void main(String[] args) {
        launch(args);
    }


}
