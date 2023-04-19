package Game;

import Entities.Living.BaseMonke;
import Entities.Living.Enemies;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.BASE_MONKE;

public class Level {
    private ArrayList<Enemies> waitingEnnemyList = new ArrayList<>();
    private ArrayList<Enemies> ingameEnnemyList = new ArrayList<>();
    private final int frameBetweenSpawn = 60;
    private int frameBetweenSpawnCounter = 60;

    public Level(int[] entityList, ArrayList<int[]> spawnList){
        int rdmNbr;
        for (int i = 0; i < entityList.length; i++) {
            rdmNbr = (int) (Math.random() * spawnList.size());
            switch (entityList[i]){
                case BASE_MONKE:
                    waitingEnnemyList.add(new BaseMonke(spawnList.get(rdmNbr)[1],spawnList.get(rdmNbr)[0]));
                    break;
            }
        }

    }

    public void update(){
        if (frameBetweenSpawnCounter == 0){
            if (waitingEnnemyList.size() > 0){
                ingameEnnemyList.add(waitingEnnemyList.get(0));
                waitingEnnemyList.remove(0);
                frameBetweenSpawnCounter = frameBetweenSpawn;
            }
        } else {
            frameBetweenSpawnCounter--;
        }
    }

    public ArrayList<Enemies> getIngameEnnemyList() {
        return ingameEnnemyList;
    }


}
