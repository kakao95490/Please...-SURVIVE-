package Entities.Living;

import Inputs.KeyboardInput;
import Game.Game;
import Weapons.Pistol;
import utils.Coord;
import utils.Hitbox;

import static utils.Constants.Directions.*;
import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.WindowConstants.*;

public class Player extends LivingEntity {
    private Game game;


    public Player(Game game){
        super();
        this.entityName = "Player";
        this.speed= (int) (3*SCALE);
        this.size = TILE_SIZE;
        this.game=game;
        generateAnimationLib(); //generate the animation library
        this.status=STATIC; //set the player status
        this.coord.setXY(11*TILE_SIZE, 8*TILE_SIZE);//set the player coord spawn
        this.hitbox.setHitboxSize(size/2);
        this.hitbox.setHitboxOffset(size/4,size/2);
        this.hitbox.updateHitbox();
        this.weapon = new Pistol(); //set the player weapon

    }




    public void updatePos(){
        movement.setXY(0,0);
        if(game.keyboardInput.directionDiagonal()){
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, UP) && game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY((int) (speed * 0.80), (int) (-speed * 0.80));
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, DOWN) && game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY((int) (speed * 0.80), (int) (speed * 0.80));
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, UP) && game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY((int) (-speed * 0.80), (int) (-speed * 0.80));
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, DOWN) && game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY((int) (-speed * 0.80), (int) (speed * 0.80));
            }
        }
        else {
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, UP)) {
                movement.addXY(0, -speed);
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, DOWN)) {
                movement.addXY(0, speed);
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, LEFT)) {
                movement.addXY(-speed, 0);
            }
            if (game.keyboardInput.isKeyPressed(game.keyboardInput.movementKeyPressed, RIGHT)) {
                movement.addXY(speed, 0);
            }
        }
        coord.addXY(movement.getX(),movement.getY());
        hitbox.updateHitbox();
    }

    public void updateShootingDirection(){
        XlookingDirection=-1;
        YlookingDirection=-1;
        if(game.keyboardInput.isKeyPressed(game.keyboardInput.shootKeyPressed, UP)){
            YlookingDirection=UP;
        }
        if(game.keyboardInput.isKeyPressed(game.keyboardInput.shootKeyPressed, DOWN)){
            YlookingDirection=DOWN;
        }
        if(game.keyboardInput.isKeyPressed(game.keyboardInput.shootKeyPressed, LEFT)){
            XlookingDirection=LEFT;
        }
        if(game.keyboardInput.isKeyPressed(game.keyboardInput.shootKeyPressed, RIGHT)){
            XlookingDirection=RIGHT;
        }
        shooting();
    }

    public void shooting(){
        if(XlookingDirection!=-1 || YlookingDirection!=-1){
            shoot();
        }
        weapon.updateCooldown();

    }

    public void updateStatus() {
        if(previousStatus==HIT && animationIndex+2<=getSpriteAmount(HIT)){
            status=HIT;
        }
        else{
            if(game.keyboardInput.isEmpty(game.keyboardInput.movementKeyPressed)) {
                status = STATIC;
            }
            if(!game.keyboardInput.isEmpty(game.keyboardInput.movementKeyPressed)){
                status=WALKING;
            }
            if(game.keyboardInput.testKey){
                shooting();
            }
        }
        if(previousStatus!=status){
            animationIndex=0;
        }
        previousStatus=status;
    }







}
