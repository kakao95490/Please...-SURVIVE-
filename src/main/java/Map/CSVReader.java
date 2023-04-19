package Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe permettant de lire un fichier CSV et de le convertir en matrice
 *
 */

public class CSVReader {

    public static int[][] readCsv(String filename) throws IOException {

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
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }





}
