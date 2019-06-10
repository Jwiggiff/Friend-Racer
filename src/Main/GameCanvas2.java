package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class is the canvas class used for level 2
 *
 * @author Om Patel
 * @version 1 - June 8 - 4 hours - set up all the platforms and obstacles
 * @version 2 - June 9 - 1 hour - updated the file IO to make it work with a jar file
 *
 * Variable             Type                Description
 *  _____________________________________________________________
 *  gc                   GraphicsContext     This is the part of the canvas where you can do graphics
 *  player               Character           This is the player
 *  platforms            ArrayList           This stores all the platforms
 *  obstacles            ArrayList           This stores all the obstacles
 *  spinningObstacles    ArrayList           This stores all the spinning obstacles
 *  decisions            ArrayList           This stores all the decisions
 *  background           Sprite              This is the background for the game
 *  gameOverStar         Sprite              This is the star that the user must reach to finish the level
 *  diffHeight           int                 This is the difference between the window's and canvas's height
 */
public class GameCanvas2 extends Canvas {
    //TODO: make all instance variables private and make getters and setters
    public GraphicsContext gc;
    public Character player;
    public ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    public ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    public ArrayList<SpinningSprite> spinningObstacles = new ArrayList<SpinningSprite>();
    public ArrayList<Sprite> decisions = new ArrayList<Sprite>();
    public ArrayList<Sprite> tips = new ArrayList<Sprite>();
    public Sprite background;
    public Sprite gameOverStar;
    public static int diffHeight = GameScene.CANVAS_HEIGHT - Main.WINDOW_HEIGHT;

