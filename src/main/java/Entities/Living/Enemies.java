package Entities.Living;

import utils.AStar;
import utils.Coord;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.Directions.*;
import static utils.Constants.WindowConstants.FPS_TARGET;

public abstract class Enemies extends LivingEntity{
    private List<Coord> path = new ArrayList<>();

    private int updatePathTick = FPS_TARGET/6;
    private int updatePathIndex = updatePathTick;




    public void setPath(List<Coord> path) {
        this.path = path;
    }

    public void updatePath(AStar aStar){
        System.out.println(coord.tileCoord());
        setPath(aStar.findPath(coord.tileCoord(),playerCoord.tileCoord()));
        for (Coord value : path) {
            System.out.println(value);
        }
        System.out.println("  ");
    }

    /*public void updatePos(AStar aStar) {
        if (updatePathIndex == 0) {
            updatePathIndex = updatePathTick;
            updatePath(aStar);
        } else {
            updatePathIndex--;
        }
        if (path != null) {
            if (path.size() > 0) {
                Coord nextCoord = path.get(0);
                if (coord.tileCoord().getX() < nextCoord.getX()) {
                    coord.addXY(speed, 0);
                    XlookingDirection = RIGHT;
                } else if (coord.tileCoord().getX() > nextCoord.getX()) {
                    coord.addXY(-speed, 0);
                    XlookingDirection = LEFT;
                }
                if (coord.tileCoord().getY() < nextCoord.getY()) {
                    coord.addXY(0, speed);
                    YlookingDirection = DOWN;
                } else if (coord.tileCoord().getY() > nextCoord.getY()) {
                    coord.addXY(0, -speed);
                    YlookingDirection = UP;
                }
                if (coord.tileCoord().equals(nextCoord)) {
                    path.remove(0);
                }
            }
        }
    }*/


    public void updatePos() {
        System.out.println(playerCoord.tileCoord());

        if (coord.getX() < playerCoord.getX()) {
            coord.addXY(speed, 0);
            XlookingDirection = RIGHT;
            Xdirection= RIGHT;
            System.out.println("ça va à droite");
        } else if (coord.getX() > playerCoord.getX()) {
            coord.addXY(-speed, 0);
            XlookingDirection = LEFT;
            Xdirection= LEFT;
        }
        if (coord.getY() < playerCoord.getY()) {
            coord.addXY(0, speed);
            YlookingDirection = DOWN;
            Ydirection= DOWN;
        } else if (coord.getY() > playerCoord.getY()) {
            coord.addXY(0, -speed);
            YlookingDirection = UP;
            Ydirection= UP;
        }
    }

}
