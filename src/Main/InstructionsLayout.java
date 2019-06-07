package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class InstructionsLayout extends StackPane {
    private Main app;

    public InstructionsLayout() {
        Label title = new Label("How to Play");
        Font f = Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 50);
        title.setFont(f);
        title.setTextFill(Color.ORANGE);

        Label subtitle = new Label("Concept");
        Font f2 = Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 30);
        subtitle.setFont(f2);
        subtitle.setTextFill(Color.ORANGE);

        VBox header = new VBox(title, subtitle);
        header.setAlignment(Pos.TOP_CENTER);

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setSkin(new MainMenuButtonSkin(backBtn));
        backBtn.setOnAction(e -> app.setMainMenuScene());

        StackPane footer = new StackPane(backBtn);
        footer.setAlignment(Pos.BOTTOM_CENTER);

        Text text = new Text("" +
                "These are example\n" +
                "steps"
        );
        Button rightArrow = new Button("", new ImageView(new Image(getClass().getClassLoader().getResource("res/arrow.png").toString())));
        text.setFont(Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 18));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);

        HBox content = new HBox(100, text, rightArrow);
        content.setAlignment(Pos.CENTER);

        this.getChildren().addAll(header, content, footer);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setApp(Main app) {
        this.app = app;
    }
}
