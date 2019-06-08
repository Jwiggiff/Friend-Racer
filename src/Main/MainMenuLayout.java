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
import javafx.scene.text.TextAlignment;

public class MainMenuLayout extends StackPane {
    private Main app;

    public MainMenuLayout() {
        VBox menuBtns = new VBox(10);
        Button startBtn = new Button("Start");
        Button instructionsBtn = new Button("How to Play");
        Button leaderboardBtn = new Button("Leaderboard");
        Button exitBtn = new Button("Exit");
        startBtn.setSkin(new MainMenuButtonSkin(startBtn));
        instructionsBtn.setSkin(new MainMenuButtonSkin(instructionsBtn));
        leaderboardBtn.setSkin(new MainMenuButtonSkin(leaderboardBtn));
        exitBtn.setSkin(new MainMenuButtonSkin(exitBtn));

        StackPane title = new StackPane();

        ImageView titleImg = new ImageView("res/game_logo.png");
        titleImg.setPreserveRatio(true);
        titleImg.setFitWidth(700);

        title.setPadding(new Insets(20,10,10,10));
        title.getChildren().add(titleImg);
        title.setAlignment(Pos.TOP_CENTER);

        // Event Listeners //
        startBtn.setOnAction(e -> app.setGameScene());
        instructionsBtn.setOnAction(e -> app.setInstructionsScene());
        leaderboardBtn.setOnAction(e -> app.setLeaderboardScene());
        exitBtn.setOnAction(e -> app.stage.close());

        menuBtns.setAlignment(Pos.CENTER_LEFT);
        menuBtns.setTranslateY(50);
        menuBtns.getChildren().addAll(startBtn,instructionsBtn,leaderboardBtn,exitBtn);
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
