package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class is the Canvas for level 1.
 *
 * @author Om Patel, Josh Friedman
 * @version 1 - May 31 - Josh Friedman - 1 hour - set up the basic structure of the class
 * @version 2 - June 3 - Om Patel - 2 hours - added platforms and obstacles to the canvas
 * @version 3 - June 8 - Om Patel - 3 hours - finished the Sprite positions
 * @version 4 - June 9 - Josh Friedman - 1 hour - updated file input so it works with a jar file
 *
 * Variable             Type                Description
 *_____________________________________________________________
 * gc                   GraphicsContext     This is the part of the canvas where you can do graphics
 * player               Character           This is the player
 * platforms            ArrayList           This stores all the platforms
 * obstacles            ArrayList           This stores all the obstacles
 * spinningObstacles    ArrayList           This stores all the spinning obstacles
 * decisions            ArrayList           This stores all the decisions
 * background           Sprite              This is the background for the game
 * gameOverStar         Sprite              This is the star that the user must reach to finish the level
 * diffHeight           int                 This is the difference between the window's and canvas's height
 */
public class GameCanvas extends Canvas {
    public GraphicsContext gc;
    public Character player;
    public ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    public ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    public ArrayList<SpinningSprite> spinningObstacles = new ArrayList<SpinningSprite>();
    public ArrayList<Sprite> decisions = new ArrayList<Sprite>();
    public Sprite background;
    public Sprite gameOverStar;
    public static int diffHeight = GameScene.CANVAS_HEIGHT - Main.WINDOW_HEIGHT;

    /**
     * This is the class constructor that creates a new Canvas
     * of the specified width and height.
     *
     * @param width The width of the Canvas
     * @param height The height of the Canvas
     */
    public GameCanvas(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        loadResources();
        setSpritePositions();

        drawWorld();
    }

