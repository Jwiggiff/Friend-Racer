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
    public Sprite background;
    public static int diffHeight = GameScene.CANVAS_HEIGHT - Main.WINDOW_HEIGHT;

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
            background = new Sprite(GameScene.CANVAS_HEIGHT - 50, new Image(getClass().getClassLoader().getResource("res/game_background.png").toString()));

            player = new Character(new Image(getClass().getClassLoader().getResource("res/running_man.png").toString()), 40);

            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100, 12));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            for (int i = 0; i < 80; i++) {
                platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/ground.png").toString()), 50));
            }

            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            for (int i = 0; i < 5; i++) {
                obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/ropes_chains/long_chain_piece.png").toString()), 13));
            }
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 18));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/gif_obstacles/spinning_spike_tower.gif").toString()), 75));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2.png").toString()), 75));

            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));

            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 24, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_2.png").toString()), 24, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 24, -1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/large_post.png").toString()), 15, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawWorld() {
        gc.clearRect(0,0, GameScene.CANVAS_WIDTH, GameScene.CANVAS_HEIGHT);
        background.render(gc);
        for (Sprite s : platforms) {
            s.render(gc);
        }
        for (Sprite s : obstacles) {
            s.render(gc);
        }
        for (Sprite s : spinningObstacles) {
            s.render(gc);
        }

        //TODO: 4 DECISIONS - JUMP FOR OPTION 1, DON'T JUMP FOR OPTION 2
        gc.setFill(Color.PINK);
        gc.fillRect(1650, 380 + diffHeight, 30, 30);
        gc.fillRect(2610, 200 + diffHeight, 30, 30);
    }

    private void setSpritePositions() {
        platforms.get(3).setPos(200, 523 + diffHeight);
        platforms.get(0).setPos(325, 480 + diffHeight);
        platforms.get(1).setPos(450, 420 + diffHeight);
        platforms.get(2).setPos(575, 360 + diffHeight);
        platforms.get(4).setPos(970, 525 + diffHeight);
        platforms.get(5).setPos(1090, 470 + diffHeight);
        platforms.get(6).setPos(1210, 523 + diffHeight);
        platforms.get(7).setPos(1360, 470 + diffHeight);
        platforms.get(8).setPos(1510, 450 + diffHeight);
        platforms.get(9).setPos(1640, 410 + diffHeight);
        platforms.get(10).setPos(1925, 410 + diffHeight);
        platforms.get(11).setPos(2050, 365 + diffHeight);
        platforms.get(12).setPos(2175, 320 + diffHeight);
        platforms.get(13).setPos(2300, 250 + diffHeight);
        platforms.get(14).setPos(2425, 290 + diffHeight);
        platforms.get(15).setPos(2600, 240 + diffHeight);
        platforms.get(16).setPos(2885, 240 + diffHeight);
        platforms.get(17).setPos(2910, 210 + diffHeight);
        platforms.get(18).setPos(3040, 210 + diffHeight);
        platforms.get(19).setPos(3010, 260 + diffHeight);
        for (int i = 20; i < 100; i++) {
            platforms.get(i).setPos(50*(i-15), 550 + diffHeight);
        }

        obstacles.get(0).setPos(412, 460 + diffHeight);
        obstacles.get(1).setPos(537, 400 + diffHeight);
        obstacles.get(2).setPos(662, 340 + diffHeight);
        obstacles.get(3).setPos(282, 505 + diffHeight);
        obstacles.get(4).setPos(1925, 362 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(5).setPos(1975, 362 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(6).setPos(820, diffHeight);
        obstacles.get(7).setPos(820, obstacles.get(6).getHeight() + diffHeight);
        obstacles.get(8).setPos(820, obstacles.get(6).getHeight()*2 + diffHeight);
        obstacles.get(9).setPos(820, obstacles.get(6).getHeight()*3 + diffHeight);
        obstacles.get(10).setPos(820, diffHeight - obstacles.get(6).getHeight());
        obstacles.get(11).setPos(1325, 520 + diffHeight);
        obstacles.get(12).setPos(1057, 526 + diffHeight - obstacles.get(12).getHeight());
        obstacles.get(13).setPos(1447, 471 + diffHeight - obstacles.get(13).getHeight());
        obstacles.get(14).setPos(2085, 550 - obstacles.get(14).getHeight() + diffHeight);
        obstacles.get(15).setPos(2285, 550 - obstacles.get(15).getHeight() + diffHeight);
        obstacles.get(16).setPos(2560, 550 - obstacles.get(16).getHeight() + diffHeight);
        obstacles.get(17).setPos(2012, 410 - obstacles.get(17).getHeight() + diffHeight);
        obstacles.get(18).setPos(2137, 370 - obstacles.get(18).getHeight() + diffHeight);
        obstacles.get(19).setPos(2262, 320 - obstacles.get(19).getHeight() + diffHeight);
        obstacles.get(20).setPos(2387, 250 - obstacles.get(20).getHeight() + diffHeight);

        spinningObstacles.get(0).setPos(175, 526 + diffHeight);
        spinningObstacles.get(1).setPos(1586, 426 + diffHeight);
        spinningObstacles.get(2).setPos(946, 526 + diffHeight);
        spinningObstacles.get(3).setPos(820 + obstacles.get(6).getWidth()/2.0 - spinningObstacles.get(3).getWidth()/2.0, diffHeight + obstacles.get(6).getHeight()*4 - spinningObstacles.get(3).getHeight()/2.0);
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
