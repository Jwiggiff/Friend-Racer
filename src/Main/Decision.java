package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * This class is used to create the Decision popups when you need to make a decision.
 *
 * @author Josh Friedman
 * @version 1 - June 4 - Josh Friedman - created the basic popup for decisions
 * @version 2 - June 6 - Josh Friedman - style the popup so it looks good and follows the rest of our game design
 * @version 3 - June 9 - Josh Friedman - added slow-mo effect for duration of popup
 */
public class Decision {
    private String message;
    private String[] options = new String[2];
    private Group pane;
    private Character player;
    private GameCanvas canvas1;
    private GameCanvas2 canvas2;
    private GameCanvas3 canvas3;

    /**
     * This is the class constructor that creates a Decision class
     * with the specified message and options, and adds it to the
     * specified Group and slows down the specified player.
     *
     * @param message The message in the Decision popup
     * @param option1 The first option in the Decision popup
     * @param option2 The second option in the Decision popup
     * @param pane The Group to add the popup to
     * @param player The player to slow down while the popup is being shown
     * @param canvas1 The canvas for level 1
     * @param canvas2 The canvas for level 2
     * @param canvas3 The canvas for level 3
     */
    public Decision(String message, String option1, String option2, Group pane, Character player, GameCanvas canvas1, GameCanvas2 canvas2, GameCanvas3 canvas3) {
        this.message = message;
        this.options[0] = option1;
        this.options[1] = option2;
        this.pane = pane;
        this.player = player;
        this.canvas1 = canvas1;
        this.canvas2 = canvas2;
        this.canvas3 = canvas3;
    }

    /**
     * This method shows the Decision popup
     */
    public void show() {
        if (GameScene.onLvl1) {
            for (Sprite s : canvas1.platforms)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas1.obstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas1.spinningObstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas1.decisions)
                s.setVel(-0.15, 0);
            canvas1.gameOverStar.setVel(-0.15, 0);
        } else if (GameScene.onLvl2) {
            for (Sprite s : canvas2.platforms)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas2.obstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas2.spinningObstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas2.decisions)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas2.tips)
                s.setVel(-0.15, 0);
            canvas2.gameOverStar.setVel(-0.15, 0);
        } else if (GameScene.onLvl3) {
            for (Sprite s : canvas3.platforms)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas3.obstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas3.spinningObstacles)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas3.decisions)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas3.tips)
                s.setVel(-0.15, 0);
            for (Sprite s : canvas3.invites)
                s.setVel(-0.15, 0);
            canvas3.gameOverStar.setVel(-0.15, 0);
        }

        VBox layout = new VBox(20);
        layout.setPrefSize(500, 300);
        layout.setMaxSize(500, 300);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.setLayoutX(350 - layout.getPrefWidth() / 2);
        layout.setLayoutY(300 - layout.getPrefHeight() / 2);
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));
        layout.setBackground(new Background(new BackgroundFill(new Color(0, 0, 0, 0.9), new CornerRadii(10), Insets.EMPTY)));

        Text title = new Text("Make a Decision");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("! PEPSi !", 30));
        title.setTextAlignment(TextAlignment.CENTER);

        Text msg = new Text(message);
        msg.setFill(Color.WHITE);
        msg.setFont(Font.font("Baloo Bhai", 18));
        msg.setTextAlignment(TextAlignment.CENTER);
        msg.setWrappingWidth(400);

        Text option1 = new Text("Jump to\n" + options[0]);
        Text option2 = new Text("Don't Jump to\n" + options[1]);
        option1.setTextAlignment(TextAlignment.CENTER);
        option1.setFill(Color.WHITE);
        option1.setFont(Font.font("Baloo Bhai", 18));
        option1.setWrappingWidth(200);
        option2.setTextAlignment(TextAlignment.CENTER);
        option2.setFill(Color.WHITE);
        option2.setFont(Font.font("Baloo Bhai", 18));
        option2.setWrappingWidth(300);
        HBox optionsBox = new HBox(100);
        optionsBox.getChildren().addAll(option1, option2);
        optionsBox.setAlignment(Pos.TOP_CENTER);
        ProgressBar pb = new ProgressBar(1);
        VBox footer = new VBox(10);
        footer.getChildren().addAll(optionsBox, pb);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        layout.getChildren().addAll(title, msg, footer);
        pane.getChildren().add(layout);

        Timeline countdown = new Timeline();
        for (int i = 0; i <= 100; i++) {
            int ii = i;
            countdown.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * 100), e -> pb.setProgress(1 - (ii / 100.0)))
            );
        }
        countdown.getKeyFrames().add(new KeyFrame(Duration.millis(10001), e -> {
            pane.getChildren().remove(layout);
            if (GameScene.onLvl1) {
                for (Sprite sp : canvas1.platforms)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas1.obstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas1.spinningObstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas1.decisions)
                    sp.setVel(-3, 0);
                canvas1.gameOverStar.setVel(-3, 0);
            } else if (GameScene.onLvl2) {
                for (Sprite sp : canvas2.platforms)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas2.obstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas2.spinningObstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas2.decisions)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas2.tips)
                    sp.setVel(-3, 0);
                canvas2.gameOverStar.setVel(-3, 0);
            } else if (GameScene.onLvl3) {
                for (Sprite sp : canvas3.platforms)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas3.obstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas3.spinningObstacles)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas3.decisions)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas3.tips)
                    sp.setVel(-3, 0);
                for (Sprite sp : canvas3.invites)
                    sp.setVel(-3, 0);
                canvas3.gameOverStar.setVel(-3, 0);
            }
        }));
        countdown.setOnFinished(e -> {
            if (GameScene.onLvl1)
                GameLoop.showingDecision = false;
            else if (GameScene.onLvl2)
                GameLoop2.showingDecision = false;
            else if (GameScene.onLvl3)
                GameLoop3.showingDecision = false;
        });
        countdown.playFromStart();
    }
}
