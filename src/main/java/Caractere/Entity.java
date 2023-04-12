package Caractere;

import javafx.scene.image.Image;

public abstract class Entity implements InterfaceEntity {
    public int X=500,Y=500;
    public int animationTick = 0;
    public int animationIndex;
    public final int animationSpeed=1000;
    public static Image[][] animationLib;
    public String pathSource="file:C:\\Users\\lucas\\Documents\\GitHub\\Please...-SURVIVE-\\resources\\Images\\";
    public int status;
    public String entityName;

}
