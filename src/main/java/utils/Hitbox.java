package utils;

import static utils.Constants.MapConstants.TILE_SIZE;

public class Hitbox{
    private Coord entityCoord;
    private Coord cornerUpLeft;
    private Coord cornerDownRight;
    private Coord cornerUpRight;
    private Coord cornerDownLeft;



    public Hitbox(Coord coord) {
        this.entityCoord = coord;
        this.cornerUpLeft = new Coord(coord.getX()+TILE_SIZE/4, coord.getY()+TILE_SIZE/2);
        this.cornerDownRight = new Coord(coord.getX()+TILE_SIZE-TILE_SIZE/4, coord.getY()+TILE_SIZE);
        this.cornerUpRight = new Coord(coord.getX()+TILE_SIZE-TILE_SIZE/4, coord.getY()+TILE_SIZE/2);
        this.cornerDownLeft = new Coord(coord.getX()+TILE_SIZE/4, coord.getY()+TILE_SIZE);
    }

    public void updateHitbox(){
        this.cornerUpLeft.setXY(entityCoord.getX()+TILE_SIZE/4, entityCoord.getY()+TILE_SIZE/2);
        this.cornerDownRight.setXY(entityCoord.getX()+TILE_SIZE-TILE_SIZE/4, entityCoord.getY()+TILE_SIZE);
        this.cornerUpRight.setXY(entityCoord.getX()+TILE_SIZE-TILE_SIZE/4, entityCoord.getY()+TILE_SIZE/2);
        this.cornerDownLeft.setXY(entityCoord.getX()+TILE_SIZE/4, entityCoord.getY()+TILE_SIZE);
    }

    public Coord getCornerUpLeft() {
        return cornerUpLeft;
    }

    public Coord getCornerDownRight() {
        return cornerDownRight;
    }

    public Coord getCornerUpRight() {
        return cornerUpRight;
    }

    public Coord getCornerDownLeft() {
        return cornerDownLeft;
    }

    public boolean isColliding(Coord coord){
        return this.cornerUpLeft.getX() <= coord.getX() && this.cornerUpLeft.getY() <= coord.getY() && this.cornerDownRight.getX() >= coord.getX() && this.cornerDownRight.getY() >= coord.getY();
    }

    public boolean isCollidingFromLeft(Hitbox hitbox) {
        return (this.isColliding(hitbox.getCornerUpLeft()) || this.isColliding(hitbox.getCornerDownLeft()));
    }

    public boolean isCollidingFromRight(Hitbox hitbox) {
        return (this.isColliding(hitbox.getCornerUpRight()) || this.isColliding(hitbox.getCornerDownRight()));
    }



}
