package Game.GameGestion;

import javafx.css.Style;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static utils.Constants.Style.font;
import static utils.Constants.Style.font2;
import static utils.Constants.WindowConstants.HEIGHT;
import static utils.Constants.WindowConstants.WIDTH;

public class HUD {
    public final BorderPane HUDLayer;
    public Text HPValue;
    public Text moneyValue;
    public Text roundValue;
    public Text enemiesLeftValue;
    public static Text timerValue;


    public HUD() {
        this.HUDLayer = new BorderPane();
        setHUDLayer();
    }

    public void setHUDLayer(){
        HBox upHudContainer = new HBox(); // conteneur pour les éléments du HUD
        upHudContainer.setPadding(new Insets(10));
        upHudContainer.setAlignment(Pos.TOP_LEFT);
        upHudContainer.setPrefWidth(WIDTH);
        upHudContainer.setMaxWidth(WIDTH);
        upHudContainer.setMinWidth(WIDTH);


        //HP and Money Box
        VBox HPandMoney = new VBox();
        HBox HPBox = new HBox();
        HBox MoneyBox = new HBox();
        Text HPText = new Text("HP : " );
        Text MoneyText = new Text("Money : " );
        moneyValue = new Text("");
        HPValue = new Text("");
        MoneyBox.getChildren().addAll(MoneyText, moneyValue);
        HPBox.getChildren().addAll(HPText, HPValue);
        HPandMoney.getChildren().addAll(HPBox, MoneyBox);
        HBox.setHgrow(HPandMoney, Priority.ALWAYS);

        HBox roundBox = new HBox();
        Text roundText = new Text("Round : ");
        roundValue = new Text("");
        roundBox.getChildren().addAll(roundText, roundValue);
        HBox.setHgrow(roundBox, Priority.ALWAYS);

        VBox enemiesLeftBox = new VBox();
        enemiesLeftBox.setMaxWidth(300);
        Text enemiesLeftText = new Text("Enemies left : ");
        enemiesLeftValue = new Text("");
        enemiesLeftBox.getChildren().addAll(enemiesLeftText, enemiesLeftValue);
        HBox.setHgrow(enemiesLeftBox, Priority.ALWAYS);
        enemiesLeftBox.setAlignment(Pos.CENTER);



        upHudContainer.getChildren().addAll(HPandMoney, roundBox, enemiesLeftBox);
        HUDLayer.setTop(upHudContainer);

        BorderStroke borderStroke = new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3));

        // Style

        HPText.setFont(font);
        HPText.setFill(javafx.scene.paint.Color.GREEN);
        HPText.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        HPValue.setFont(font);
        HPValue.setFill(javafx.scene.paint.Color.GREEN);
        MoneyText.setFont(font);
        MoneyText.setFill(Color.YELLOW);
        moneyValue.setFont(font);
        moneyValue.setFill(Color.YELLOW);
        roundText.setFont(font2);
        roundText.setFill(Color.RED);
        roundValue.setFont(font2);
        roundValue.setFill(Color.RED);
        enemiesLeftText.setFont(font);
        enemiesLeftText.setFill(Color.WHITE);
        enemiesLeftValue.setFont(font);
        enemiesLeftValue.setFill(Color.WHITE);


        HBox downHudContainer = new HBox(); // conteneur pour les éléments du HUD
        downHudContainer.setPadding(new Insets(10));
        downHudContainer.setMinHeight(100);
        downHudContainer.setAlignment(Pos.CENTER);
        downHudContainer.setPrefWidth(WIDTH);
        downHudContainer.setMaxWidth(WIDTH);
        downHudContainer.setMinWidth(WIDTH);


        HBox timer= new HBox();
        Text timerText= new Text("Next round in : ");
        timerValue = new Text("");
        timerText.setFont(font);
        timerText.setFill(Color.RED);
        timerValue.setFont(font);
        timerValue.setFill(Color.RED);

        timer.visibleProperty().bind(timerValue.textProperty().isNotEmpty());


        timer.getChildren().addAll(timerText, timerValue);
        downHudContainer.getChildren().add(timer);

        HUDLayer.setBottom(downHudContainer);




    }



}
