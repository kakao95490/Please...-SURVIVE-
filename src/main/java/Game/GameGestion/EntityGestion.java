package Game.GameGestion;

import Entities.Entity;
import Entities.Living.Enemies.Enemies;
import Entities.Living.GoodGuys.PNJ;
import Entities.Living.GoodGuys.Player;
import Entities.Living.GoodGuys.Seller;
import Entities.Living.LivingEntity;
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

    public static void checkCollideWithPlayer(Player player, Enemies entity){
        entity.detectEntityCollision(player);
        if(entity.getCollisions()[0] || entity.getCollisions()[1] || entity.getCollisions()[2] || entity.getCollisions()[3]){
            entity.isInCollisionWithPlayer=true;
            player.gotHit(entity);
        }
        else {
            entity.isInCollisionWithPlayer=false;
        }
        entity.resetCollisions();
    }

    public static void interactWithPnj(Camera camera,Player player, PNJ pnj){
        if(player.isNearPnj(pnj) && !player.isOnMenu){
                camera.displayDialog(pnj.interaction(player));
                player.isOnMenu=true;
        }
        else if(!player.isNearPnj(pnj) && player.isOnMenu){
            player.isOnMenu=false;
            camera.hideDialog();
        }
    }

    public Player initPlayer(){
        return new Player();
    }

    public PNJ initPNJ(String type){
        switch(type){
            case "Seller":
                return new Seller();
            default:
                return null;
        }
    }







}
