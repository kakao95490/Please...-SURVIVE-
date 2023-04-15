package Game;

import Caractere.Player;

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
        wallCollision();
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
        player.updateStatus();
        player.updateDirection();
    }

    public void playerRender(){
        game.gc.drawImage(player.animationLib[player.status][player.animationIndex], SPRITE_COORD.getX(), SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);
    }

    public boolean isWalkable(){
        return map.getMapMatrice()[player.getHitbox().getCornerUpLeft().tileCoord().getY()][player.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0
                || map.getMapMatrice()[player.getHitbox().getCornerUpRight().tileCoord().getY()][player.getHitbox().getCornerUpRight().tileCoord().getX()] != 0
                || map.getMapMatrice()[player.getHitbox().getCornerDownLeft().tileCoord().getY()][player.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0
                || map.getMapMatrice()[player.getHitbox().getCornerDownRight().tileCoord().getY()][player.getHitbox().getCornerDownRight().tileCoord().getX()] != 0;
    }

    public void wallCollision(){
        if(isWalkable()){
            switch (player.getDirection()) {
                case UP -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(0, 1);
                        player.getHitbox().updateHitbox();
                    }
                }
                case DOWN -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(0, -1);
                        player.getHitbox().updateHitbox();
                    }
                }
                case LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, 0);
                        player.getHitbox().updateHitbox();

                    }
                }
                case RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, 0);
                        player.getHitbox().updateHitbox();
                    }
                }
                case UP_RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, 1);
                        player.getHitbox().updateHitbox();

                    }
                }
                case UP_LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, 1);
                        player.getHitbox().updateHitbox();
                    }
                }
                case DOWN_RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, -1);
                        player.getHitbox().updateHitbox();
                    }
                }
                case DOWN_LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, -1);
                        player.getHitbox().updateHitbox();
                    }
                }
            }

        }


    }





}
