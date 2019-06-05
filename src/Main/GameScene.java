package Main;

import javafx.scene.Group;
import javafx.scene.Scene;

public class GameScene extends Scene {
    private static GameCanvas gameCanvas;
    private static Group gameGroup = new Group();
    static GameLoop gameLoop;

    public GameScene(int windowWidth, int windowHeight) {
        super(gameGroup, windowWidth, windowHeight);
        //TODO: windowWidth+1200 is the width of the canvas (currently 2000)
        gameCanvas = new GameCanvas(windowWidth + 1200, windowHeight);
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
                    new Decision("this is a decision.", "OK", "Not OK", gameGroup);
                    break;
                case T: //TESTING
                    new Tip("This is a tip!", gameGroup);
                    break;
            }
        });
    }
}
