package Map;

import Game.Game;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import static utils.Constants.MapConstants.*;

public class Map {
    public Game game;
    public Image[] textureLib;
    private int[][] mapMatrice;


    public Map(Game game) throws URISyntaxException, IOException {
        this.game=game;
        this.textureLib=new Image[9];
        this.mapMatrice = getMapCSV();
        importTexture();


    }

    private void importTexture() {
        textureLib[FLOOR]= new Image(Objects.requireNonNull(getClass().getResource("/Map/Wood.png")).toExternalForm());
        textureLib[WALLR]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallRight.png")).toExternalForm());
        textureLib[WALLL]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallLeft.png")).toExternalForm());
        textureLib[WALLU]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallUp.png")).toExternalForm());
        textureLib[WALLD]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallDown.png")).toExternalForm());
        textureLib[CORNERUR]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallCornerUR.png")).toExternalForm());
        textureLib[CORNERUL]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallCornerUL.png")).toExternalForm());
        textureLib[CORNERDR]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallCornerDR.png")).toExternalForm());
        textureLib[CORNERDL]= new Image(Objects.requireNonNull(getClass().getResource( "/Map/WallCornerDL.png")).toExternalForm());
    }

    private int[][] getMapCSV() throws URISyntaxException, IOException {
        URL url = CSVReader.class.getResource("/Map/map.csv");
        assert url != null;
        String file = String.valueOf(Paths.get(url.toURI()).toFile());
        return CSVReader.readCsv(String.valueOf(file));
    }

}
