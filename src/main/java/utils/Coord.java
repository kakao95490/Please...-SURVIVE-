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

    public void setX(int x) {
        this.X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        this.Y = y;
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

    public Coord hitboxCoord(){
        return new Coord(X+ (int) (TILE_SIZE * 0.5), Y + (int) (TILE_SIZE * 0.5));
    }


}
