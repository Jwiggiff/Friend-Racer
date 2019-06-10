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
    }

    public void show() {
        VBox layout = new VBox(20);
        layout.setPrefSize(500,300);
        layout.setMaxSize(500,300);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.setLayoutX(400-layout.getPrefWidth()/2);
        layout.setLayoutY(300-layout.getPrefHeight()/2);
        layout.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(5))));
        layout.setBackground(new Background(new BackgroundFill(new Color(0,0,0,0.9), new CornerRadii(10), Insets.EMPTY)));

        Text title = new Text("Here's a Tip");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("! PEPSi !", 30));
        title.setTextAlignment(TextAlignment.CENTER);

        Text msg = new Text(tip);
        msg.setFill(Color.WHITE);
        msg.setFont(Font.font("Baloo Bhai", 18));
        msg.setTextAlignment(TextAlignment.CENTER);
        msg.setWrappingWidth(400);

        Button continueBtn = new Button("Continue");
        continueBtn.setTextAlignment(TextAlignment.CENTER);
        continueBtn.setTextFill(Color.WHITE);
        continueBtn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
        continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0))));
        continueBtn.setFont(Font.font("! PEPSi !", 18));
        continueBtn.setOnMouseEntered(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        continueBtn.setOnMouseExited(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        continueBtn.setOnAction(e -> {
            parent.getChildren().remove(layout);
            Timeline t = new Timeline();
            t.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(3), event -> {
                        if (GameScene.onLvl1)
                            GameScene.gameLoop.start();
                        else if (GameScene.onLvl2)
                            GameScene.gameLoop2.start();
                        else if (GameScene.onLvl3)
                            GameScene.gameLoop3.start();
                    })
            );
            t.playFromStart();
        });
        StackPane footer = new StackPane();
        footer.getChildren().add(continueBtn);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        layout.getChildren().addAll(title, msg, footer);
        parent.getChildren().add(layout);

        if (GameScene.onLvl1)
            GameScene.gameLoop.stop();
        else if (GameScene.onLvl2)
            GameScene.gameLoop2.stop();
        else if (GameScene.onLvl3)
            GameScene.gameLoop3.stop();
    }
}
