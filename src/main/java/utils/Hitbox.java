package utils;

public class Hitbox{
    private final Coord entityCoord;
    private Coord cornerUpLeft;
    private Coord cornerDownRight;
    private Coord cornerUpRight;
    private Coord cornerDownLeft;
    private int sizeX;
    private int sizeY;
    private int offsetX;
    private int offsetY;



    public Hitbox(Coord coord, int sizeX,int sizeY, int offsetX, int offsetY) {
        this.entityCoord = coord;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.cornerUpLeft = new Coord(coord.getX()+offsetX, coord.getY()+offsetY);
        this.cornerDownRight = new Coord(coord.getX()+offsetX+sizeX, coord.getY()+offsetY+sizeY);
        this.cornerUpRight = new Coord(coord.getX()+offsetX+sizeX, coord.getY()+offsetY);
        this.cornerDownLeft = new Coord(coord.getX()+offsetX, coord.getY()+offsetY+sizeY);
    }

    public void updateHitbox(){
        this.cornerUpLeft = new Coord(entityCoord.getX()+offsetX, entityCoord.getY()+offsetY);
        this.cornerDownRight = new Coord(entityCoord.getX()+offsetX+sizeX, entityCoord.getY()+offsetY+sizeY);
        this.cornerUpRight = new Coord(entityCoord.getX()+offsetX+sizeX, entityCoord.getY()+offsetY);
        this.cornerDownLeft = new Coord(entityCoord.getX()+offsetX, entityCoord.getY()+offsetY+sizeY);
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

    public boolean isColliding(Hitbox hitbox){
        return this.isCollidingFromTopLeft(hitbox) || this.isCollidingFromTopRight(hitbox) || this.isCollidingFromBottomLeft(hitbox) || this.isCollidingFromBottomRight(hitbox);
    }

    public void setHitboxSize(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        updateHitbox();
    }
    public void setHitboxOffset(int offsetX, int offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        updateHitbox();
    }

    public Coord centeredCoord(){
        return new Coord(this.cornerUpLeft.getX()+(this.sizeX/2),this.cornerUpLeft.getY()+(this.sizeY/2));
    }

    public int getSizeX() {
        return sizeX;
    }
    public int getSizeY() {
        return sizeY;
    }



}
