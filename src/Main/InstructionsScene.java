package Main;

import javafx.scene.Scene;

/**
 * This class is the Instructions scene
 *
 * @author Josh Friedman
 * @version 1 - Josh Friedman - June 7 - 10 mins - coded the entire class
 *
 * Variable             Type                Description
 * ___________________________________________________________________
 * instructionsLayout   InstructionsLayout  This is the InstructionsLayout object for the instructions screen
 */
public class InstructionsScene extends Scene {
    private static final InstructionsLayout instructionsLayout = new InstructionsLayout();

    /**
     * This is the constructors for the InstructionsScene class
     * @param windowWidth The height of the window
     * @param windowHeight The width of the window
     * @param app The Main application
     */
    public InstructionsScene(int windowWidth, int windowHeight, Main app) {
        super(instructionsLayout, windowWidth, windowHeight);
        instructionsLayout.setApp(app);
    }
}
