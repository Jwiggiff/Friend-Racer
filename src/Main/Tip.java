package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Tip {
    private String tip;
    private Group parent;

    public Tip(String tip, Group parent) {
        this.tip = tip;
        this.parent = parent;
        this.show();
    }

    public void show() {
        StackPane layout = new StackPane();
        layout.setPrefSize(500,300);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.setLayoutX(400-layout.getPrefWidth()/2);
        layout.setLayoutY(300-layout.getPrefHeight()/2);
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));
        layout.setBackground(new Background(new BackgroundFill(new Color(0,0,0,0.9), new CornerRadii(10), Insets.EMPTY)));

        Text title = new Text("Here's a Tip!");
        title.setFill(Color.WHITE);
        title.setFont(new Font(30));
        title.setTextAlignment(TextAlignment.CENTER);

        Text msg = new Text(tip);
        msg.setFill(Color.WHITE);
        msg.setFont(new Font(18));
        msg.setTextAlignment(TextAlignment.CENTER);
        StackPane s = new StackPane();
        s.getChildren().add(msg);
        s.setAlignment(Pos.CENTER);

        /*Text footerTxt = new Text("Press any key to continue...");
        footerTxt.setTextAlignment(TextAlignment.CENTER);
        footerTxt.setFill(Color.WHITE);
        footerTxt.setFont(new Font(18));
        */
        Button continueBtn = new Button("Continue");
        continueBtn.setTextAlignment(TextAlignment.CENTER);
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0))));
        continueBtn.setFont(new Font(18));
        continueBtn.setOnMouseEntered(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        continueBtn.setOnMouseExited(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        continueBtn.setOnAction(e -> {
            parent.getChildren().remove(layout);
            Timeline t = new Timeline();
            t.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(3), event -> GameScene.gameLoop.start())
            );
            t.playFromStart();
        });
        StackPane footer = new StackPane();
        footer.getChildren().add(continueBtn);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        layout.getChildren().addAll(title, s, footer);
        parent.getChildren().add(layout);

        GameScene.gameLoop.stop();
    }
}
