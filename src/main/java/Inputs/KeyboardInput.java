package Inputs;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Game.Game;

public class KeyboardInput {
    private Pane root;
    private Scene scene;
    private boolean[] keysPressed;

    public KeyboardInput(Game game) {
        keysPressed = new boolean[256];

        // écouteur d'événements pour les touches pressées
        Game.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            KeyCode keyCode = event.getCode();
            keysPressed[keyCode.ordinal()] = true;
        });

        // écouteur d'événements pour les touches relâchées
        Game.getScene().addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            KeyCode keyCode = event.getCode();
            keysPressed[keyCode.ordinal()] = false;
        });

    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keysPressed[keyCode.ordinal()];
    }

    public void clear() {
        keysPressed = new boolean[256];
    }
}