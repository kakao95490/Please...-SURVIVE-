package Entities;

import javafx.scene.image.Image;

public interface InterfaceEntity {
    void generateAnimationLib();
    void updatePos();
    void updateAnimationIndex(Image[] lib);
    void updateStatus();
    void updateDirection();
}
