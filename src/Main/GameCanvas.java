package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameCanvas extends Canvas {
    //TODO: make all instance variables private and make getters and setters
    public GraphicsContext gc;
    public boolean pause;
    public boolean returnToGame;
    public Character player;
    public ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    public ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    public ArrayList<SpinningSprite> spinningObstacles = new ArrayList<SpinningSprite>();

    public GameCanvas(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        loadResources();
        setSpritePositions();

        player.setPos(20, 600 - player.getHeight());
        player.render(gc);

        drawWorld();
    }

    private void loadResources() {
        try {
            player = new Character(new Image("./running_man.png"), 40);
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform_1.PNG"), 75));

            for (int i = 0; i < 40; i++) {
                platforms.add(new Sprite(new Image("./platforms/ground.PNG"), 50));
            }

            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));

            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));

            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_1.png"), 25, 1));
            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_2.png"), 25, 1));
            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_3.png"), 25, -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawWorld() {
        gc.clearRect(0,0,2000,600);
        gc.setFill(Color.RED);

        for (Sprite s : platforms) {
            s.render(gc);
        }
        for (Sprite s : obstacles) {
            s.render(gc);
        }
        for (Sprite s : spinningObstacles) {
            s.render(gc);
        }
    }

    private void setSpritePositions() {
        platforms.get(0).setPos(295, 480);
        platforms.get(1).setPos(390, 420);
        platforms.get(2).setPos(485, 360);
        platforms.get(3).setPos(200, 525);

        for (int i = 4; i < 44; i++) {
            platforms.get(i).setPos(50*(i-4), 550);
        }

        platforms.get(44).setPos(820, 515);
        platforms.get(45).setPos(910, 480);
        platforms.get(46).setPos(1000, 525);
        platforms.get(47).setPos(1140, 525);
        platforms.get(48).setPos(1230, 480);

        obstacles.get(0).setPos(362, 460);
        obstacles.get(1).setPos(457, 400);
        obstacles.get(2).setPos(552, 340);
        obstacles.get(3).setPos(262, 505);

        spinningObstacles.get(0).setPos(174, 525);
        spinningObstacles.get(1).setPos(875, 490);
        spinningObstacles.get(2).setPos(1285, 455);
    }

    public void rotateSpinningSprites() {
        for (SpinningSprite s : spinningObstacles) {
            if (s.getRotationDirection() == 1) {
                s.rotateImage(gc, 3);
            } else {
                s.rotateImage(gc, -3);
            }
        }
    }

    public void pause() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 2000, 600);
        gc.setFill(Color.GREEN);
        gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
        gc.setFill(Color.RED);
        //TODO: make a pause and countdown 3-2-1
    }

    public void returnToGame() {
        pause = false;
        returnToGame = false;
    }
}
