package Main;

import javafx.animation.*;
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
import javafx.util.Duration;

/**
 * This class is the Scene for the game portion of the program
 *
 * @author Josh Friedman, Om Patel
 * @version 1 - Josh Friedman - June 2 - 1 hour - set up the basic structure of the program
 * @version 2 - Josh Friedman - June 6 - 3 hours - set up the character selection and the introductions to each level
 * @version 3 - Om Patel - June 9 - 30 mins - modified the key listeners and created the start level methods
 *
 * Variable             Type            Description
 * __________________________________________________________
 * gameCanvas           GameCanvas      This is the canvas for level 1
 * gameCanvas2          GameCanvas2     This is the canvas for level 2
 * gameCanvas3          GameCanvas3     This is the canvas for level 3
 * gameGroup            Group           This is the Group layout for the stage
 * app                  Main            This is the Main application
 * gameLoop             GameLoop        This is the animation for level 1
 * gameLoop2            GameLoop2       This is the animation for level 2
 * gameLoop3            GameLoop3       This is the animation for level 3
 * onLvl1               boolean         Indicates whether the game is on level 1
 * onLvl2               boolean         Indicates whether the game is on level 2
 * onLvl3               boolean         Indicates whether the game is on level 3
 * numDeaths            int             This is the number of deaths for the player
 * numTips              int             This is the number of tips picked up by the player
 * numInvites           int             This is the number of invites given out by the player
 * playerChoice         String          This stores the player's choice for the player
 * CANVAS_WIDTH         int             This is the canvas width
 * CANVAS_HEIGHT        height          This is the canvas height
 * curLine              int             This is current line for the intro
 */
public class GameScene extends Scene {
    private static GameCanvas gameCanvas;
    private static GameCanvas2 gameCanvas2;
    private static GameCanvas3 gameCanvas3;
    public static Group gameGroup = new Group();
    private Main app;
    public static GameLoop gameLoop;
    public static GameLoop2 gameLoop2;
    public static GameLoop3 gameLoop3;
    public static boolean onLvl1 = false;
    public static boolean onLvl2 = false;
    public static boolean onLvl3 = false;
    public static int numDeaths = 0;
    public static int numTips = 0;
    public static int numInvites = 0;
    public static String playerChoice;
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;
    private static int curLine = 0;

    /**
     * This is the constructor for the GameScene class
     *
     * @param windowWidth The width of the scene
     * @param windowHeight The height of the scene
     * @param app The Main application
     */
    public GameScene(int windowWidth, int windowHeight, Main app) {
        super(gameGroup, windowWidth, windowHeight);
        this.app = app;
    }

    /**
     * This method is called to prompt the user to select a character,
     * of which there are two choices - a teenage boy or girl. The method then
     * goes directly into level 1 of the game
     */
    public void characterSelect() {
        VBox characterSelect = new VBox(50);
        HBox characters = new HBox(50);
        Text title = new Text("Choose a Character:");
        title.setFont(Font.font("! PEPSI !", 50));
        title.setFill(Color.ORANGE);
        title.setTextAlignment(TextAlignment.CENTER);

        ImageView guy = new ImageView(new Image(getClass().getClassLoader().getResource("res/characters/boy.png").toString()));
        guy.setScaleX(0.5);
        guy.setScaleY(0.5);
        Button guyBtn = new Button("", guy);
        guyBtn.setBackground(Background.EMPTY);
        guyBtn.setMaxSize(232, 397);
        guyBtn.setMinSize(232, 397);
        guyBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
        guyBtn.setOnMouseEntered(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        guyBtn.setOnMouseExited(event -> guyBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));

        ImageView girl = new ImageView(new Image(getClass().getClassLoader().getResource("res/characters/girl.png").toString()));
        girl.setScaleX(0.5);
        girl.setScaleY(0.5);
        Button girlBtn = new Button("", girl);
        girlBtn.setBackground(Background.EMPTY);
        girlBtn.setMaxSize(232, 397);
        girlBtn.setMinSize(232, 397);
        girlBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
        girlBtn.setOnMouseEntered(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        girlBtn.setOnMouseExited(event -> girlBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));

        characters.getChildren().addAll(guyBtn, girlBtn);
        characterSelect.getChildren().addAll(title, characters);
        characterSelect.setAlignment(Pos.TOP_CENTER);
        characters.setAlignment(Pos.CENTER);
        characterSelect.setPadding(new Insets(10));
        characterSelect.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        characterSelect.setPrefWidth(801);
        characters.setPrefHeight(500);
        gameGroup.getChildren().add(characterSelect);

        //Action Listeners
        guyBtn.setOnAction(e -> {
            playerChoice = "boy";
            gameGroup.getChildren().remove(characterSelect);
            introLvl1();
        });
        girlBtn.setOnAction(e -> {
            playerChoice = "girl";
            gameGroup.getChildren().remove(characterSelect);
            introLvl1();
        });
    }

