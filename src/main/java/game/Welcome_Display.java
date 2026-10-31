package game;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.application.Platform;


public class Welcome_Display extends Application {

    @Override
    public void start(Stage startScreen) {
        //Layout
        Label title = new Label("Tic-Tac-Toe");
        title.setStyle(
                        "-fx-text-fill: #595959;" +
                        "-fx-font-size: 70px;" +
                        "-fx-font-weight: bold;");

        Button startButton = new Button("START");
        Button exitButton = new Button("EXIT");

        for (Button btn: new Button[] { startButton, exitButton }) {
            btn.setPrefSize(200,60);
            btn.setStyle(
                        "-fx-background-color: #595959;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 20px;" +
                        "-fx-background-radius: 30px;" +
                        "-fx-font-weight: bold;");
        }

        //Buttons
        VBox btnRoot = new VBox();
        btnRoot.setAlignment(Pos.TOP_CENTER);
        btnRoot.setSpacing(50);
        btnRoot.getChildren().addAll(startButton, exitButton);

        //Layout
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.setSpacing(120);
        root.getChildren().addAll(title, btnRoot);
        root.setPadding(new Insets(100,0,0,0));

        Scene scene = new Scene(root, 500,600);
        startScreen.setResizable(false);
        startScreen.setScene(scene);
        startScreen.show();

        //Button behaviors
        exitButton.setOnAction(e -> {
            Platform.exit();
        });

        startButton.setOnAction(e -> {
            Game_Display game = new Game_Display();
            startScreen.setScene(game.gameScene());
        });

    }
    public static void main(String[] args) {
        launch(args);
    }
}
