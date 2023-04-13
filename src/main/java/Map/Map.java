package Map;

import Game.Game;
import javafx.scene.image.Image;

import java.util.Objects;

import static utils.Constants.MapConstants.*;
import static utils.Constants.WindowConstants.SCALE;

public class Map {
    public Game game;
    public Image[] textureLib;


    public Map(Game game){
        this.game=game;
        game.gc = game.getCanvas().getGraphicsContext2D();
        game.gc.setImageSmoothing(false);
        this.textureLib=new Image[9];
        importTexture();

    }

    public void importTexture(){
        String path ="/Map/";

        textureLib[FLOOR]= new Image(Objects.requireNonNull(getClass().getResource(path + "Wood.png")).toExternalForm());
        textureLib[WALLR]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallRight.png")).toExternalForm());
        textureLib[WALLL]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallLeft.png")).toExternalForm());
        textureLib[WALLU]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallUp.png")).toExternalForm());
        textureLib[WALLD]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallDown.png")).toExternalForm());
        textureLib[CORNERUR]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallCornerUR.png")).toExternalForm());
        textureLib[CORNERUL]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallCornerUL.png")).toExternalForm());
        textureLib[CORNERDR]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallCornerDR.png")).toExternalForm());
        textureLib[CORNERDL]= new Image(Objects.requireNonNull(getClass().getResource(path + "WallCornerDL.png")).toExternalForm());

    }
}
