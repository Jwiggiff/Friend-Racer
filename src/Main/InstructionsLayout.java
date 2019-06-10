package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class InstructionsLayout extends BorderPane {
    private Main app;
    private String[] subtitles = {"Concept", "Levels", "Controls", "Objective"};
    private String[] texts = {"" +
            "The concept of Friend Racer is to overcome " +
                "loneliness that is caused by social media." +
                "Many teenagers can struggle with forms of " +
                "loneliness and depression from social media. " +
                "In Friend Racer, you will play as either a male " +
                "or female character trying to overcome loneliness " +
                "caused by social media. You will progress through three levels, " +
                "each getting slightly harder.",
            "The first level will consist of you, the player, " +
                    "running and jumping over various obstacles while having to make decisions " +
                    "of what to do in various social situations that will be presented to you. " +
                    "Your decision will affect your ability to complete the level. The second level " +
                    "is the same as the first level, but with the addition of coins throughout the level " +
                    "which represent tips on how to make decisions in social situations. These " +
                    "will help you make the correct decisions throughout the rest of the game. " +
                    "The third level is the same as the second, but with the addition of other people " +
                    "throughout the level that you can invite to your party. This will contribute to " +
                    "your character's number of friends.",
            "SPACE....................................................Jump\n" +
                    "E..............................................................Pick up a Coin\n" +
                    "I...............................................................Invite a Friend\n",
            "This game has three main objectives. These objectives are to make the correct decisions " +
                    "throughout the game, collect as many tips throughout the game, and invite as many " +
                    "friends as you can to your party. These are all separate scores that you will be able " +
                    "to see throughout the game and at the end."
    };
    private int curPage = 0;

    public InstructionsLayout() {
        Label title = new Label("How to Play");
        Font f = Font.font("! PEPSi !", 50);
        title.setFont(f);
        title.setTextFill(Color.ORANGE);

        Label subtitle = new Label(subtitles[curPage]);
        Font f2 = Font.font("! PEPSi !", 30);
        subtitle.setFont(f2);
        subtitle.setTextFill(Color.ORANGE);

        VBox header = new VBox(title, subtitle);
        header.setAlignment(Pos.TOP_CENTER);

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setSkin(new MainMenuButtonSkin(backBtn));
        backBtn.setOnAction(e -> app.setMainMenuScene());

        StackPane footer = new StackPane(backBtn);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        Text text = new Text(texts[curPage]);
        text.setWrappingWidth(450);
        text.setFont(Font.font("Baloo Bhai", 18));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);

        Button rightArrow = new Button("", new ImageView(new Image(getClass().getClassLoader().getResource("res/arrow.png").toString())));
        rightArrow.getGraphic().setScaleX(0.5);
        rightArrow.getGraphic().setScaleY(0.5);
        rightArrow.setBackground(Background.EMPTY);
        rightArrow.setAlignment(Pos.CENTER_LEFT);
        Button leftArrow = new Button("", new ImageView(new Image(getClass().getClassLoader().getResource("res/arrow.png").toString())));
        leftArrow.getGraphic().setScaleX(-0.5);
        leftArrow.getGraphic().setScaleY(0.5);
        leftArrow.setBackground(Background.EMPTY);
        leftArrow.setAlignment(Pos.CENTER_RIGHT);

        // Action Listeners //
        rightArrow.setOnAction(e -> {
            curPage++;
            if (curPage>=subtitles.length)
                curPage = 0;
            if (curPage==2)
                text.setTextAlignment(TextAlignment.LEFT);
            else
                text.setTextAlignment(TextAlignment.CENTER);
            subtitle.setText(subtitles[curPage]);
            text.setText(texts[curPage]);
        });
        leftArrow.setOnAction(e -> {
            curPage--;
            if (curPage<0)
                curPage = subtitles.length-1;
            if (curPage==2)
                text.setTextAlignment(TextAlignment.LEFT);
            else
                text.setTextAlignment(TextAlignment.CENTER);
            subtitle.setText(subtitles[curPage]);
            text.setText(texts[curPage]);
        });

        HBox content = new HBox(25, leftArrow, text, rightArrow);
        content.setAlignment(Pos.CENTER);
        content.setSpacing(0);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setTop(header);
        this.setBottom(footer);
        this.setCenter(content);
    }

    public void setApp(Main app) {
        this.app = app;
    }
}