    /**
     * This is the introduction for level 1. It has a couple lines of text to
     * depict a scene, after which the actual game starts.
     */
    public void introLvl1() {
        VBox level1Intro = new VBox(20);
        Text[] lines = {
                new Text("You're on the bus on the way home from school.  You're sitting all by yourself."),
                new Text("You lean over to the kid across the aisle and try to start a conversation.  He ignores you."),
                new Text("You pull out your phone to check social media.  You have no notifications.  No messages.  No likes."),
                new Text("In one last attempt to talk to someone, you lean over the chair in front of you and try to start a conversation with the girl in front of you.  She tells you to go away, and calls you a loser."),
                new Text("You sit back down on your chair and tears start streaming down your face.  You realize that you are lonely."),
                new Text("\nTip: Make the correct decisions to finish the level")
        };
        for (Text t : lines) {
            t.setOpacity(0);
            t.setFont(Font.font("Baloo Bhai", 18));
            t.setFill(Color.ORANGE);
            t.setWrappingWidth(600);
        }
        Button continueBtn = new Button("Continue");
        continueBtn.setFont(Font.font("! PEPSi !", 18));
        continueBtn.setBackground(Background.EMPTY);
        continueBtn.setTextFill(Color.BLUE);
        continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
        continueBtn.setOnMouseEntered(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        continueBtn.setOnMouseExited(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), lines[0]);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        continueBtn.setOnAction(e -> {
            curLine++;
            if (curLine == lines.length) {
                gameGroup.getChildren().remove(level1Intro);
                onLvl1 = true;
                startLvl1();
            } else {
                fadeIn.setNode(lines[curLine]);
                fadeIn.playFromStart();
            }
        });

        level1Intro.getChildren().addAll(lines);
        level1Intro.getChildren().add(continueBtn);
        level1Intro.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        level1Intro.setPrefSize(801, 601);
        level1Intro.setAlignment(Pos.CENTER);
        gameGroup.getChildren().add(level1Intro);

        fadeIn.playFromStart();
    }

