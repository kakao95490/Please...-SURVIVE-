package utils;

public class Hitbox{
    private final Coord entityCoord;
    private Coord cornerUpLeft;
    private Coord cornerDownRight;
    private Coord cornerUpRight;
    private Coord cornerDownLeft;
    private int size;
    private int offsetX;
    private int offsetY;



    public Hitbox(Coord coord, int size, int offsetX, int offsetY) {
        this.entityCoord = coord;
        this.size = size;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.cornerUpLeft = new Coord(coord.getX()+offsetX, coord.getY()+offsetY);
        this.cornerDownRight = new Coord(coord.getX()+offsetX+size, coord.getY()+offsetY+size);
        this.cornerUpRight = new Coord(coord.getX()+offsetX+size, coord.getY()+offsetY);
        this.cornerDownLeft = new Coord(coord.getX()+offsetX, coord.getY()+offsetY+size);
    }

    public void updateHitbox(){
        this.cornerUpLeft = new Coord(entityCoord.getX()+offsetX, entityCoord.getY()+offsetY);
        this.cornerDownRight = new Coord(entityCoord.getX()+offsetX+size, entityCoord.getY()+offsetY+size-1);
        this.cornerUpRight = new Coord(entityCoord.getX()+offsetX+size, entityCoord.getY()+offsetY);
        this.cornerDownLeft = new Coord(entityCoord.getX()+offsetX, entityCoord.getY()+offsetY+size-1);
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

    public boolean isCollidingFromTopLeft(Hitbox hitbox) {
        return this.isColliding(hitbox.getCornerUpLeft());
    }

    public boolean isCollidingFromTopRight(Hitbox hitbox) {
        return this.isColliding(hitbox.getCornerUpRight());
    }

    public boolean isCollidingFromBottomLeft(Hitbox hitbox) {
        return this.isColliding(hitbox.getCornerDownLeft());
    }

    public boolean isCollidingFromBottomRight(Hitbox hitbox) {
        return this.isColliding(hitbox.getCornerDownRight());
    }


    public void setHitboxSize(int size){
        this.size = size;
    }
    public void setHitboxOffset(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }



}
