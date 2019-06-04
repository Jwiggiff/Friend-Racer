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

            for (int i = 0; i < 40; i++) {
                platforms.add(new Sprite(new Image("./platforms/ground.PNG"), 50));
            }

            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform_1.PNG"), 75));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform_1.PNG"), 75));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80));
            platforms.add(new Sprite(new Image("./platforms/platform.PNG"), 80, 7));

            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 13));
            obstacles.add(new Sprite(new Image("./spikes/spikes_2 - upside down.png"), 40));
            obstacles.add(new Sprite(new Image("./spikes/spikes_2 - upside down.png"), 40));
            for (int i = 0; i < 4; i++) {
                obstacles.add(new Sprite(new Image("./ropes_chains/long_chain_piece.png"), 13));
            }
            obstacles.add(new Sprite(new Image("./spikes/spikes_1.png"), 50));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 10));
            obstacles.add(new Sprite(new Image("./spikes/single_spike.png"), 10));

            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_1.png"), 24, 1));
            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_2.png"), 24, 1));
            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/blade_3.png"), 24, -1));
            spinningObstacles.add(new SpinningSprite(new Image("./rotating_blades/large_post.png"), 15, 1));
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
        for (int i = 0; i < 40; i++) {
            platforms.get(i).setPos(50*(i-4), 550);
        }

        platforms.get(40).setPos(295, 480);
        platforms.get(41).setPos(390, 420);
        platforms.get(42).setPos(485, 360);
        platforms.get(43).setPos(200, 523);
        platforms.get(44).setPos(870, 525);
        platforms.get(45).setPos(970, 470);
        platforms.get(46).setPos(1070, 523);
        platforms.get(47).setPos(1210, 470);
        platforms.get(48).setPos(1310, 450);
        platforms.get(49).setPos(1420, 410);
        platforms.get(50).setPos(1595, 410);

        obstacles.get(0).setPos(362, 460);
        obstacles.get(1).setPos(457, 400);
        obstacles.get(2).setPos(552, 340);
        obstacles.get(3).setPos(262, 505);
        obstacles.get(4).setPos(1595, 408 + platforms.get(50).getHeight());
        obstacles.get(5).setPos(1635, 408 + platforms.get(50).getHeight());
        obstacles.get(6).setPos(720, 0);
        obstacles.get(7).setPos(720, obstacles.get(6).getHeight());
        obstacles.get(8).setPos(720, obstacles.get(6).getHeight()*2);
        obstacles.get(9).setPos(720, obstacles.get(6).getHeight()*3);
        obstacles.get(10).setPos(1170, 520);
        obstacles.get(11).setPos(940, 510);
        obstacles.get(12).setPos(1280, 455);

        spinningObstacles.get(0).setPos(175, 526);
        spinningObstacles.get(1).setPos(1366, 426);
        spinningObstacles.get(2).setPos(846, 526);
        spinningObstacles.get(3).setPos(720 + obstacles.get(6).getWidth()/2 - spinningObstacles.get(3).getWidth()/2, obstacles.get(6).getHeight()*4 - spinningObstacles.get(3).getHeight()/2);
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