    /**
     * This method actually starts the game for level 1, using
     * GameLoop and GameCanvas
     */
    public void startLvl1() {
        gameCanvas = new GameCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gameGroup.getChildren().add(gameCanvas);

        gameLoop = new GameLoop(gameCanvas, this);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (!GameLoop.respawning && gameCanvas.player.playerPlatformStatus(gameCanvas.platforms)[0] == 0) {
                        GameLoop.jump = true;
                    }
                    break;
            }
        });
    }

    /**
     * This is the introduction for level 2. It has a couple lines of text to
     * depict a scene, after which the actual game starts.
     */
    public void introLvl2() {
        curLine = 0;
        VBox level2Intro = new VBox(20);
        Text[] lines = {
                new Text("You've decided that you want to see someone to help you overcome your loneliness."),
                new Text("You go to see a therapist. She gives you various tips on how to make decisions in social situations."),
                new Text("She tells you to practice these tips when making decisions in your life, and it should help you overcome your loneliness."),
                new Text("You tell yourself that you are going to try and remember as many of these tips as possible when in social situations."),
                new Text("\nTip: Collect as many tips as you can to help you make decisions by pressing E when you are near the coins")
        };
        for (Text t : lines) {
            t.setOpacity(0);
            t.setFont(Font.font("Baloo Bhai", 18));
            t.setFill(Color.ORANGE);
            t.setWrappingWidth(600);
        }
        Button continueBtn = new Button("Continue");
        continueBtn.setFont(Font.font("! PEPSi !", 18));
        continueBtn.setBackground(Background.EMPTY);
        continueBtn.setTextFill(Color.BLUE);
        continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
        continueBtn.setOnMouseEntered(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        continueBtn.setOnMouseExited(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), lines[0]);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        continueBtn.setOnAction(e -> {
            curLine++;
            if (curLine == lines.length) {
                gameGroup.getChildren().remove(level2Intro);
                onLvl2 = true;
                startLvl2();
            } else {
                fadeIn.setNode(lines[curLine]);
                fadeIn.playFromStart();
            }
        });

        level2Intro.getChildren().addAll(lines);
        level2Intro.getChildren().add(continueBtn);
        level2Intro.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        level2Intro.setPrefSize(801, 601);
        level2Intro.setAlignment(Pos.CENTER);
        gameGroup.getChildren().add(level2Intro);

        fadeIn.playFromStart();
    }

    /**
     * This method actually starts the game for level 2, using
     * GameLoop2 and GameCanvas2
     */
    public void startLvl2() {
        gameCanvas2 = new GameCanvas2(CANVAS_WIDTH, CANVAS_HEIGHT);
        gameGroup.getChildren().add(gameCanvas2);

        gameLoop2 = new GameLoop2(gameCanvas2, this);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (!GameLoop2.respawning && gameCanvas2.player.playerPlatformStatus(gameCanvas2.platforms)[0] == 0) {
                        GameLoop2.jump = true;
                    }
                    break;
                case E:
                    for (Sprite tip : gameCanvas2.tips) {
                        if (gameCanvas2.player.intersects(tip)) {
                            gameCanvas2.tips.remove(tip);
                            GameScene.numTips++;
                            gameLoop2.tips[gameLoop2.curTip].show();
                            gameLoop2.curTip++;
                            break;
                        }
                    }
            }
        });
    }

    /**
     * This is the introduction for level 3. It has a couple lines of text to
     * depict a scene, after which the actual game starts.
     */
    public void introLvl3() {
        curLine = 0;
        VBox level3Intro = new VBox(20);
        Text[] lines = {
                new Text("You go back to see your therapist. You tell her that you have been using her tips and that they have helped a lot."),
                new Text("She says she's very happy for you. She thinks that you should throw a party and invite all of your new friends to celebrate."),
                new Text("You've always wanted to throw a party, and now is the perfect chance. You run home, design invitations, and print out hundreds of copies to give to your friends."),
                new Text("You get to school and you look around to find people to invite."),
                new Text("\nTip: Invite as many friends as you can!")
        };
        for (Text t : lines) {
            t.setOpacity(0);
            t.setFont(Font.font("Baloo Bhai", 18));
            t.setFill(Color.ORANGE);
            t.setWrappingWidth(600);
        }
        Button continueBtn = new Button("Continue");
        continueBtn.setFont(Font.font("! PEPSi !", 18));
        continueBtn.setBackground(Background.EMPTY);
        continueBtn.setTextFill(Color.BLUE);
        continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
        continueBtn.setOnMouseEntered(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        continueBtn.setOnMouseExited(e -> continueBtn.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0)))));
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), lines[0]);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        continueBtn.setOnAction(e -> {
            curLine++;
            if (curLine == lines.length) {
                gameGroup.getChildren().remove(level3Intro);
                onLvl3 = true;
                startLvl3();
            } else {
                fadeIn.setNode(lines[curLine]);
                fadeIn.playFromStart();
            }
        });

        level3Intro.getChildren().addAll(lines);
        level3Intro.getChildren().add(continueBtn);
        level3Intro.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        level3Intro.setPrefSize(801, 601);
        level3Intro.setAlignment(Pos.CENTER);
        gameGroup.getChildren().add(level3Intro);

        fadeIn.playFromStart();
    }

    /**
     * This method actually starts the game for level 3, using
     * GameLoop3 and GameCanvas3
     */
    public void startLvl3() {
        gameCanvas3 = new GameCanvas3(CANVAS_WIDTH, CANVAS_HEIGHT);
        gameGroup.getChildren().add(gameCanvas3);

        gameLoop3 = new GameLoop3(gameCanvas3, this, app);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    if (!GameLoop3.respawning && gameCanvas3.player.playerPlatformStatus(gameCanvas3.platforms)[0] == 0) {
                        GameLoop3.jump = true;
                    }
                    break;
                case E:
                    for (Sprite tip : gameCanvas3.tips) {
                        if (gameCanvas3.player.intersects(tip)) {
                            gameCanvas3.tips.remove(tip);
                            GameScene.numTips++;
                            gameLoop3.tips[gameLoop3.curTip].show();
                            gameLoop3.curTip++;
                            break;
                        }
                    }
                case I:
                    for (Sprite invite : gameCanvas3.invites) {
                        if (gameCanvas3.player.intersects(invite)) {
                            gameCanvas3.invites.remove(invite);
                            numInvites++;
                            break;
                        }
                    }
            }
        });
    }
}
