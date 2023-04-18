package Game;

import Entities.Living.BaseMonke;
import Entities.Entity;
import Entities.Living.LivingEntity;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.BASE_MONKE;

public class Level {
    private ArrayList<LivingEntity> waitingEnnemyList = new ArrayList<>();
    private ArrayList<LivingEntity> ingameEnnemyList = new ArrayList<>();
    private int frameBetweenSpawn = 500;

    public Level(int[] entityList) {
        for (int i = 0; i < entityList.length; i++) {
            switch (entityList[i]){
                case BASE_MONKE:
                    waitingEnnemyList.add(new BaseMonke());
                    break;
            }
        }
    }

}
