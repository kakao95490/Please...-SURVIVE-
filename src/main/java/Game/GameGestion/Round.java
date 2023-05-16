package Game.GameGestion;

import Entities.Living.Enemies.Axie;
import Entities.Living.Enemies.BaseMonke;
import Entities.Living.Enemies.Enemies;
import Entities.Living.Enemies.Maskass;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.*;


/**
 * Gère les rounds
 */
public class Round {
    private final ArrayList<Enemies> waitingEnnemyList = new ArrayList<>();
    private final ArrayList<Enemies> ingameEnnemyList = new ArrayList<>();
    private int frameBetweenSpawn;
    private int frameBetweenSpawnCounter = frameBetweenSpawn;

    public Round(int[] entityList, ArrayList<int[]> spawnList, int frameBetweenSpawn){
        this.frameBetweenSpawn = frameBetweenSpawn;
        int rdmNbr;
        for (int j : entityList) {
            rdmNbr = (int) (Math.random() * spawnList.size());
            switch (j) {
                case BASE_MONKE -> waitingEnnemyList.add(new BaseMonke(spawnList.get(rdmNbr)[1], spawnList.get(rdmNbr)[0]));
                case AXIE -> waitingEnnemyList.add(new Axie(spawnList.get(rdmNbr)[1], spawnList.get(rdmNbr)[0]));
                case MASKASS -> waitingEnnemyList.add(new Maskass(spawnList.get(rdmNbr)[1], spawnList.get(rdmNbr)[0]));
            }
        }

    }

    /**
     * Met à jour les rounds, si il n'y a plus d'ennemis à faire spawn, retourne false pour indiquer que le round est fini
     * @return
     */
    public boolean update(){
        if(waitingEnnemyList.size() == 0 && ingameEnnemyList.size() == 0){
            return false;
        }
        else if(frameBetweenSpawnCounter == 0){
            if (waitingEnnemyList.size() > 0){
                ingameEnnemyList.add(waitingEnnemyList.get(0));
                waitingEnnemyList.remove(0);
                frameBetweenSpawnCounter = frameBetweenSpawn;
            }
        } else {
            frameBetweenSpawnCounter--;
        }
        return true;
    }

    public ArrayList<Enemies> getIngameEnnemyList() {
        return ingameEnnemyList;
    }
    public ArrayList<Enemies> getWaitingEnnemyList() {
        return waitingEnnemyList;
    }


}
