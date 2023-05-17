package Entities.Living.GoodGuys;

import Items.Consume.ConsumeItem;
import Items.Weapons.Pistol;
import Items.Weapons.Shotgun;
import Items.Weapons.Uzi;
import Items.Weapons.Weapon;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

import static utils.Constants.MapConstants.TILE_SIZE;
import static utils.Constants.PlayerConstants.STATIC;
import static utils.Constants.Style.font;
import static utils.Constants.WindowConstants.*;
import Items.AbstractItem;
import javafx.scene.text.Text;

public class Seller extends PNJ{

    private final ArrayList<AbstractItem> itemList = new ArrayList<>();
    private final ArrayList<Button> buyButtonList = new ArrayList<>();
    private final VBox menuBox = new VBox();

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
        initInteractionBox();
    }


    @Override
    public void cancelCollision() {

    }

    @Override
    public Node interaction(Player player){
        updateButtons(player);
        return menuBox;

    }


    void initInteractionBox(){

        int menuWidth = 500;
        int menuHeight = 800;
        menuBox.setMinSize(menuWidth,menuHeight);
        menuBox.setMaxSize(menuWidth,menuHeight);
        menuBox.setStyle("-fx-background-color: #A0A445; -fx-border-color: #40421C; -fx-border-width: 5;");
        for(AbstractItem item : itemList){
            HBox itemBox = new HBox();
            itemBox.setMinSize(menuWidth,menuHeight/10);
            itemBox.setMaxSize(menuWidth,menuHeight/10);
            Text itemName = new Text(item.name);
            itemName.setWrappingWidth(menuWidth/2);
            Text itemPrice = new Text(Integer.toString(item.price));
            Button buyButton = new Button("Buy");
            buyButtonList.add(buyButton);


            buyButton.setStyle("-fx-background-color: #40421C; -fx-border-color: #40421C; -fx-border-width: 5; -fx-border-radius: 8;");
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

    }

    public void updateButtons(Player player){
        for(int i = 0; i<buyButtonList.size();i++){
            int finalI = i;
            buyButtonList.get(i).setOnAction(event -> {
                    buyItem(player,itemList.get(finalI));
                    updateButtons(player);
            });
            if(itemList.get(finalI) instanceof Weapon){
                if(Objects.equals(player.getWeapon().name, itemList.get(finalI).name)){
                    buyButtonList.get(finalI).setText("Equipped");
                    buyButtonList.get(finalI).setDisable(true);
                }
                else{
                    buyButtonList.get(finalI).setText("Buy");
                    buyButtonList.get(finalI).setDisable(false);
                }
            }
            else{
                if(player.inventoryIsFull()){
                    buyButtonList.get(finalI).setText("Inventory Full");
                    buyButtonList.get(finalI).setDisable(true);
                }
                else{
                    buyButtonList.get(finalI).setText("Buy");
                    buyButtonList.get(finalI).setDisable(false);
                }
            }
            buyButtonList.get(i).setDisable(player.money < itemList.get(i).price);

        }

    }


    public void buyItem(Player player, AbstractItem item){
        if(player.money>=item.price){
            player.money-=item.price;
            if(item instanceof Weapon) {
                player.setWeapon((Weapon) item);
            }
            else{
                for(int i = 0; i < player.inventory.length ;i++){
                    if(player.inventory[i]==null){
                        player.inventory[i]= (ConsumeItem) item;
                        break;
                    }
                }
            }
        }
    }

    public void initItemList(){
        AbstractItem pistol = new Pistol(this);
        AbstractItem uzi = new Uzi(this);
        AbstractItem shotgun = new Shotgun(this);
        itemList.add(pistol);
        itemList.add(uzi);
        itemList.add(shotgun);
    }


}
