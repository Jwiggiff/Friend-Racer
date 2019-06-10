package Main;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the Main driver class
 *
 * @author Om Patel, Josh Friedman
 * @version 1 - Josh Friedman - June 1 - 1 hour - set up the structure for the class
 * @version 2 - Om Patel - June 4 - 10 mins - added the debugging option to make everything more convenient
 *
 * Variable         Type            Description
 * ________________________________________________________
 * WINDOW_WIDTH     int             The window width
 * WINDOW_HEIGHT    int             The window height
 * stage            Stage           The stage for the program
 * mainMenuScene    MainMenuScene   The scene for the mainmenu
 * gameScene        GameScene       The scene for the game
 * gameOver         GameOverScene   The scene for game over
 * introScene       IntroScene      The scene for the intro
 * DEBUGGING        boolean         Used for debugging
 */
public class Main extends Application {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public Stage stage;
    private MainMenuScene mainMenuScene;
    private GameScene gameScene;
    private InstructionsScene instructionsScene;
    private LeaderboardScene leaderboardScene;
    private GameOverScene gameOverScene;
    private IntroScene introScene;
    private static final boolean DEBUGGING = false;

    /**
     * Used to start the application
     *
     * @param stage The stage to start the application on
     */
    public void start(Stage stage) {
        loadFonts();

        mainMenuScene = new MainMenuScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        instructionsScene = new InstructionsScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        leaderboardScene = new LeaderboardScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        gameOverScene = new GameOverScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
        gameScene = new GameScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);

        this.stage = stage;

        stage.setTitle("Friend Racer");

        if (!DEBUGGING) {
            introScene = new IntroScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);
            stage.setScene(introScene);
        } else {
            setMainMenuScene();
        }

        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Sets the game scene
     */
    public void setGameScene() {
        stage.setScene(gameScene);
        gameScene.characterSelect();
    }

    /**
     * Sets the main menu scene
     */
    public void setMainMenuScene() {
        stage.setScene(mainMenuScene);
        mainMenuScene.fadeIn();
    }

    /**
     * Sets the leaderboard scene
     */
    public void setLeaderboardScene() {
        stage.setScene(leaderboardScene);
        leaderboardScene.readLeaderboard();
    }

    /**
     * Sets the instructions scene
     */
    public void setInstructionsScene() {
        stage.setScene(instructionsScene);
    }

    /**
     * sets the game over scene
     */
    public void setGameOverScene() {
        stage.setScene(gameOverScene);
        gameOverScene.update();
    }

    /**
     * loads the fonts
     */
    public void loadFonts() {
        Font.loadFont(getClass().getClassLoader().getResource("res/pepsi_font.ttf").toString(), 30);
        Font.loadFont(getClass().getClassLoader().getResource("res/BalooBhai.ttf").toString(), 18);
    }

    /**
     * main method, starts the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
