package Game.GameGestion;

import Entities.Entity;
import Entities.Inert.Bullet;
import Entities.Living.Enemies.Enemies;
import Entities.Living.GoodGuys.PNJ;
import Entities.Living.GoodGuys.Player;
import Entities.Living.GoodGuys.Seller;
import Entities.Living.LivingEntity;
import Map.Map;
import utils.AStar;

import java.util.ArrayList;
import java.util.Comparator;

import static utils.Constants.BonusConstants.*;
import static utils.Constants.PlayerConstants.DEAD;
import static utils.Constants.PlayerConstants.STATIC;

public class EntityGestion {
    public static Player player;
    private ArrayList<PNJ> pnjList = new ArrayList<>();
    public ArrayList<Entity> displayedEntities = new ArrayList<>();



    /**
     * Initialise player et pnj
     *
     */
    public void initEntities(){
        player=new Player();
        pnjList.add(initPNJ("Seller"));
    }


    /**
     * Detecte les collisions entre une entité et les murs. Si il y a bien une collision, l'information va être entrée dans un tableau de booléens {@link Entity#getCollisions}
     * @param entity
     * @param map
     * @return
     */
    public boolean detectWallCollision(Entity entity, Map map){

        entity.getHitbox().updateHitbox();
        entity.resetCollisions();
        entity.setCollision(0, map.getMapMatrice()[entity.getHitbox().getCornerUpLeft().tileCoord().getY()][entity.getHitbox().getCornerUpLeft().tileCoord().getX()] != 0);
        entity.setCollision(1, map.getMapMatrice()[entity.getHitbox().getCornerUpRight().tileCoord().getY()][entity.getHitbox().getCornerUpRight().tileCoord().getX()] != 0);
        entity.setCollision(2, map.getMapMatrice()[entity.getHitbox().getCornerDownLeft().tileCoord().getY()][entity.getHitbox().getCornerDownLeft().tileCoord().getX()] != 0);
        entity.setCollision(3, map.getMapMatrice()[entity.getHitbox().getCornerDownRight().tileCoord().getY()][entity.getHitbox().getCornerDownRight().tileCoord().getX()] != 0);
        return entity.getCollisions()[0] || entity.getCollisions()[1] || entity.getCollisions()[2] || entity.getCollisions()[3];
    }


