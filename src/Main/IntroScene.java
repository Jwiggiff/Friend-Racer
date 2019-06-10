package Main;

import javafx.scene.Scene;

/**
 * This class is the scene to run the intro at the beginning of the game.
 *
 * @author Josh Friedman
 * @version 1 - May 11 - Josh Friedman - 10 mins - created entire class
 */
public class IntroScene extends Scene {
    private static final IntroLayout introLayout = new IntroLayout();

    /**
     * This is the class constructor that creates a scene with the introLayout as root.
     *
     * @param windowWidth Width of the window
     * @param windowHeight Height of the window
     * @param app The Main class
     */
    public IntroScene(int windowWidth, int windowHeight, Main app) {
        super(introLayout, windowWidth, windowHeight);
        introLayout.setApp(app);
    }
}
