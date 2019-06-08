package Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameScene extends Scene {
    private static GameCanvas gameCanvas;
    private static Group gameGroup = new Group();
    public static GameLoop gameLoop;
    public static final int CANVAS_WIDTH = 3500;
    public static final int CANVAS_HEIGHT = 800;

    public GameScene(int windowWidth, int windowHeight) {
        super(gameGroup, windowWidth, windowHeight);
        characterSelect();
    }

    public void characterSelect() {
        VBox characterSelect = new VBox(50);
        HBox characters = new HBox(50);
        Text title = new Text("Choose a Character:");
        title.setFont(Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(),50));
        title.setFill(Color.ORANGE);
        title.setTextAlignment(TextAlignment.CENTER);

        ImageView guy = new ImageView(new Image(getClass().getClassLoader().getResource("res/male_character.png").toString()));
        guy.setScaleX(0.5);
        guy.setScaleY(0.5);
        Button guyBtn = new Button("", guy);
        guyBtn.setBackground(Background.EMPTY);
        guyBtn.setMaxSize(232,397);
        guyBtn.setMinSize(232,397);
        guyBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0))));
        guyBtn.setOnMouseEntered(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        guyBtn.setOnMouseExited(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));

        ImageView girl = new ImageView(new Image(getClass().getClassLoader().getResource("res/female_character.png").toString()));
        girl.setScaleX(0.5);
        girl.setScaleY(0.5);
        Button girlBtn = new Button("", girl);
        girlBtn.setBackground(Background.EMPTY);
        girlBtn.setMaxSize(232,397);
        girlBtn.setMinSize(232,397);
        girlBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0))));
        girlBtn.setOnMouseEntered(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));
        girlBtn.setOnMouseExited(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,5,0)))));

        characters.getChildren().addAll(guyBtn, girlBtn);
        characterSelect.getChildren().addAll(title,characters);
        characterSelect.setAlignment(Pos.TOP_CENTER);
        characters.setAlignment(Pos.CENTER);
        characterSelect.setPadding(new Insets(10));
        characterSelect.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        characterSelect.setPrefWidth(801);
        characters.setPrefHeight(500);
        gameGroup.getChildren().add(characterSelect);

        // Action Listeners //
        guyBtn.setOnAction(e -> {
            gameGroup.getChildren().remove(characterSelect);
            startGame();
        });
        girlBtn.setOnAction(e -> {
            gameGroup.getChildren().remove(characterSelect);
            startGame();
        });
    }

    public void startGame() {
        //TODO: windowWidth+1200 is the width of the canvas (currently 2000)
        gameCanvas = new GameCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gameGroup.getChildren().add(gameCanvas);

        gameLoop = new GameLoop(gameCanvas);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    if (!gameCanvas.pause) {
                        gameCanvas.pause = true;
                        gameCanvas.pause();
                    } else {
                        gameCanvas.returnToGame = true;
                        gameCanvas.returnToGame();
                    }
                    break;
                case SPACE:
                    if (!GameLoop.respawning && gameCanvas.player.playerPlatformStatus(gameCanvas.platforms)[0] == 0) {
                        GameLoop.jump = true;
                    }
                    break;
                case A: //TESTING
                    new Decision("this is a decision.", "OK", "Not OK", gameGroup, gameCanvas.player);
                    break;
                case T: //TESTING
                    new Tip("This is a tip", gameGroup);
                    break;
            }
        });
    }
}