    /**
     * Detecte les collisions entre le player et les autres entités. Si il y a bien une collision, l'entité va être immobilisé
     * @param player
     * @param entity
     */
    public void checkCollideWithPlayer(Player player, Enemies entity){
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


    /**
     * Detecte la proximité entre le player et un pnj. Si le player est assez proche, le pnj va lui parler.
     * @param pnj
     * @param camera
     */
    public void interactWithPnj(Camera camera,Player player, PNJ pnj){
        if(player.isNearPnj(pnj) && !player.isOnMenu){
                camera.displayDialog(pnj.interaction(player));
                player.isOnMenu=true;
        }
        else if(!player.isNearPnj(pnj) && player.isOnMenu){
            player.isOnMenu=false;
            camera.hideDialog();
        }
    }




    /**
     * Initialise un pnj en fonction de son type
     * @param type
     * @return
     */
    public PNJ initPNJ(String type){
        if (type.equals("Seller")) {
            return new Seller();
        }
        return null;
    }

    /**
     * Update the player, bonus used, items used, position, collisions, shooting direction, status, animation index
     * @param camera
     * @param map
     */
    public void playerUpdate(Camera camera, Map map){
        player.updateBonus();
        player.useItem();
        player.updatePos();
        for (PNJ pnj : pnjList) {
            interactWithPnj(camera, player, pnj);
        }

        while(detectWallCollision(player,map)){
            player.cancelCollision();
        }
        player.updateShootingDirection();
        player.updateStatus();
        player.updateAnimationIndex();
    }

    public void updatePnj() {
        for(PNJ pnj : pnjList) {
            pnj.updateAnimationIndex();
        }
    }

    /**
     * Update the enemies, position, collisions, status, animation index
     * @param currentRound
     * @param aStar A* algorithm pour suivre le player
     * @param map
     */
    public void updateEnemies(Round currentRound, AStar aStar, Map map){
        Enemies currentEnemy;

        //for each enemy
        for(int i = 0; i< currentRound.getIngameEnnemyList().size(); i++){
            currentEnemy = currentRound.getIngameEnnemyList().get(i);

            //check if the enemy is hit by a bullet
            for(int j=0;j<player.getWeapon().getBullets().size();j++){
                currentEnemy.gotHitByBullet(player.getWeapon().getBullets().get(j));
            }



            //death animation
            if(currentEnemy.status==DEAD){
                currentEnemy.updateStatus();
                currentEnemy.updateAnimationIndex();
                if(!currentEnemy.isAlive){
                    currentRound.getIngameEnnemyList().remove(currentEnemy);
                }
                continue;
            }

            //if the enemy is dead, launch the death animation
            if(currentEnemy.checkIfDied()){
                continue;
            }

            if(currentEnemy.cantMove){
                currentEnemy.updateStatus();
                currentEnemy.updateAnimationIndex();
                continue;
            }

            //update the enemy position (A* algorithm to follow the player)
            currentEnemy.updatePos(aStar);

            //check collision with other enemies
            for(int j = 0; j< currentRound.getIngameEnnemyList().size(); j++){
                if(i==j){
                    continue;
                }
                currentEnemy.updateEntityCollisions(currentRound.getIngameEnnemyList().get(j));
            }

            //cancel wall collision
            while(detectWallCollision(currentEnemy,map)){
                if((currentEnemy.getCollisions()[0]&&currentEnemy.getCollisions()[1]&&currentEnemy.getCollisions()[2]&&currentEnemy.getCollisions()[3]) ){
                    break;
                }
                currentEnemy.cancelCollision();
            }
            currentEnemy.resetCollisions();
            checkCollideWithPlayer(player,currentEnemy);

            currentEnemy.updateStatus();
            currentEnemy.updateAnimationIndex();
        }

    }

    /**
     * Update the bullets shot all the entities (only the player for now)
     * TODO : update the bullets shot by the enemies (if they have a weapon)
     */
    public void updateBullets( Map map){
        for (Entity value : displayedEntities) {
            if (value instanceof Player entity) {
                for (int i = 0; i < entity.getWeapon().getBullets().size(); i++) {
                    Bullet bullet = entity.getWeapon().getBullets().get(i);
                    if (bullet.status == STATIC) {

                        entity.getWeapon().getBullets().remove(bullet);
                    } else {

                        bullet.updateStatus();
                        bullet.updatePos();
                        if (detectWallCollision(bullet, map)) {
                            bullet.status = STATIC;
                        }
                        bullet.getHitbox().updateHitbox();
                    }
                }
            }
        }
    }


    /**
     * Update la liste des entités affichées à l'écran (player, pnj, enemies, bullets) et les trie par ordre de position y pour une impression de profondeur
     * @param inGameEnemies
     */
    public void updateDisplayedEntitiesList(ArrayList<Enemies> inGameEnemies){
        displayedEntities.clear();
        displayedEntities.add(player);
        displayedEntities.addAll(pnjList);
        displayedEntities.addAll(inGameEnemies);
        displayedEntities.addAll(getPlayer().getWeapon().getBullets());
        displayedEntities.sort(Comparator.comparingInt(o -> o.getHitbox().getCornerDownLeft().getY()));
    }

    public boolean isPlayerDead(){
        return player.getHP() <= 0;
    }



    public Player getPlayer() {
        return player;
    }

    public ArrayList<PNJ> getPnjList() {
        return pnjList;
    }

    public void cleanEntities() {
        for (int i = 0; i < displayedEntities.size(); i++) {
            if (displayedEntities.get(i).status == STATIC) {
                displayedEntities.remove(i);
            }
        }
    }


    static void bonus(int type){
        switch (type){
            case HEALTH -> {
                player.setMaxHP(player.getMaxHP() + 15);
                player.setHP(player.getMaxHP());
            }
            case SPEED -> player.addSpeed(1);
            case DAMAGE -> player.addDmg(0.15);
        }

    }

}
