package Main;

import javafx.scene.Scene;

public class InstructionsScene extends Scene {
    private static final InstructionsLayout instructionsLayout = new InstructionsLayout();

    public InstructionsScene(int windowWidth, int windowHeight, Main app) {
        super(instructionsLayout, windowWidth, windowHeight);
        instructionsLayout.setApp(app);
    }
}
