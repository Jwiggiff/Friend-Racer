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

public class Decision {
    private String message;
    private String[] options = new String[2];
    private Group pane;

    public Decision(String message, String option1, String option2, Group pane) {
        this.message = message;
        this.options[0] = option1;
        this.options[1] = option2;
        this.pane = pane;
        show();
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

        Text title = new Text("Make a Decision!");
        title.setFill(Color.WHITE);
        title.setFont(new Font(30));
        title.setTextAlignment(TextAlignment.CENTER);

        Text msg = new Text(message);
        msg.setFill(Color.WHITE);
        msg.setFont(new Font(18));
        msg.setTextAlignment(TextAlignment.CENTER);
        StackPane s = new StackPane();
        s.getChildren().add(msg);
        s.setAlignment(Pos.CENTER);

        Text option1 = new Text("Jump to\n"+options[0]);
        Text option2 = new Text("Don't Jump to\n"+options[1]);
        option1.setTextAlignment(TextAlignment.CENTER);
        option1.setFill(Color.WHITE);
        option1.setFont(new Font(18));
        option2.setTextAlignment(TextAlignment.CENTER);
        option2.setFill(Color.WHITE);
        option2.setFont(new Font(18));
        HBox optionsBox = new HBox(100);
        optionsBox.getChildren().addAll(option1, option2);
        optionsBox.setAlignment(Pos.TOP_CENTER);
        ProgressBar pb = new ProgressBar(1);
        VBox footer = new VBox(10);
        footer.getChildren().addAll(optionsBox, pb);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        layout.getChildren().addAll(title, s, footer);
        pane.getChildren().add(layout);

        Timeline countdown = new Timeline();
        for (int i = 0; i <= 150; i++) {
            int ii = i;
            countdown.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i*100), e -> pb.setProgress(1-(ii/150.0)))
            );
        }
        countdown.getKeyFrames().add(new KeyFrame(Duration.millis(15001), e -> pane.getChildren().remove(layout)));
        countdown.playFromStart();
    }
}
