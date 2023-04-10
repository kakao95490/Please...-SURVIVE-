package caractere;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface InterfaceCaractere {
    BufferedImage importImg(String str);
    void updateAnimation(BufferedImage[] animLib);
    void loadAnimLib(String str);

}
