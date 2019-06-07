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
        this.setTranslateY(diffHeight);
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

            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 75));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 75));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 80, 12));
            for (int i = 11; i < 61; i++) {
                platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/ground.png").toString()), 50));
            }

            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 40));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 40));
            for (int i = 0; i < 4; i++) {
                obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/ropes_chains/long_chain_piece.png").toString()), 13));
            }
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 10));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 10));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 15));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/gif_obstacles/spinning_spike_tower.gif").toString()), 50));

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
        gc.fillRect(1450, 320 + diffHeight, 30, 30);
    }

    private void setSpritePositions() {
        platforms.get(0).setPos(295, 480 + diffHeight);
        platforms.get(1).setPos(390, 420 + diffHeight);
        platforms.get(2).setPos(485, 360 + diffHeight);
        platforms.get(3).setPos(200, 523 + diffHeight);
        platforms.get(4).setPos(870, 525 + diffHeight);
        platforms.get(5).setPos(970, 470 + diffHeight);
        platforms.get(6).setPos(1070, 523 + diffHeight);
        platforms.get(7).setPos(1210, 470 + diffHeight);
        platforms.get(8).setPos(1310, 450 + diffHeight);
        platforms.get(9).setPos(1420, 410 + diffHeight);
        platforms.get(10).setPos(1585, 410 + diffHeight);
        for (int i = 11; i < 61; i++) {
            platforms.get(i).setPos(50*(i-15), 550 + diffHeight);
        }

        obstacles.get(0).setPos(362, 460 + diffHeight);
        obstacles.get(1).setPos(457, 400 + diffHeight);
        obstacles.get(2).setPos(552, 340 + diffHeight);
        obstacles.get(3).setPos(262, 505 + diffHeight);
        obstacles.get(4).setPos(1585, 362 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(5).setPos(1625, 362 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(6).setPos(720, diffHeight);
        obstacles.get(7).setPos(720, obstacles.get(6).getHeight() + diffHeight);
        obstacles.get(8).setPos(720, obstacles.get(6).getHeight()*2 + diffHeight);
        obstacles.get(9).setPos(720, obstacles.get(6).getHeight()*3 + diffHeight);
        obstacles.get(10).setPos(1170, 520 + diffHeight);
        obstacles.get(11).setPos(940, 510 + diffHeight);
        obstacles.get(12).setPos(1280, 455 + diffHeight);
        obstacles.get(13).setPos(1785, 550 - obstacles.get(13).getHeight() + diffHeight);
        //obstacles.get(14).setPos(1985, 550 - obstacles.get(14).getHeight() + diffHeight);

        spinningObstacles.get(0).setPos(175, 526 + diffHeight);
        spinningObstacles.get(1).setPos(1366, 426 + diffHeight);
        spinningObstacles.get(2).setPos(846, 526 + diffHeight);
        spinningObstacles.get(3).setPos(720 + obstacles.get(6).getWidth()/2 - spinningObstacles.get(3).getWidth()/2, diffHeight + obstacles.get(6).getHeight()*4 - spinningObstacles.get(3).getHeight()/2);
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
