package Entities.Living;

import utils.AStar;
import utils.Coord;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.Directions.*;
import static utils.Constants.WindowConstants.FPS_TARGET;

public abstract class Enemies extends LivingEntity{
    private List<Coord> path = new ArrayList<>();

    private int updatePathTick = FPS_TARGET/4;
    private int updatePathIndex = updatePathTick;




    public void setPath(List<Coord> path) {
        this.path = path;
    }

    public void updatePath(AStar aStar){
        setPath(aStar.findPath(coord.centeredCoord().tileCoord().invertedCoord(),playerCoord.centeredCoord().tileCoord().invertedCoord()));
        for (Coord value : path) {
            System.out.println(value);
        }
    }


    public void updatePos(AStar aStar) {
        if (updatePathIndex == 0) {
            updatePathIndex = updatePathTick;
            updatePath(aStar);
        } else {
            updatePathIndex--;
        }
        if(path != null) {

            if( path.size()>3 ){
                Coord nextTile = path.get(0);
                destCoord = nextTile.pixelCoord().centeredCoord();
                directionCalcul();
                coord.addXY(movement.getX(), movement.getY());
                if (coord.centeredCoord().tileCoord() == nextTile) {
                    path.remove(0);
                }

            }

            else if (path.size() > 2 && !isNearPlayer()) {
                Coord nextTile = path.get(2);
                destCoord = nextTile.pixelCoord().centeredCoord();
                directionCalcul();
                coord.addXY(movement.getX(), movement.getY());
                if (coord.centeredCoord().tileCoord() == nextTile) {
                    path.remove(0);
                    path.remove(1);
                    path.remove(2);
                }
            }
            else {
                destCoord = playerCoord.centeredCoord();
                directionCalcul();
                coord.addXY(movement.getX(), movement.getY());
            }
        }
    }

    public boolean isNearPlayer(){
        for(int i=playerCoord.tileCoord().getX()-2;i<=playerCoord.tileCoord().getX()+2;i++){
            for(int j=playerCoord.tileCoord().getY()-2;j<=playerCoord.tileCoord().getY()+2;j++){
                if(coord.tileCoord().getX()==i && coord.tileCoord().getY()==j){
                    return true;
                }
            }
        }
        return false;
    }


}
