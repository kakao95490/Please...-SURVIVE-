package Game.GameGestion;

import Entities.Living.Enemies.Axie;
import Entities.Living.Enemies.BaseMonke;
import Entities.Living.Enemies.Enemies;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.AXIE;
import static utils.Constants.EntityConstants.BASE_MONKE;

public class Round {
    private ArrayList<Enemies> waitingEnnemyList = new ArrayList<>();
    private ArrayList<Enemies> ingameEnnemyList = new ArrayList<>();
    private int frameBetweenSpawn;
    private int frameBetweenSpawnCounter = frameBetweenSpawn;

    public Round(int[] entityList, ArrayList<int[]> spawnList, int frameBetweenSpawn){
        this.frameBetweenSpawn = frameBetweenSpawn;
        int rdmNbr;
        for (int i = 0; i < entityList.length; i++) {
            rdmNbr = (int) (Math.random() * spawnList.size());
            switch (entityList[i]){
                case BASE_MONKE:
                    waitingEnnemyList.add(new BaseMonke(spawnList.get(rdmNbr)[1],spawnList.get(rdmNbr)[0]));
                    break;
                case AXIE:
                    waitingEnnemyList.add(new Axie(spawnList.get(rdmNbr)[1],spawnList.get(rdmNbr)[0]));
                    break;
            }
        }

    }

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
