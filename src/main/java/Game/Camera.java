package Game;

import Caractere.Player;

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
        for (int i = 0; i < map.getMapMatrice().length; i++) {
            for (int j = 0; j < map.getMapMatrice()[i].length; j++) {
                if(map.getMapMatrice()[i][j] != -1) {
                    game.gc.drawImage(map.textureLib[map.getMapMatrice()[i][j]], (j * TILE_SIZE)-player.getCoord().getX()+ SPRITE_COORD.getX(), (i * TILE_SIZE)-player.getCoord().getY()+ SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);
                }
            }
        }
    }

    public void playerReload(){
        player.updateAnimationIndex(player.animationLib[player.status]);
        player.updatePos();
        player.updateStatus();
        game.gc.drawImage(player.animationLib[player.status][player.animationIndex], SPRITE_COORD.getX(), SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);
    }


}
