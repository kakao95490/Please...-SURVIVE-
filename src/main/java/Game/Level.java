package Game;

import Entities.Living.BaseMonke;
import Entities.Entity;
import Entities.Living.Ennemies;
import Entities.Living.LivingEntity;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.BASE_MONKE;

public class Level {
    private ArrayList<Ennemies> waitingEnnemyList = new ArrayList<>();
    private ArrayList<Ennemies> ingameEnnemyList = new ArrayList<>();
    private final int frameBetweenSpawn = 60;
    private int frameBetweenSpawnCounter = 60;

    public Level(int[] entityList) {
        for (int i = 0; i < entityList.length; i++) {
            switch (entityList[i]){
                case BASE_MONKE:
                    waitingEnnemyList.add(new BaseMonke());
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

    public ArrayList<Ennemies> getIngameEnnemyList() {
        return ingameEnnemyList;
    }




}
