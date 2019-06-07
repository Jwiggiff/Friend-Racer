package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuLayout extends StackPane /*VBox*/ {
    private Main app;

    public MainMenuLayout() {
        VBox menuBtns = new VBox(10);
        Button startBtn = new Button("Start");
        Button instructionsBtn = new Button("How to Play");
        Button leaderboardBtn = new Button("Leaderboard");
        Button settingsBtn = new Button("Settings");
        Button exitBtn = new Button("Exit");
        startBtn.setSkin(new MainMenuButtonSkin(startBtn));
        instructionsBtn.setSkin(new MainMenuButtonSkin(instructionsBtn));
        ((Button) leaderboardBtn).setSkin(new MainMenuButtonSkin(leaderboardBtn));
        settingsBtn.setSkin(new MainMenuButtonSkin(settingsBtn));
        exitBtn.setSkin(new MainMenuButtonSkin(exitBtn));

        StackPane title = new StackPane();

        ImageView titleImg = new ImageView("res/game_logo.png");
        titleImg.setPreserveRatio(true);
        titleImg.setFitWidth(700);

        title.setPadding(new Insets(20,10,10,10));
        title.getChildren().add(titleImg);
        title.setAlignment(Pos.TOP_CENTER);

        // Event Listeners //
        startBtn.setOnAction(e -> {
            HBox characterSelect = new HBox();
            Button guyBtn = new Button("guy"/*, Guy character */);
            guyBtn.setFont(Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(),30));
            guyBtn.setTextFill(Color.BLUE);
            guyBtn.setOnMouseEntered(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
            guyBtn.setOnMouseExited(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
            Button girlBtn = new Button("girl"/*, Girl character */);
            girlBtn.setFont(Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(),30));
            girlBtn.setTextFill(Color.BLUE);
            girlBtn.setOnMouseEntered(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
            girlBtn.setOnMouseExited(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
            characterSelect.getChildren().addAll(guyBtn, girlBtn);
            Text header = new Text("Choose a Character:");
            header.setFont(Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(),30));
            header.setFill(Color.ORANGE);
            VBox popup = new VBox(10);
            popup.getChildren().addAll(title, characterSelect);
            this.getChildren().add(popup);
            guyBtn.setOnAction(event -> {
                this.getChildren().remove(popup);
                app.setGameScene();
            });
            girlBtn.setOnAction(event -> {
                this.getChildren().remove(popup);
                app.setGameScene();
            });
        });
        instructionsBtn.setOnAction(e -> app.setInstructionsScene());
        leaderboardBtn.setOnAction(e -> app.setLeaderboardScene());
        exitBtn.setOnAction(e -> app.stage.close());

        menuBtns.setAlignment(Pos.BOTTOM_LEFT);
        menuBtns.getChildren().addAll(startBtn,instructionsBtn,leaderboardBtn, settingsBtn,exitBtn);
        this.getChildren().addAll(title, menuBtns);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(0,0,50,0));

        for (Node n : this.getChildren()) {
            n.setOpacity(0);
        }
    }

    public void setApp(Main app) {
        this.app = app;
    }
}
