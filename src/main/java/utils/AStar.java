package utils;

import java.util.*;

public class AStar {
    private int[][] matrixrevert;
    private int [][] matrix;// la matrice d'entiers
    private int numRows; // nombre de lignes de la matrice
    private int numCols; // nombre de colonnes de la matrice

    // les mouvements possibles
    private int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    // la classe Node représente une case de la matrice
    private class Node implements Comparable<Node> {
        int row; // ligne de la case
        int col; // colonne de la case
        int f; // f = g + h, où g est le coût pour atteindre cette case depuis le point de départ, et h est une estimation du coût pour atteindre la destination
        int g; // coût pour atteindre cette case depuis le point de départ
        int h; // estimation du coût pour atteindre la destination

        Node parent; // le nœud parent, utilisé pour reconstruire le chemin

        public Node(int row, int col, int g, int h, Node parent) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
            this.f = g + h;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }
    }

    public AStar(int[][] matrix) {
        this.matrix = matrix;
        this.numRows = matrix.length;
        this.numCols = matrix[0].length;
    }

    // retourne true si la case est valide (i.e. contient un 0 et est dans la matrice), false sinon
    private boolean isValid(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && matrix[row][col] == 0;
    }

    // retourne la liste des nœuds voisins valides d'un nœud donné
    private List<Node> getNeighbors(Node node, Node destination) {
        List<Node> neighbors = new ArrayList<>();

        for (int[] direction : directions) {
            int newRow = node.row + direction[0];
            int newCol = node.col + direction[1];

            if (isValid(newRow, newCol)) {
                int g = node.g + 1;
                int h = getDistance(newRow, newCol, destination.row, destination.col);
                Node neighbor = new Node(newRow, newCol, g, h, node);
                neighbors.add(neighbor);
            }
        }

        return neighbors;
    }

    // retourne la distance de Manhattan entre deux cases
    private int getDistance(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }

    // retourne le chemin entre la source et la destination, ou null si aucun chemin n'a été trouvé
    public List<Coord> findPath(Coord source, Coord destination) {
        System.out.println("ça cherche le chemin");
        Node startNode = new Node(source.getX(), source.getY(), 0, getDistance(source.getX(), source.getY(), destination.getX(), destination.getY()), null);
        Node endNode = new Node(destination.getX(), destination.getY(), 0, 0, null);

        Set<Node> visited = new HashSet<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.row == endNode.row && current.col == endNode.col) {
                List<Coord> path = new ArrayList<>();

                while (current != null) {
                    path.add(new Coord(current.col, current.row));
                    current = current.parent;
                }

                Collections.reverse(path);
                System.out.println("chemin trouvé");
                return path;
            }

            visited.add(current);

            for (Node neighbor : getNeighbors(current, endNode)) {
                if (!visited.contains(neighbor)) {
                    if (frontier.contains(neighbor)) {
                        if (neighbor.g < current.g) {
                            frontier.remove(neighbor);
                            frontier.add(neighbor);
                        }
                    } else {
                        frontier.add(neighbor);
                    }
                }
            }
        }

        return null; // aucun chemin trouvé
    }

    public static int[][] inverseMatrice(int[][] matrice) {
        // Obtenir les dimensions de la matrice
        int nbLignes = matrice.length;
        int nbColonnes = matrice[0].length;

        // Créer une nouvelle matrice pour stocker les valeurs inversées
        int[][] matriceInverse = new int[nbColonnes][nbLignes];

        // Remplir la matrice inversée avec les valeurs inversées
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceInverse[j][i] = matrice[i][j];
            }
        }

        return matriceInverse;
    }

    public static void main(String[] args) {
        int[][] matrice = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        AStar aStar = new AStar(matrice);
        List<Coord> path = aStar.findPath(new Coord(0, 0), new Coord(12, 8));
        for(Coord coord : path) {
            System.out.println(coord);
        }
    }

}