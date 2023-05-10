module test {
    requires javafx.controls;
    requires javafx.fxml;


    opens Game to javafx.fxml;
    exports Game;
    exports Game.GameGestion;
    opens Game.GameGestion to javafx.fxml;
}