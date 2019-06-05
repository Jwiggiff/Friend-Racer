package Main;

import javafx.application.Application;
import javafx.stage.Stage;

//TODO: change the little logo on the top left of the run screen to our company logo
//TODO: convert all image backgrounds to transparent (PNG) instead of white
//TODO: make a legit ground that's a sprite
public class Main extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    public Stage stage;
    private MainMenuScene mainMenuScene;
    private GameScene gameScene = new GameScene(WINDOW_WIDTH, WINDOW_HEIGHT);
    private InstructionsScene instructionsScene = new InstructionsScene(WINDOW_WIDTH,WINDOW_HEIGHT, this);;
    private LeaderboardScene leaderboardScene = new LeaderboardScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);;
    private IntroScene introScene;
    private static final boolean DEBUGGING = true;

    public void start(Stage stage) {
        mainMenuScene = new MainMenuScene(WINDOW_WIDTH, WINDOW_HEIGHT, this);

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

    public void setGameScene() {
        stage.setScene(gameScene);
    }

    public void setMainMenuScene() {
        stage.setScene(mainMenuScene);
        mainMenuScene.fadeIn();
    }

    public void setLeaderboardScene() {
        stage.setScene(leaderboardScene);
    }

    public void setInstructionsScene() {
        stage.setScene(instructionsScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
