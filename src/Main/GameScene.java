package Main;

import javafx.scene.Group;
import javafx.scene.Scene;

public class GameScene extends Scene {
    GameCanvas gameCanvas;
    private static Group gameGroup = new Group();

    public GameScene(int windowWidth, int windowHeight) {
        super(GameScene.gameGroup);
        gameCanvas = new GameCanvas(windowWidth, windowHeight);
        gameGroup.getChildren().add(gameCanvas);

        new GameLoop(gameCanvas);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    gameCanvas.pause();
                    break;
                case SPACE:
                    GameLoop.jump = true;
                    gameCanvas.player.jump(gameCanvas);
                    break;
            }
        });
    }
}
