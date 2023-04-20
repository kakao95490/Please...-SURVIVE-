package Map;

import javafx.scene.image.Image;
import utils.AStar;
import utils.Coord;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static utils.Constants.MapConstants.*;

public class Map {
    public Image[] textureLib;
    private final int[][] mapMatrice;
    private ArrayList<int[]> spwanCoords = new ArrayList<>();


    public Map() throws URISyntaxException, IOException {
        this.textureLib=new Image[31];
        this.mapMatrice = getMapCSV();
        importTexture();
        CSVReader.printMatrix(mapMatrice);
        setSpwanCoords(mapMatrice);
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

    // Renvoie les coordonnées de spawn de la map (les portes)
    public void setSpwanCoords(int[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == DOORD || matrice[i][j] == DOORL || matrice[i][j] == DOORR || matrice[i][j] == DOORU){
                    if (i > 0 && matrice[i-1][j] == FLOOR) { // Case au-dessus
                        spwanCoords.add(new int[]{i-1, j});
                    }
                    if (i < matrice.length-1 && matrice[i+1][j] == FLOOR) { // Case en-dessous
                        spwanCoords.add(new int[]{i+1, j});
                    }
                    if (j > 0 && matrice[i][j-1] == FLOOR) { // Case à gauche
                        spwanCoords.add(new int[]{i, j-1});
                    }
                    if (j < matrice[i].length-1 && matrice[i][j+1] == FLOOR) { // Case à droite
                        spwanCoords.add(new int[]{i, j+1});
                    }
                }
            }
        }

    }

    public ArrayList<int[]> getSpwanCoords() {
        return spwanCoords;
    }






}
