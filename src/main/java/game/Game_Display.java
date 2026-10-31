package game;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Game_Display {
    //Array for us to store the game history and check for winner
    private Button[][] cells = new Button[3][3];
    private boolean xTurn = true;
    private boolean gameOver = false;
    private boolean gameTie = false;


    public Scene gameScene() {
        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setHgap(8);
        board.setVgap(8);

        //Layout:
        Label Announcement = new Label("'X' Turns");
        Announcement.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-text-fill: #595959");


        //Play Again Button
        Button playAgain = new Button("Play Again");
        playAgain.setPrefSize(200,40);
        playAgain.setStyle("-fx-background-color: #595959;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-background-radius: 30px");

        //Exit to Main Menu Button
        Button mainMenu = new Button("Main Menu");
        mainMenu.setPrefSize(200,40);
        mainMenu.setStyle("-fx-background-color: #595959;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-background-radius: 30px");

        //Don't show until game is over
        playAgain.setVisible(false);
        playAgain.setManaged(false);
        mainMenu.setVisible(false);
        mainMenu.setManaged(false);

        //Play Again Button:
        playAgain.setOnAction(e -> {
            Game_Display freshGame = new Game_Display();
            Stage stage = (Stage) playAgain.getScene().getWindow();
            stage.setScene(freshGame.gameScene());
        });

        //Main Menu Button
        mainMenu.setOnAction(e -> {
            Stage stage = (Stage) mainMenu.getScene().getWindow();
            Welcome_Display mainDisplay = new Welcome_Display();
            mainDisplay.start(stage);
        });




        //Buttons for Clicking
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //Button Costumization
                Button cell = new Button();
                cells[row][col] = cell;
                cell.setPrefSize(100, 100);
                cell.setStyle(
                        "-fx-background-color: #595959;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-font-size: 30px;" +
                        "-fx-text-fill: white;");
                //Button Listeners
                cell.setOnAction(e -> {
                    if (cell.getText().isEmpty() && !gameOver) {
                        if (xTurn) {
                            cell.setText("X");
                        } else {
                            cell.setText("O");
                        }

                        if (checkWinner()) {
                            gameOver = true;

                            if (xTurn) {
                                Announcement.setText("'X' Won!");
                            } else {
                                Announcement.setText("'O' Won!");
                            }
                        } else if (isBoardFull()) {
                            gameOver = true;
                            Announcement.setText("It's a draw!");
                        } else {
                            xTurn = !xTurn;

                            if (xTurn) {
                                Announcement.setText("'X' Turns");
                            } else {
                                Announcement.setText("'O' Turns");
                            }
                        }

                        if (gameOver) {
                            playAgain.setVisible(true);
                            playAgain.setManaged(true);
                            mainMenu.setVisible(true);
                            mainMenu.setManaged(true);
                        }
                    }
                });
                // Add the border
                board.add(cell, col, row);
            }
        }

        VBox root = new VBox();
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(board, Announcement, playAgain, mainMenu);

        Scene scene = new Scene(root, 500, 600);
        return scene;
    }

    private boolean checkWinner () {
        for (int i = 0; i < 3; i++) {
            String rows = cells[i][0].getText();
            String cols = cells[0][i].getText();

            //Check for three in a row (horizontal)
            if (!rows.isEmpty() &&
            rows.equals(cells[i][1].getText()) &&
            rows.equals(cells[i][2].getText())) {
                cells[i][0].setStyle(cells[i][0].getStyle() + "-fx-background-color: #3F704D;");
                cells[i][1].setStyle(cells[i][1].getStyle() + "-fx-background-color: #3F704D;");
                cells[i][2].setStyle(cells[i][2].getStyle() + "-fx-background-color: #3F704D;");
                return true;
            }
            //Check for three in a row (vertical)
            if (!cols.isEmpty() &&
                    cols.equals(cells[1][i].getText()) &&
                    cols.equals(cells[2][i].getText())) {
                cells[0][i].setStyle(cells[0][i].getStyle() + "-fx-background-color: #3F704D;");
                cells[1][i].setStyle(cells[1][i].getStyle() + "-fx-background-color: #3F704D;");
                cells[2][i].setStyle(cells[2][i].getStyle() + "-fx-background-color: #3F704D;");
                return true;
            }
        }
        //Check for diagonal
        String diagonal1 = cells[0][0].getText();
        String diagonal2 = cells[0][2].getText();
        //Check for three in a row (diagonal)
        if (!diagonal1.isEmpty() &&
                diagonal1.equals(cells[1][1].getText()) &&
                diagonal1.equals(cells[2][2].getText())) {
            cells[0][0].setStyle(cells[0][0].getStyle() + "-fx-background-color: #3F704D;");
            cells[1][1].setStyle(cells[1][1].getStyle() + "-fx-background-color: #3F704D;");
            cells[2][2].setStyle(cells[2][2].getStyle() + "-fx-background-color: #3F704D;");
            return true;
        }
        if (!diagonal2.isEmpty() &&
                diagonal2.equals(cells[1][1].getText()) &&
                diagonal2.equals(cells[2][0].getText())) {
            cells[0][2].setStyle(cells[0][2].getStyle() + "-fx-background-color: #3F704D;");
            cells[1][1].setStyle(cells[1][1].getStyle() + "-fx-background-color: #3F704D;");
            cells[2][0].setStyle(cells[2][0].getStyle() + "-fx-background-color: #3F704D;");
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}


