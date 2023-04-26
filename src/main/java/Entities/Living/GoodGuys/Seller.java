package Entities.Living.GoodGuys;

import Objects.Weapons.Pistol;
import Objects.Weapons.Uzi;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.STATIC;
import static utils.Constants.Style.font;
import static utils.Constants.WindowConstants.*;
import Objects.AbstractObjects;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Constants.Style.*;

public class Seller extends PNJ{

    public ArrayList<AbstractObjects> itemList = new ArrayList<>();

    public Seller(){
        super();
        this.entityName = "PNJ";
        this.spriteSize=64;
        this.size = (int) (spriteSize*SCALE);
        this.speed= (int) (3*SCALE);
        this.spriteSheet = new Image(Objects.requireNonNull(getClass().getResource("/Sprites/PnjSPRITESHEET.png")).toExternalForm());
        this.hitbox.setHitboxSize(size/3, size-size/3);
        this.hitbox.setHitboxOffset(size/3,size/3);

        this.status=STATIC;
        this.coord.setXY(18*TILE_SIZE, 11*TILE_SIZE);
        this.hitbox.updateHitbox();
        initItemList();
    }

    @Override
    public void cancelCollision() {

    }

    public void initMenu(BorderPane HUD){
        VBox menuBox = new VBox();
        int menuWidth = 500;
        int menuHeight = 800;
        menuBox.setMinSize(menuWidth,menuHeight);
        menuBox.setMaxSize(menuWidth,menuHeight);
        menuBox.setStyle("-fx-background-color: #A0A445; -fx-border-color: #40421C; -fx-border-width: 5;");
        for(AbstractObjects item : itemList){
            HBox itemBox = new HBox();
            itemBox.setMinSize(menuWidth,menuHeight/10);
            itemBox.setMaxSize(menuWidth,menuHeight/10);
            Text itemName = new Text(item.name);
            Text itemPrice = new Text(Integer.toString(10));
            Button buyButton = new Button("Buy");
            buyButton.setStyle("-fx-background-color: #40421C; -fx-border-color: #40421C; -fx-border-width: 5;");
            buyButton.setTextFill(Color.YELLOW);

            itemName.setFont(font);
            itemName.setFill(Color.valueOf("#40421C"));
            itemPrice.setFont(font);
            itemPrice.setFill(Color.YELLOW);
            itemBox.getChildren().addAll(itemName,itemPrice,buyButton);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setSpacing(20);
            menuBox.getChildren().add(itemBox);
        }


        HUD.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.5), CornerRadii.EMPTY, Insets.EMPTY)));

        HUD.setCenter(menuBox);

    }

    public void initItemList(){
        AbstractObjects pistol = new Pistol(this);
        AbstractObjects uzi = new Uzi(this);
        itemList.add(pistol);
        itemList.add(uzi);
    }


}
