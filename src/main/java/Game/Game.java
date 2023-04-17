package Game;

import Map.Map;
import Entities.Entity;
import Entities.Player;
import Inputs.KeyboardInput;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.WindowConstants.*;


public class Game {
    public int dx=0,dy=0;
    private final Canvas playerCanvas;
    private final Canvas backgroundCanvas;
    private static Scene scene;
    private final Stage stage;
    private final Group root;
    private TilePane tilePane;
    public GraphicsContext gc;
    public GraphicsContext bgc;

    public KeyboardInput keyboardInput;
    public Double framerate;

    public List<Entity> entities;
    public Player player;
    public Map map;
    public Camera camera;
    public PerspectiveCamera perspectiveCamera;




    public Game() throws IOException, URISyntaxException {

        this.stage = new Stage();
        this.root = new Group();
        this.perspectiveCamera = new PerspectiveCamera();
        this.perspectiveCamera.setScaleX(1/SCALE);
        this.perspectiveCamera.setScaleY(1/SCALE);

        //set a canva for the player
        this.playerCanvas = new Canvas(WIDTH,HEIGHT);
        this.backgroundCanvas = new Canvas(WIDTH,HEIGHT);

        gc = playerCanvas.getGraphicsContext2D();
        bgc = backgroundCanvas.getGraphicsContext2D();

        gc.setImageSmoothing(false);
        bgc.setImageSmoothing(false);


        scene = new Scene(root,WIDTH, HEIGHT);
        Color BACKGROUND_COLOR = Color.BLACK;
        scene.setFill(BACKGROUND_COLOR);
        this.stage.setScene(scene);
        root.getChildren().add(backgroundCanvas);
        root.getChildren().add(playerCanvas);

        this.stage.setTitle("Please....SURVIVE!");

        this.stage.setResizable(true);
        this.stage.setFullScreen(false);
        this.keyboardInput = new KeyboardInput();


        this.player=new Player(this);
        this.map = new Map();
        entities = new ArrayList<>();
        entities.add(player);

        this.camera= new Camera(this);
        this.scene.setCamera(perspectiveCamera);


    }

    public Canvas getCanvas() {
        return playerCanvas;
    }
    public Canvas getBackgroundCanvas() {
        return backgroundCanvas;
    }

    public static Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Group getRoot() {
        return root;
    }


}

