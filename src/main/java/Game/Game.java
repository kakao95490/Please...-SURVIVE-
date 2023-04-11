package Game;

import Inputs.KeyboardInputs;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import static javafx.application.Application.launch;

public class Game {
    public int dx=0,dy=0;
    private Canvas canvas;
    private Scene scene;
    private Stage stage;
    private Group root;
    public KeyboardInputs inputs;
    public Game(){
        this.stage = new Stage();
        this.root =new Group();
        this.scene = new Scene(root);
        this.stage.setScene(this.scene);
        this.canvas = new Canvas(1920,1080);
        root.getChildren().add(canvas);
        this.stage.setTitle("Please....SURVIVE!");
        this.stage.setResizable(false);
        inputs = new KeyboardInputs(scene);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Group getRoot() {
        return root;
    }
}

