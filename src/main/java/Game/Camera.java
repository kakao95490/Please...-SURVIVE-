package Game;

import Entities.Living.LivingEntity;
import Entities.Inert.Bullet;
import Entities.Entity;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.*;

import javafx.scene.canvas.GraphicsContext;

public class Camera {
    Game game;


    public Camera(Game game){
        this.game = game;

    }



    //draw the map with the movement of the player (decalageCamera)
    public void drawMapMatrice(){
        for (int i = 0; i < game.map.getMapMatrice().length; i++) {
            for (int j = 0; j < game.map.getMapMatrice()[i].length; j++) {
                if(game.map.getMapMatrice()[i][j] != -1) {
                    game.bgc.drawImage(game.map.textureLib[game.map.getMapMatrice()[i][j]], (j * TILE_SIZE) + game.decalageCameraX , (i * TILE_SIZE)+game.decalageCameraY,TILE_SIZE,TILE_SIZE);
                }
            }
        }
    }


    //render the player
    public void playerRender(){
        game.gc.drawImage(game.player.animationLib[game.player.status][game.player.animationIndex], SPRITE_COORD.getX(), SPRITE_COORD.getY() ,TILE_SIZE,TILE_SIZE);
    }

    //render bullets shot by an entity
    public void renderBullets(LivingEntity entity, GraphicsContext g){
        for(Bullet bullet : entity.getWeapon().getBullets()){
            bullet.render(game.gc,game.decalageCameraX,game.decalageCameraY);
        }
    }

    //render an entity
    void renderEntity(LivingEntity entity, GraphicsContext g){
        g.drawImage(entity.animationLib[entity.status][entity.animationIndex], entity.getCoord().getX()+(game.decalageCameraX), entity.getCoord().getY()+(game.decalageCameraY), TILE_SIZE, TILE_SIZE);
    }

    void renderAll(){
        //clear all
        game.bgc.clearRect(0, 0, WIDTH, HEIGHT);
        game.gc.clearRect(0,0,WIDTH,HEIGHT);

        //redraw the map with the movement of the player
        drawMapMatrice();

        //render entities
        playerRender();
        renderBullets(game.player, game.gc);

    }



}
