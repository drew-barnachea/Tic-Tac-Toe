module com.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe to javafx.fxml;
    exports com.example.tictactoe;

    exports game to javafx.graphics;
}