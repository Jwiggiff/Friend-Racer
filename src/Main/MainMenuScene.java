package Main;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * This is the scene for the mainmenu
 *
 * @author Josh Friedman
 * @version 1 - Josh Friedman - June 8 - 1 hour - coded the entire class
 *
 * Variable             Type                Description
 * ____________________________________________________________________
 * mainMenuLayout       MainMenuLayout      This is the layout for the mainmenu scene
 */
public class MainMenuScene extends Scene {
    private static final MainMenuLayout mainMenuLayout = new MainMenuLayout();

    /**
     * This is the constructor for the MainMenuScene class
     *
     * @param windowWidth the window's width
     * @param windowHeight the window's height
     * @param app the Main application
     */
    public MainMenuScene(int windowWidth, int windowHeight, Main app) {
        super(mainMenuLayout, windowWidth, windowHeight);
        mainMenuLayout.setApp(app);
    }

    /**
     * this method fades in the main menu scene
     */
    public void fadeIn() {
        for (Node n : mainMenuLayout.getChildren()) {
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), n);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.playFromStart();
        }
    }
}
