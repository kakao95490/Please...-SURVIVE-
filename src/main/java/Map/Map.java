package Map;

import Caractere.Player;
import Game.Game;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

import static utils.Constants.MapConstants.*;
import static utils.Constants.WindowConstants.SCALE;

public class Map {
    private Game game;
    private Player player;
    public Image[] textureLib;
    private final int[][] mapMatrice;
    private final int tileSize= (int) (64);


    public Map(Game game) throws URISyntaxException, IOException {
        this.game=game;
        this.player=game.player;
        this.textureLib=new Image[17];
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
        textureLib[OUTCORNERUR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/OutCornerUR.png")).toExternalForm());
        textureLib[OUTCORNERUL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/OutCornerUL.png")).toExternalForm());
        textureLib[OUTCORNERDR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/OutCornerDR.png")).toExternalForm());
        textureLib[OUTCORNERDL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/OutCornerDL.png")).toExternalForm());
        textureLib[DOORR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoorR.png")).toExternalForm());
        textureLib[DOORL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoorL.png")).toExternalForm());
        textureLib[DOORU] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoorU.png")).toExternalForm());
        textureLib[DOORD] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoorD.png")).toExternalForm());
    }

    private int[][] getMapCSV() throws URISyntaxException, IOException {
        URL url = CSVReader.class.getResource("/Map/map.csv");
        assert url != null;
        String file = String.valueOf(Paths.get(url.toURI()).toFile());
        return CSVReader.readCsv(String.valueOf(file));
    }

    public int[][] getMapMatrice() {
        return mapMatrice;
    }


    public void drawMapMatrice(){
        for (int i = 0; i < mapMatrice.length; i++) {
            for (int j = 0; j < mapMatrice[i].length; j++) {
                if(mapMatrice[i][j] != -1) {
                    game.gc.drawImage(textureLib[mapMatrice[i][j]], j * tileSize, i * tileSize,tileSize,tileSize);
                }
            }
        }
    }





}
