package Main;

import javafx.scene.Scene;

/**
 * This class is the Scene that is used at the end of the game
 *
 * @author Josh Friedman
 * @version 1 - June 8 - 10 mins - coded the entire class
 */
public class GameOverScene extends Scene {
    private static final GameOverLayout gameOverLayout = new GameOverLayout();

    /**
     * This is the constructor for the GameOverScene method, that sets up the Scene
     *
     * @param windowWidth The width of the window
     * @param windowHeight The height of the window
     * @param app The Main application
     */
    public GameOverScene(int windowWidth, int windowHeight, Main app) {
        super(gameOverLayout, windowWidth, windowHeight);
        gameOverLayout.setApp(app);
    }

    /**
     * This is the update method that updates the gameOverLayout
     */
    public void update() {
        gameOverLayout.update();
    }
}
