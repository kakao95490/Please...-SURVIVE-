package Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
    public Sprite(Image image) {
        super(image);
    }

    public void moveX(int x) {
        setX(getX() + x);
    }

    public void moveY(int y) {
        setY(getY() + y);
    }
}
