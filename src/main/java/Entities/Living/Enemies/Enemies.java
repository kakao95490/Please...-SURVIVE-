package Entities.Living.Enemies;

import Entities.Living.LivingEntity;
import utils.AStar;
import utils.Coord;

import java.util.ArrayList;
import java.util.List;

import static utils.Constants.Directions.*;
import static utils.Constants.PlayerConstants.HIT;
import static utils.Constants.WindowConstants.FPS_TARGET;

public abstract class Enemies extends LivingEntity {
    private List<Coord> path = new ArrayList<>();

    private final int updatePathTick = FPS_TARGET/2;
    private int updatePathIndex = updatePathTick;

    public boolean isInCollisionWithPlayer = false;



    public void setPath(List<Coord> path) {
        this.path = path;
    }

    public void updatePath(AStar aStar){
        setPath(aStar.findPath(coord.centeredCoord().tileCoord().invertedCoord(),playerCoord.centeredCoord().tileCoord().invertedCoord()));
    }

    public void updatePos(AStar aStar) {
        movement.setXY(0,0);
        prevCoord.setXY(coord.getX(), coord.getY());
        if(isInCollisionWithPlayer){
            return;
        }

        //find the nearest path 4 times per second
        if (updatePathIndex == 0) {
            updatePathIndex = updatePathTick;
            updatePath(aStar);
        } else {
            updatePathIndex--;
        }
        if(path != null) {
            //if the player is too far, the enemy will follow the astar path
            if (path.size() > 2 && !isNearPlayer()) {
                Coord nextTile = path.get(1);
                if (hitbox.centeredCoord().tileCoord().equals(nextTile)) {
                    path.remove(0);
                    path.remove(1);
                    nextTile = path.get(0);
                }
                destCoord = nextTile.pixelCoord().centeredCoord();
                directionCalcul();
                coord.addXY(movement.getX(), movement.getY());
            }
            //if the player is near, the enemy will directly the player
            else {
                destCoord = playerCoord.centeredCoord();
                directionCalcul();
                coord.addXY(movement.getX(), movement.getY());
            }
            hitbox.updateHitbox();
            updateDirection();
        }
    }

    @Override
    public void cancelCollision() {
        int[] cancelCollision = {0, 0};

        if(collisions[0]){
            cancelCollision[0]+=1;
            cancelCollision[1]+=1;
        }
        if(collisions[1]){
            cancelCollision[0]-=1;
            cancelCollision[1]+=1;
        }
        if(collisions[3]){
            cancelCollision[0]-=1;
            cancelCollision[1]-=1;
        }
        if(collisions[2]){
            cancelCollision[0]+=1;
            cancelCollision[1]-=1;
        }

        if(cancelCollision[0]>0){
            coord.addXY(1,0);
        }
        if(cancelCollision[0]<0){
            coord.addXY(-1,0);
        }
        if(cancelCollision[1]>0){
            coord.addXY(0,1);
        }
        if(cancelCollision[1]<0){
            coord.addXY(0,-1);
        }
        hitbox.updateHitbox();
    }

    /**
     * Check if the player is near the enemy
     * Used to change the direction of the enemy
     */
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
