package Map;

import Game.Game;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static utils.Constants.MapConstants.*;
import static utils.Constants.WindowConstants.SCALE;

public class Map {
    public Game game;
    public Image[] textureLib;


    public Map(Game game) throws IOException {
        this.game=game;
        game.gc = game.getCanvas().getGraphicsContext2D();
        game.gc.setImageSmoothing(false);
        this.textureLib=new Image[9];
        importTexture();
        int tab[][] = readCsv("C:\\Users\\lucas\\OneDrive\\Documents\\GitHub\\Please...-SURVIVE-\\src\\main\\resources\\Map\\map.csv");
        printMatrix(tab);


    }

    public void importTexture() throws IOException {
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


    public static int[][] readCsv(String filename) throws IOException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        int numRows = 0;
        int numCols = 0;

        while ((line = reader.readLine()) != null) {
            numRows++;
            String[] row = line.split(",");
            numCols = Math.max(numCols, row.length);
        }

        // Création de la matrice
        int[][] matrix = new int[numRows][numCols];

        // Remplissage de la matrice avec les valeurs du fichier
        reader = new BufferedReader(new FileReader(filename));
        int rowIdx = 0;
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            for (int colIdx = 0; colIdx < row.length; colIdx++) {
                matrix[rowIdx][colIdx] = Integer.parseInt(row[colIdx]);
            }
            rowIdx++;
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