    /**
     * This method loads all of the resources for the decisions, tips, platforms,
     * and obstacles.
     */
    private void loadResources() {
        try {
            gameOverStar = new Sprite(new Image(getClass().getClassLoader().getResource("res/game_over_star.png").toString()), 80);

            decisions.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/decision.png").toString()), 25));
            decisions.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/decision.png").toString()), 25));
            decisions.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/decision.png").toString()), 25));

            background = new Sprite(GameScene.CANVAS_HEIGHT - 50, new Image(getClass().getClassLoader().getResource("res/game_background.png").toString()));

            player = new Character(new Image(getClass().getClassLoader().getResource("res/characters/running/"+GameScene.playerChoice+".gif").toString()), 40);

            Image platformImage = new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString());
            Image platform1Image = new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString());
            Image longPlatformImage = new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString());
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platform1Image, 95));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platform1Image, 95));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(longPlatformImage, 200));
            platforms.add(new Sprite(platformImage, 100, 12));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(longPlatformImage, 200));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100, 12));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platform1Image, 115));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(longPlatformImage, 200));
            platforms.add(new Sprite(platformImage, 100));

            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            platforms.add(new Sprite(platformImage, 100));
            for (int i = 0; i < 100; i++) {
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
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 60));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2.png").toString()), 75));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/gif_obstacles/spinning_spike_tower.gif").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 40));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 10));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 10));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 10));

            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 22, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_2.png").toString()), 22, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 22, -1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/large_post.png").toString()), 15, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 50, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 22, -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method moves all of the sprites to the left to create the illusion of
     * the player moving forward.
     */
    public void moveSprites() {
        gc.clearRect(0,0, GameScene.CANVAS_WIDTH, GameScene.CANVAS_HEIGHT);
        for (Sprite s : platforms) {
            s.erase(gc);
        }
        for (Sprite s : obstacles) {
            s.erase(gc);
        }
        for (Sprite s : spinningObstacles) {
            s.erase(gc);
        }
        for (Sprite s : decisions) {
            s.erase(gc);
        }
        gameOverStar.erase(gc);
        background.erase(gc);
        background.setVel(-0.15, 0);
        background.update();
        background.render(gc);
        for (Sprite s : platforms) {
            s.update();
            s.render(gc);
        }
        for (Sprite s : obstacles) {
            s.update();
            s.render(gc);
        }
        for (SpinningSprite s : spinningObstacles) {
            s.update();
        }
        for (Sprite s : decisions) {
            s.update();
            s.render(gc);
        }
        gameOverStar.update();
        gameOverStar.render(gc);
    }

    /**
     * This method sets the original postion of all of the sprites when the player
     * respawns.
     *
     * @param playerX
     */
    public void setOriginalPositions(int playerX) {
        int diffPlayerX = playerX - 10;
        for (Sprite s : platforms) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        for (Sprite s : obstacles) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        for (Sprite s : spinningObstacles) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        for (Sprite s : decisions) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        gameOverStar.setOriginalPos(gameOverStar.getOriginalPos().x - diffPlayerX, gameOverStar.getOriginalPos().y);
    }

    /**
     * This method draws all of the sprites except for the player.
     */
    public void drawWorld() {
        gc.clearRect(0,0, GameScene.CANVAS_WIDTH, GameScene.CANVAS_HEIGHT);
        background.render(gc);
        for (Sprite s : platforms) {
            s.render(gc);
        }
        for (Sprite s : obstacles) {
            s.render(gc);
        }
        for (Sprite s : decisions) {
            s.render(gc);
        }
        gameOverStar.render(gc);
    }

    /**
     * This method sets the position of all of the sprites except the player.
     */
    private void setSpritePositions() {
        gameOverStar.setOriginalPos(4340, 260 - gameOverStar.getHeight() + diffHeight);
        gameOverStar.resetPos();

        decisions.get(0).setOriginalPos(1650, 410 - decisions.get(0).getHeight() + diffHeight);
        decisions.get(1).setOriginalPos(2610, 240 - decisions.get(1).getHeight() + diffHeight);
        decisions.get(2).setOriginalPos(3610, 380 - decisions.get(2).getHeight() + diffHeight);
        for (Sprite s : decisions) {
            s.resetPos();
        }

        platforms.get(3).setOriginalPos(200, 523 + diffHeight);
        platforms.get(0).setOriginalPos(325, 480 + diffHeight);
        platforms.get(1).setOriginalPos(450, 420 + diffHeight);
        platforms.get(2).setOriginalPos(575, 360 + diffHeight);
        platforms.get(4).setOriginalPos(970, 525 + diffHeight);
        platforms.get(5).setOriginalPos(1090, 470 + diffHeight);
        platforms.get(6).setOriginalPos(1210, 523 + diffHeight);
        platforms.get(7).setOriginalPos(1360, 470 + diffHeight);
        platforms.get(8).setOriginalPos(1510, 450 + diffHeight);
        platforms.get(9).setOriginalPos(1640, 410 + diffHeight);
        platforms.get(10).setOriginalPos(1930, 410 + diffHeight);
        platforms.get(11).setOriginalPos(2050, 365 + diffHeight);
        platforms.get(12).setOriginalPos(2175, 320 + diffHeight);
        platforms.get(13).setOriginalPos(2300, 250 + diffHeight);
        platforms.get(14).setOriginalPos(2425, 290 + diffHeight);
        platforms.get(15).setOriginalPos(2600, 240 + diffHeight);
        platforms.get(16).setOriginalPos(2895, 240 + diffHeight);
        platforms.get(17).setOriginalPos(3030, 190 + diffHeight);
        platforms.get(18).setOriginalPos(3130, 230 + diffHeight);
        platforms.get(19).setOriginalPos(3230, 190 + diffHeight);
        platforms.get(20).setOriginalPos(2870, 550 - platforms.get(20).getHeight() + diffHeight);
        platforms.get(21).setOriginalPos(2995, 450 + diffHeight);
        platforms.get(22).setOriginalPos(3130, 400 + diffHeight);
        platforms.get(23).setOriginalPos(3280, 430 + diffHeight);
        platforms.get(24).setOriginalPos(3405, 380 + diffHeight);
        platforms.get(25).setOriginalPos(3605, 380 + diffHeight);
        platforms.get(26).setOriginalPos(3905, 380 + diffHeight);
        platforms.get(27).setOriginalPos(4060, 350 + diffHeight);
        platforms.get(28).setOriginalPos(4200, 310 + diffHeight);
        platforms.get(29).setOriginalPos(4320, 260 + diffHeight);
        for (int i = 30; i < 130; i++) {
            platforms.get(i).setOriginalPos(50*(i-30), 550 + diffHeight);
        }
        for (Sprite platform : platforms)
            platform.resetPos();

        obstacles.get(0).setOriginalPos(412, 460 + diffHeight);
        obstacles.get(1).setOriginalPos(537, 400 + diffHeight);
        obstacles.get(2).setOriginalPos(662, 340 + diffHeight);
        obstacles.get(3).setOriginalPos(282, 505 + diffHeight);
        obstacles.get(4).setOriginalPos(1930, 364 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(5).setOriginalPos(1980, 364 + platforms.get(50).getHeight() + diffHeight);
        obstacles.get(6).setOriginalPos(820, diffHeight);
        obstacles.get(7).setOriginalPos(820, obstacles.get(6).getHeight() + diffHeight);
        obstacles.get(8).setOriginalPos(820, obstacles.get(6).getHeight()*2 + diffHeight);
        obstacles.get(9).setOriginalPos(820, obstacles.get(6).getHeight()*3 + diffHeight);
        obstacles.get(10).setOriginalPos(820, diffHeight - obstacles.get(6).getHeight());
        obstacles.get(11).setOriginalPos(1325, 520 + diffHeight);
        obstacles.get(12).setOriginalPos(1057, 526 + diffHeight - obstacles.get(12).getHeight());
        obstacles.get(13).setOriginalPos(1447, 471 + diffHeight - obstacles.get(13).getHeight());
        obstacles.get(14).setOriginalPos(2085, 550 - obstacles.get(14).getHeight() + diffHeight);
        obstacles.get(15).setOriginalPos(2285, 550 - obstacles.get(15).getHeight() + diffHeight);
        obstacles.get(16).setOriginalPos(2560, 550 - obstacles.get(16).getHeight() + diffHeight);
        obstacles.get(17).setOriginalPos(2017, 410 - obstacles.get(17).getHeight() + diffHeight);
        obstacles.get(18).setOriginalPos(2137, 365 - obstacles.get(18).getHeight() + diffHeight);
        obstacles.get(19).setOriginalPos(2262, 320 - obstacles.get(19).getHeight() + diffHeight);
        obstacles.get(20).setOriginalPos(2387, 250 - obstacles.get(20).getHeight() + diffHeight);
        obstacles.get(21).setOriginalPos(3150, 230 - obstacles.get(21).getHeight() + diffHeight);
        obstacles.get(22).setOriginalPos(2983, 240 - obstacles.get(22).getHeight() + diffHeight);
        obstacles.get(23).setOriginalPos(3130, 236 + diffHeight);
        obstacles.get(24).setOriginalPos(3180, 236 + diffHeight);
        obstacles.get(25).setOriginalPos(3082, 450 - obstacles.get(25).getHeight() + diffHeight);
        obstacles.get(26).setOriginalPos(3367, 430 - obstacles.get(26).getHeight() + diffHeight);
        obstacles.get(27).setOriginalPos(3530, 550 - obstacles.get(27).getHeight() + diffHeight);
        obstacles.get(28).setOriginalPos(4155, 550 - obstacles.get(28).getHeight() + diffHeight);
        obstacles.get(29).setOriginalPos(3960, 550 - obstacles.get(29).getHeight() + diffHeight);
        obstacles.get(30).setOriginalPos(3995, 380 - obstacles.get(30).getHeight() + diffHeight);
        obstacles.get(31).setOriginalPos(4150, 350 - obstacles.get(31).getHeight() + diffHeight);
        obstacles.get(32).setOriginalPos(4290, 310 - obstacles.get(32).getHeight() + diffHeight);
        for (Sprite obstacle : obstacles) {
            obstacle.resetPos();
        }

        spinningObstacles.get(0).setOriginalPos(176, 527 + diffHeight);
        spinningObstacles.get(1).setOriginalPos(1587, 427 + diffHeight);
        spinningObstacles.get(2).setOriginalPos(947, 527 + diffHeight);
        spinningObstacles.get(3).setOriginalPos(820 + obstacles.get(6).getWidth()/2.0 - spinningObstacles.get(3).getWidth()/2.0, diffHeight + obstacles.get(6).getHeight()*4 - spinningObstacles.get(3).getHeight()/2.0);
        spinningObstacles.get(4).setOriginalPos(3280, 140 + diffHeight);
        spinningObstacles.get(5).setOriginalPos(2963, 528 - platforms.get(20).getHeight() + diffHeight);
        for (Sprite obstacle : spinningObstacles) {
            obstacle.resetPos();
        }
    }

    /**
     *This method resets the position of all of the sprites to the original position.
     */
    public void resetSpritePositions() {
        for (Sprite s : obstacles) {
            s.resetPos();
        }
        for (Sprite s : spinningObstacles) {
            s.resetPos();
        }
        for (Sprite s : platforms) {
            s.resetPos();
        }
        for (Sprite s : decisions) {
            s.resetPos();
        }
        gameOverStar.resetPos();
    }

    /**
     * This method rotates all of the spinning sprites.
     */
    public void rotateSpinningSprites() {
        for (SpinningSprite s : spinningObstacles) {
            if (s.getRotationDirection() == 1) {
                s.rotateImage(gc, 3);
            } else {
                s.rotateImage(gc, -3);
            }
        }
    }
}
