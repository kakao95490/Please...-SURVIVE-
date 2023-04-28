package Entities.Living.GoodGuys;

import Entities.Living.LivingEntity;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class PNJ extends LivingEntity {
    protected static double detectionRange=100;
    protected HBox interactionBox = new HBox();
    public PNJ(){
        super();
    }


    public double getDetectionRange() {
        return detectionRange;
    }

    public abstract Node interaction(Player player);
}
