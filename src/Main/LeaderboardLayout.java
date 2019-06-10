package Main;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is the layout for the LeaderboardScene
 *
 * @author Josh Friedman
 * @version 1 - June 2 - Josh Friedman - 1 hour - Created general layout and style it
 * @version 2 - June 8 - Josh Friedman - 1.5 hours - Created styles for the table
 */
public class LeaderboardLayout extends VBox {
    Main app;
    ArrayList<LeaderboardEntry> entries = new ArrayList<LeaderboardEntry>();
    TableView tv;
    private File leaderboardFile = new File(System.getProperty("user.home") + "\\Desktop\\Friend Racer Leaderboard\\leaderboard.txt");

    /**
     * This is the class constructor that creates all the Nodes
     * and adds them to the layout.
     */
    public LeaderboardLayout() {
        super(10);

        readLeaderboardFile();

        tv = new TableView();
        tv.setMaxSize(500, 400);
        Text placeholderText = new Text("Be the first to save a highscore");
        placeholderText.setFont(Font.font("! PEPSI !",18));
        placeholderText.setFill(Color.ORANGE);
        tv.setPlaceholder(placeholderText);

        TableColumn<String, LeaderboardEntry> column1 = new TableColumn<String, LeaderboardEntry>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, LeaderboardEntry> column2 = new TableColumn<String, LeaderboardEntry>("Deaths");
        column2.setCellValueFactory(new PropertyValueFactory<>("numDeaths"));
        TableColumn<String, LeaderboardEntry> column3 = new TableColumn<String, LeaderboardEntry>("Tips");
        column3.setCellValueFactory(new PropertyValueFactory<>("numTips"));
        TableColumn<String, LeaderboardEntry> column4 = new TableColumn<String, LeaderboardEntry>("Invites");
        column4.setCellValueFactory(new PropertyValueFactory<>("numInvites"));

        column1.setPrefWidth(tv.getMaxWidth()/3);
        column2.setPrefWidth(2*tv.getMaxWidth()/9-1);
        column3.setPrefWidth(2*tv.getMaxWidth()/9-1);
        column4.setPrefWidth(2*tv.getMaxWidth()/9-1);

        column1.setSortable(false);
        column1.setResizable(false);
        column2.setResizable(false);
        column3.setResizable(false);
        column4.setResizable(false);

        column2.setSortType(TableColumn.SortType.DESCENDING);

        tv.getStylesheets().add(getClass().getResource("tableview.css").toString());

        tv.getColumns().addAll(column1, column2, column3, column4);
        tv.getItems().addAll(entries);

        Label title = new Label("Leaderboard");

        Font f = Font.font("! PEPSi !", 50);
        title.setFont(f);
        title.setTextFill(Color.ORANGE);
        title.setAlignment(Pos.TOP_CENTER);

        Button clearBtn = new Button("Clear Leaderboard");
        clearBtn.setFont(Font.font("! PEPSi !", 18));
        clearBtn.setBackground(Background.EMPTY);
        clearBtn.setTextFill(Color.BLUE);
        clearBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(0,0,5,0))));
        clearBtn.setOnMouseEntered(e -> clearBtn.setBorder(new Border(new BorderStroke(Color.BLUE,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(0,0,5,0)))));
        clearBtn.setOnMouseExited(e -> clearBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(0,0,5,0)))));
        clearBtn.setOnAction(e -> clearLeaderboard());

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setSkin(new MainMenuButtonSkin(backBtn));
        backBtn.setOnAction(e -> app.setMainMenuScene());

        this.getChildren().addAll(title, tv, clearBtn, backBtn);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * This method sets the app isntance variable.
     *
     * @param app The app to set it to
     */
    public void setApp(Main app) {
        this.app = app;
    }

    /**
     * This method reads the leaderboard file and adds the information to the table.
     */
    public void readLeaderboardFile() {
        if (entries.size()>0)
            entries.clear();
        if (tv != null)
            tv.getItems().clear();
        if (!leaderboardFile.exists()) {
            return;
        }
        try {
            Scanner s = new Scanner(leaderboardFile);
            while (s.hasNext()) {
                String l = s.nextLine();
                String[] line = l.split(":");
                entries.add(new LeaderboardEntry(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (tv != null)
            tv.getItems().addAll(entries);
    }

    /**
     * This method clears the leaderboard file.
     */
    public void clearLeaderboard() {
        if (!leaderboardFile.exists())
            return;
        try {
            FileWriter out = new FileWriter(leaderboardFile);
            out.write("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readLeaderboardFile();
        tv.getItems().addAll(entries);
        tv.refresh();
    }
}
