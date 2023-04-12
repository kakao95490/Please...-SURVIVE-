package Caractere;

import javafx.scene.image.Image;

public abstract class Entity {
    protected int X=500,Y=500;
    protected int animationTick = 0;
    protected int animationIndex;
    protected final int animationSpeed=2;
    protected static Image[][] animationLib;
    protected String pathSource="file:C:\\Users\\lucas\\OneDrive\\Documents\\GitHub\\Please...-SURVIVE-\\resources\\Images\\";
    protected int maxSpd=7;
    protected int maxDiagSpd=80*maxSpd/100;

    public Entity(){
    }
}
