package utils;

import static utils.Constants.MapConstants.TILE_SIZE;

public class Coord {
    private int X;
    private int Y;

    public Coord(int x, int y){
        this.X=x;
        this.Y=y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return X == coord.X && Y == coord.Y;
    }

    @Override
    public String toString() {
        return "(" + X + "," + Y + ")";
    }

    public void addXY(int x, int y){
        this.X+=x;
        this.Y+=y;
    }

    public void setXY(int x, int y){
        this.X=x;
        this.Y=y;
    }

    public Coord tileCoord(){
        return new Coord(X/TILE_SIZE, Y/TILE_SIZE);
    }

    public Coord pixelCoord(int x, int y){
        return new Coord(x*TILE_SIZE, y*TILE_SIZE);
    }

}
