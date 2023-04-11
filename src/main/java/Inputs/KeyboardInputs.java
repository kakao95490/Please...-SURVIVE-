package Inputs;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardInputs {

    private Scene scene;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyboardInputs(Scene scene) {
        this.scene = scene;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP) {
                    upPressed = true;
                } else if (code == KeyCode.DOWN) {
                    downPressed = true;
                } else if (code == KeyCode.LEFT) {
                    leftPressed = true;
                } else if (code == KeyCode.RIGHT) {
                    rightPressed = true;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.UP) {
                    upPressed = false;
                } else if (code == KeyCode.DOWN) {
                    downPressed = false;
                } else if (code == KeyCode.LEFT) {
                    leftPressed = false;
                } else if (code == KeyCode.RIGHT) {
                    rightPressed = false;
                }
            }
        });
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

}
