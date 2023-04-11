module test {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens Game to javafx.fxml;
    exports Game;
}