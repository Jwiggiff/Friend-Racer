package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class InstructionsLayout extends VBox {
    private Main app;

    public InstructionsLayout() {
        super(10);

        Label title = new Label("How to Play");

        Font f = Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 50);
        title.setFont(f);
        title.setTextFill(Color.ORANGE);
        title.setAlignment(Pos.TOP_CENTER);

        Label subtitle = new Label("Concept");
        Font f2 = Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 30);
        subtitle.setFont(f2);
        subtitle.setTextFill(Color.ORANGE);
        subtitle.setAlignment(Pos.TOP_CENTER);

        Button backBtn = new Button("Back to Main Menu");
        backBtn.setSkin(new MainMenuButtonSkin(backBtn));
        backBtn.setOnAction(e -> app.setMainMenuScene());

        Text text = new Text("" +
                "These are example\n" +
                "steps!"
        );
        Polygon rightTriangle = new Polygon(
                600, 250,
                600, 350,
                750, 300
        );
        text.setFont(new Font(18));
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);

        this.getChildren().addAll(title, subtitle, text, rightTriangle, backBtn);
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(10));
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setApp(Main app) {
        this.app = app;
    }
}
