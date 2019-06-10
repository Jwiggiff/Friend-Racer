package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is the layout class for the game over scene
 *
 * @author Josh Friedman
 * @version 1 - June 8 - Josh Friedman - 1 hour - created the basic layout for the GameOverScene
 * @version 2 - June 9 - Josh Friedman - 30 mins - styled the layout so that it looks good
 */
public class GameOverLayout extends StackPane {
    private Main app;
    private File leaderboardDir = new File(System.getProperty("user.home") + "\\Desktop\\Friend Racer Leaderboard");
    private File leaderboardFile = new File(System.getProperty("user.home") + "\\Desktop\\Friend Racer Leaderboard\\leaderboard.txt");
    Text scores;

    /**
     * This is the class constructor that creates all of the elements of the GameOverLayout
     * and adds it to the StackPane.
     */
    public GameOverLayout() {
        Text title = new Text("You Overcame Loneliness");
        title.setFill(Color.ORANGE);
        title.setFont(Font.font("! PEPSI !", 35));
        title.setTextAlignment(TextAlignment.CENTER);
        scores = new Text("You had "+GameScene.numDeaths+" deaths, picked up "+GameScene.numTips+" tips, and invited "+GameScene.numInvites+" people to your party.");
        scores.setFill(Color.ORANGE);
        scores.setFont(Font.font("Baloo Bhai", 30));
        scores.setTextAlignment(TextAlignment.CENTER);
        scores.setWrappingWidth(500);
        Text nameTxt = new Text("Please enter your name to save your score in the leaderboard:");
        nameTxt.setFill(Color.BLUE);
        nameTxt.setFont(Font.font("Baloo Bhai", 30));
        nameTxt.setTextAlignment(TextAlignment.CENTER);
        nameTxt.setWrappingWidth(500);
        TextField nameField = new TextField();
        nameField.setFont(Font.font("Baloo Bhai", 24));
        nameField.setBackground(new Background(new BackgroundFill(Color.rgb(16,16,16), new CornerRadii(10), Insets.EMPTY)));
        nameField.setAlignment(Pos.CENTER);
        nameField.setMaxWidth(500);
        nameField.setOnAction(e -> {
            writeLeaderboard(nameField.getText());
            nameField.setText("");
            app.setMainMenuScene();
        });
        VBox nameContent = new VBox(10);
        nameContent.getChildren().addAll(scores, nameTxt, nameField);
        nameContent.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(30));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().addAll(title, nameContent);
    }

    /**
     * This method updates the sentence that tells the player the scores.
     */
    public void update() {
        scores.setText("You had "+GameScene.numDeaths+" deaths, picked up "+GameScene.numTips+" tips, and invited "+GameScene.numInvites+" people to your party.");
    }

    /**
     * This method sets the app variable
     * @param app The app to set it to
     */
    public void setApp(Main app) {
        this.app = app;
    }

    /**
     * This method writes the name and scores to the leaderboard file.
     * @param name The name entered by the player
     */
    private void writeLeaderboard(String name) {
        try {
            if (!leaderboardDir.exists())
                leaderboardDir.mkdirs();
            if (!leaderboardFile.exists())
                leaderboardFile.createNewFile();
            FileWriter out = new FileWriter(leaderboardFile, true);
            out.write(name+":"+GameScene.numInvites+":"+GameScene.numTips+":"+GameScene.numDeaths+"\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
