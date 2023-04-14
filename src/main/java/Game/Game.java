package Game;

import Map.Map;
import Caractere.Entity;
import Caractere.Player;
import Inputs.KeyboardInput;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;
import static utils.Constants.MapConstants.*;
import static utils.Constants.WindowConstants.*;


public class Game {
    public int dx=0,dy=0;
    private final Canvas canvas;
    private static Scene scene;
    private final Stage stage;
    private final Group root;
    public KeyboardInput keyboardInput;
    public GraphicsContext gc;
    public List<Entity> entities;
    public Player player;
    public Map map;
    public Double framerate;


    public Game() throws IOException, URISyntaxException {

        this.stage = new Stage();
        this.root =new Group();
        scene = new Scene(root,WIDTH, HIGH);
        this.stage.setScene(scene);
        this.canvas = new Canvas(scene.getWidth(),scene.getWidth());
        root.getChildren().add(canvas);
        this.stage.setTitle("Please....SURVIVE!");

        this.stage.setResizable(true);
        this.stage.setFullScreen(true);
        this.keyboardInput = new KeyboardInput();


        this.player=new Player(this);
        this.map = new Map(this);
        entities = new ArrayList<>();
        entities.add(player);

        gc = getCanvas().getGraphicsContext2D();
        gc.setImageSmoothing(false);
    }

    public Canvas getCanvas() {
        return canvas;
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


    public void reloadCanvas(){
        gc.clearRect(0,0,1920,1080);
        for(int x=0;x<=canvas.getWidth();x+=64*SCALE){
            for(int y=0;y<=canvas.getHeight();y+=64*SCALE){
                if(x==0 && y==0){
                    gc.drawImage(map.textureLib[CORNERUL],x,y,map.textureLib[FLOOR].getWidth()*SCALE,map.textureLib[FLOOR].getHeight()*SCALE);
                }
                else if(x==0){
                    gc.drawImage(map.textureLib[WALLL],x,y,map.textureLib[FLOOR].getWidth()*SCALE,map.textureLib[FLOOR].getHeight()*SCALE);
                }
                else if(y==0){
                    gc.drawImage(map.textureLib[WALLU],x,y,map.textureLib[FLOOR].getWidth()*SCALE,map.textureLib[FLOOR].getHeight()*SCALE);
                }
                else{
                    gc.drawImage(map.textureLib[FLOOR],x,y,map.textureLib[FLOOR].getWidth()*SCALE,map.textureLib[FLOOR].getHeight()*SCALE);
                }
            }

        }



    }



    private void setWindow(){

    }
}

