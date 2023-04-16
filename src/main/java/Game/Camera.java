package Game;

import Entities.Bullet;
import Entities.Entity;
import Entities.Player;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.SPRITE_COORD;
import Map.Map;

public class Camera {
    Game game;
    Player player;
    Map map;


    public Camera(Game game){
        this.game = game;
        this.player = game.player;
        this.map = game.map;
    }


    public void drawMapMatrice(){

        System.out.println(player.getCoord().tileCoord());
        for (int i = 0; i < map.getMapMatrice().length; i++) {
            for (int j = 0; j < map.getMapMatrice()[i].length; j++) {
                if(map.getMapMatrice()[i][j] != -1) {
                    game.gc.drawImage(map.textureLib[map.getMapMatrice()[i][j]], (j * TILE_SIZE)-player.getCoord().getX()+ SPRITE_COORD.getX(), (i * TILE_SIZE)-player.getCoord().getY()+ SPRITE_COORD.getY(),TILE_SIZE,TILE_SIZE);
                }
            }
        }
    }

    public void playerReload(){
        player.updateAnimationIndex(player.animationLib[player.status]);
        player.updatePos();
        player.updateDirection();
        cancelWallCollision(player);
        player.updateStatus();
        player.getWeapon().updateBullets(game.gc);


    }

    public void playerRender(){
        game.gc.drawImage(player.animationLib[player.status][player.animationIndex], SPRITE_COORD.getX(), SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);


    }


    public void collideDirection(Entity entity){
        entity.resetWallCollision();
        entity.setWallCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
        entity.setWallCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
        entity.setWallCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
    }

    public void cancelWallCollision(Entity entity){
        collideDirection(entity);
        //int[0] = up and down, int[1] = left and right
        int[] direction = new int[]{0,0};
        if(entity.getWallCollision()[0]) {
            if (player.getYDirection() == UP){
                direction[0] += 1;
            }
            if (player.getXDirection() == LEFT){
                direction[1] += 1;
            }
        }
        if(entity.getWallCollision()[1]){
            if (player.getYDirection() == UP){
                direction[0] += 1;
            }
            if (player.getXDirection() == RIGHT){
                direction[1] -= 1;
            }
        }
        if(entity.getWallCollision()[2]){
            if (player.getYDirection() == DOWN){
                direction[0] -= 1;
            }
            if (player.getXDirection() == LEFT){
                direction[1] += 1;
            }
        }
        if(entity.getWallCollision()[3]) {
            if (player.getYDirection() == DOWN){
                direction[0] -= 1;
            }
            if (player.getXDirection() == RIGHT){
                direction[1] -= 1;
            }
        }
        while(entity.getWallCollision()[0] || entity.getWallCollision()[1] || entity.getWallCollision()[2] || entity.getWallCollision()[3]) {

            if(direction[1] < 0) {
                player.getCoord().addXY(-1,0);
            } else if(direction[1] > 0) {
                player.getCoord().addXY(1,0);
            }
            if(direction[0] < 0) {
                player.getCoord().addXY(0,-1);
            } else if(direction[0] > 0) {
                player.getCoord().addXY(0,1);
            }
            player.getCoord().addXY(direction[1], direction[0]);
            player.getHitbox().updateHitbox();
            collideDirection(entity);
        }


    }



}
