package Game;

import Entities.BaseMonke;
import Entities.Entity;

import java.util.ArrayList;

import static utils.Constants.EntityConstants.BASE_MONKE;

public class Level {
    private ArrayList<Entity> waitingEnnemyList = new ArrayList<>();
    private ArrayList<Entity> ingameEnnemyList = new ArrayList<>();
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
