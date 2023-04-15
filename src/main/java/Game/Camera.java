package Game;

import Caractere.Player;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.SPRITE_COORD;
import Map.Map;
import Caractere.Entity;

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
                    game.gc.drawImage(map.textureLib[map.getMapMatrice()[i][j]], (j * TILE_SIZE)-player.getCoord().getX()+ SPRITE_COORD.getX() + TILE_SIZE*0.5, (i * TILE_SIZE)-player.getCoord().getY()+ SPRITE_COORD.getY()+TILE_SIZE,TILE_SIZE,TILE_SIZE);
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
        return map.getMapMatrice()[player.getCoord().tileCoord().getY()][player.getCoord().tileCoord().getX()] != 0;
    }

    public void wallCollision(){
        if(isWalkable() ){
            switch (player.getDirection()) {
                case UP -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(0, 1);
                    }
                }
                case DOWN -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(0, -1);
                    }
                }
                case LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, 0);
                    }
                }
                case RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, 0);
                    }
                }
                case UP_RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, 1);

                    }
                }
                case UP_LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, 1);
                    }
                }
                case DOWN_RIGHT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(-1, -1);
                    }
                }
                case DOWN_LEFT -> {
                    while (isWalkable()) {
                        player.getCoord().addXY(1, -1);
                    }
                }
            }

        }


    }





}