    /**
     * This is the class constructor that makes a canvas of the specified width and height.
     *
     * @param width The width of the canvas
     * @param height The height of the canvas
     */
    public GameCanvas2(int width, int height) {
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

            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100, 12));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            for (int i = 0; i < 100; i++) {
                platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/ground.png").toString()), 50));
            }

            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 25));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/ropes_chains/long_chain_piece.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 16));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/gif_obstacles/spinning_spike_tower.gif").toString()), 50));

            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 22, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/large_post.png").toString()), 15, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_2.png").toString()), 22, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 22, -1));

            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));
            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));
            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));
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
        for (Sprite s : tips) {
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
        for (Sprite s : tips) {
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
     * @param playerX The player's relative position to the beginning of the game map
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
        for (Sprite s : tips) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        gameOverStar.setOriginalPos(gameOverStar.getOriginalPos().x - diffPlayerX, gameOverStar.getOriginalPos().y);
    }

    /**
     * This method draws the canvas, rendering all the sprites on it except
     * the player
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
        for (Sprite s : tips) {
            s.render(gc);
        }
        gameOverStar.render(gc);
    }

    /**
     * This method sets the Sprite's initial positions when the canvas is first created, right after
     * loading all the images
     */
    private void setSpritePositions() {
        gameOverStar.setOriginalPos(3165, 310 - gameOverStar.getHeight() + diffHeight);
        gameOverStar.resetPos();

        decisions.get(0).setOriginalPos(1050, 330 - decisions.get(0).getHeight() + diffHeight);
        decisions.get(1).setOriginalPos(1745, 430 - decisions.get(1).getHeight() + diffHeight);
        decisions.get(2).setOriginalPos(2585, 360 - decisions.get(2).getHeight() + diffHeight);
        for (Sprite s : decisions) {
            s.resetPos();
        }

        platforms.get(0).setOriginalPos(205, 550 - platforms.get(0).getHeight() + diffHeight);
        platforms.get(1).setOriginalPos(340, 460 + diffHeight);
        platforms.get(2).setOriginalPos(490, 500 + diffHeight);
        platforms.get(3).setOriginalPos(640, 550 - platforms.get(3).getHeight() + diffHeight);
        platforms.get(4).setOriginalPos(775, 460 + diffHeight);
        platforms.get(5).setOriginalPos(910, 400 + diffHeight);
        platforms.get(6).setOriginalPos(1045, 330 + diffHeight);
        platforms.get(7).setOriginalPos(1355, 330 + diffHeight);
        platforms.get(8).setOriginalPos(1495, 270 + diffHeight);
        platforms.get(9).setOriginalPos(1640, 235 + diffHeight);
        platforms.get(10).setOriginalPos(1275, 550 - platforms.get(10).getHeight() + diffHeight);
        platforms.get(11).setOriginalPos(1525, 550 - platforms.get(11).getHeight() + diffHeight);
        platforms.get(12).setOriginalPos(1630, 460 + diffHeight);
        platforms.get(13).setOriginalPos(1740, 430 + diffHeight);
        platforms.get(14).setOriginalPos(2035, 410 + diffHeight);
        platforms.get(15).setOriginalPos(2160, 380 + diffHeight);
        platforms.get(16).setOriginalPos(2300, 330 + diffHeight);
        platforms.get(17).setOriginalPos(2445, 370 + diffHeight);
        platforms.get(18).setOriginalPos(2580, 360 + diffHeight);
        platforms.get(19).setOriginalPos(2870, 360 + diffHeight);
        platforms.get(20).setOriginalPos(3000, 330 + diffHeight);
        platforms.get(21).setOriginalPos(3140, 310 + diffHeight);
        for (int i = 22; i < 122; i++) {
            platforms.get(i).setOriginalPos(50*(i-22), 550 + diffHeight);
        }
        for (Sprite platform : platforms) {
            platform.resetPos();
        }

        obstacles.get(0).setOriginalPos(192, 550 - obstacles.get(0).getHeight() + diffHeight);
        obstacles.get(1).setOriginalPos(287, platforms.get(0).getPos().y - obstacles.get(1).getHeight());
        obstacles.get(2).setOriginalPos(577, platforms.get(2).getPos().y - obstacles.get(2).getHeight());
        obstacles.get(3).setOriginalPos(862, platforms.get(4).getPos().y - obstacles.get(3).getHeight());
        obstacles.get(4).setOriginalPos(997, platforms.get(5).getPos().y - obstacles.get(4).getHeight());
        obstacles.get(5).setOriginalPos(1442, platforms.get(7).getPos().y - obstacles.get(5).getHeight());
        obstacles.get(6).setOriginalPos(1582, platforms.get(8).getPos().y - obstacles.get(6).getHeight());
        obstacles.get(7).setOriginalPos(1815, platforms.get(9).getPos().y - obstacles.get(7).getHeight());
        obstacles.get(8).setOriginalPos(1760, GameCanvas.diffHeight);
        obstacles.get(9).setOriginalPos(1607, platforms.get(11).getPos().y - obstacles.get(9).getHeight());
        obstacles.get(10).setOriginalPos(1717, platforms.get(12).getPos().y - obstacles.get(10).getHeight());
        obstacles.get(11).setOriginalPos(2035, platforms.get(14).getPos().y + 5);
        obstacles.get(12).setOriginalPos(2085, platforms.get(14).getPos().y + 5);
        obstacles.get(13).setOriginalPos(2122, platforms.get(14).getPos().y - obstacles.get(13).getHeight());
        obstacles.get(14).setOriginalPos(2247, platforms.get(15).getPos().y - obstacles.get(14).getHeight());
        obstacles.get(15).setOriginalPos(2532, platforms.get(17).getPos().y - obstacles.get(15).getHeight());
        obstacles.get(16).setOriginalPos(2250, 550 - obstacles.get(16).getHeight() + diffHeight);
        obstacles.get(17).setOriginalPos(2365, 550 - obstacles.get(17).getHeight() + diffHeight);
        obstacles.get(18).setOriginalPos(2415, 550 - obstacles.get(18).getHeight() + diffHeight);
        obstacles.get(19).setOriginalPos(2957, platforms.get(19).getPos().y - obstacles.get(19).getHeight());
        obstacles.get(20).setOriginalPos(3087, platforms.get(20).getPos().y - obstacles.get(20).getHeight());
        obstacles.get(21).setOriginalPos(3080, 550 - obstacles.get(21).getHeight() + diffHeight);
        for (Sprite obstacle : obstacles) {
            obstacle.resetPos();
        }

        spinningObstacles.get(0).setOriginalPos(713, platforms.get(3).getPos().y - 22);
        spinningObstacles.get(1).setOriginalPos(1760 + obstacles.get(8).getWidth()/2.0 - spinningObstacles.get(1).getWidth()/2.0, diffHeight + obstacles.get(8).getHeight() - spinningObstacles.get(1).getHeight()/2.0);
        spinningObstacles.get(2).setOriginalPos(1525 - spinningObstacles.get(2).getWidth(), 550 - spinningObstacles.get(2).getHeight() + diffHeight);
        spinningObstacles.get(3).setOriginalPos(2930, 550 - spinningObstacles.get(2).getHeight() + diffHeight);
        for (Sprite obstacle : spinningObstacles) {
            obstacle.resetPos();
        }

        tips.get(0).setOriginalPos(platforms.get(1).getPos().x+35, platforms.get(1).getPos().y-30);
        tips.get(1).setOriginalPos(platforms.get(10).getPos().x+35, platforms.get(10).getPos().y-30);
        tips.get(2).setOriginalPos(platforms.get(16).getPos().x+35, platforms.get(16).getPos().y-30);
        for (Sprite tip : tips) {
            tip.resetPos();
        }
    }

    /**
     * This method resets all the sprites' positions to their original position,
     * used during player respawning
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
        for (Sprite s : tips) {
            s.resetPos();
        }
        gameOverStar.resetPos();
    }

    /**
     * This method rotates all the spinning sprites in the canvas, and
     * then renders them
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
