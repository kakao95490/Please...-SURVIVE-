package Game.GameGestion;

import Entities.Entity;
import Map.Map;

public class EntityGestion {

    public static boolean detectWallCollision(Entity entity, Map map){

        entity.getHitbox().updateHitbox();
        entity.resetCollisions();
        entity.setCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
        entity.setCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
        entity.setCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
        entity.setCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
        return entity.getCollisions()[0] || entity.getCollisions()[1] || entity.getCollisions()[2] || entity.getCollisions()[3];
    }


}
