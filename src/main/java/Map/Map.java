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
import static utils.Constants.WindowConstants.SPRITE_COORD;

public class Map {
    public Image[] textureLib;
    private final int[][] mapMatrice;


    public Map() throws URISyntaxException, IOException {
        this.textureLib=new Image[31];
        this.mapMatrice = getMapCSV();
        importTexture();
        CSVReader.printMatrix(mapMatrice);
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
        textureLib[CHAIR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/Chair.png")).toExternalForm());
        textureLib[CHAIR2] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/Chair2.png")).toExternalForm());
        textureLib[DOUBLEWALL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoubleWallVertical.png")).toExternalForm());
        textureLib[DOUBLEWALLCORNERD] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoubleWallCornerD.png")).toExternalForm());
        textureLib[DOUBLEWALLCORNERU] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/DoubleWallCornerU.png")).toExternalForm());
        textureLib[TABLEU] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableU.png")).toExternalForm());
        textureLib[TABLED] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableD.png")).toExternalForm());
        textureLib[TABLEUR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableUR.png")).toExternalForm());
        textureLib[TABLEUL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableUL.png")).toExternalForm());
        textureLib[TABLEDR] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableDR.png")).toExternalForm());
        textureLib[TABLEDL] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TableDL.png")).toExternalForm());
        textureLib[TRIPLEWALLDOWN] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TripleWallDown.png")).toExternalForm());
        textureLib[TRIPLEWALLUP] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/TripleWallUp.png")).toExternalForm());
        textureLib[PLANT] = new Image(Objects.requireNonNull(getClass().getResource( "/Map/Plant.png")).toExternalForm());

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








}
