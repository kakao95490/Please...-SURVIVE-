package Entities.Living.GoodGuys;

import Entities.Living.LivingEntity;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class PNJ extends LivingEntity {

    protected HBox interactionBox = new HBox();
    public PNJ(){
        super();
    }




    public abstract Node interaction(Player player);
}
