package Caractere;

import javafx.scene.image.Image;

public abstract class Entity implements InterfaceEntity {
    public int X=500,Y=500;
    public int animationTick = 0;
    public int animationIndex;
    public final int animationSpeed=2;
    public static Image[][] animationLib;
    public String pathSource="file:C:\\Users\\lucas\\OneDrive\\Documents\\GitHub\\Please...-SURVIVE-\\resources\\Images\\";
    public int maxSpd=7;
    public int maxDiagSpd=80*maxSpd/100;
    public int status;
    public String entityName;

}
