package Caractere;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface InterfaceEntity {
    void generateAnimationLib();
    void updatePos();
    void updateAnimationIndex(Image[] lib);
    void updateStatus();
}
