package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaderboardLayout extends VBox {
    Main app;
    ArrayList<LeaderboardEntry> entries = new ArrayList<LeaderboardEntry>();

    public LeaderboardLayout() {
        super(10);

        readLeaderboardFile();

        TableView tv = new TableView();
        tv.setMaxSize(500, 400);

        TableColumn<String, LeaderboardEntry> column1 = new TableColumn<String, LeaderboardEntry>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, LeaderboardEntry> column2 = new TableColumn<String, LeaderboardEntry>("Deaths");
        column2.setCellValueFactory(new PropertyValueFactory<>("numDeaths"));
        TableColumn<String, LeaderboardEntry> column3 = new TableColumn<String, LeaderboardEntry>("Tips");
        column3.setCellValueFactory(new PropertyValueFactory<>("numTips"));
        TableColumn<String, LeaderboardEntry> column4 = new TableColumn<String, LeaderboardEntry>("Invites");
        column4.setCellValueFactory(new PropertyValueFactory<>("numInvites"));

        column1.setPrefWidth(100);
        column2.setPrefWidth(100);
        column3.setPrefWidth(100);
        column4.setPrefWidth(100);

        tv.getColumns().addAll(column1, column2, column3, column4);
        tv.getItems().addAll(entries);

        Label title = new Label("Leaderboard");
        try {
            Font f = Font.loadFont(new FileInputStream(new File("res/pepsi_font.ttf")), 50);
            title.setFont(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        title.setTextFill(Color.ORANGE);
        title.setAlignment(Pos.TOP_CENTER);

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setSkin(new MainMenuButtonSkin(backBtn));
        backBtn.setOnAction(e -> app.setMainMenuScene());

        this.getChildren().addAll(title, tv, backBtn);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setApp(Main app) {
        this.app = app;
    }

    public void readLeaderboardFile() {
        try {
            Scanner s = new Scanner(new File("res/leaderboard"));
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" ");
                entries.add(new LeaderboardEntry(line[0],Integer.parseInt(line[1]),Integer.parseInt(line[2]),Integer.parseInt(line[3])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
