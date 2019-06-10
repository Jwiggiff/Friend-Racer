package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * This class is the canvas class used for level 3 of the game.
 *
 * @author Om Patel
 * @version 1 - June 8 - 4 hours - set the positions of all the sprites
 * @version 2 - June 9 - 1 hour - updated the file IO so that it works with a jar file
 *
 * Variable             Type                Description
 *  _____________________________________________________________
 *  gc                   GraphicsContext     This is the part of the canvas where you can do graphics
 *  player               Character           This is the player
 *  platforms            ArrayList           This stores all the platforms
 *  obstacles            ArrayList           This stores all the obstacles
 *  spinningObstacles    ArrayList           This stores all the spinning obstacles
 *  decisions            ArrayList           This stores all the decisions
 *  tips                 ArrayList           This stores all the tips
 *  invites              ArrayList           This stores all the people to invite
 *  background           Sprite              This is the background for the game
 *  gameOverStar         Sprite              This is the star that the user must reach to finish the level
 *  diffHeight           int                 This is the difference between the window's and canvas's height
 */
public class GameCanvas3 extends Canvas {
    //TODO: make all instance variables private and make getters and setters
    public GraphicsContext gc;
    public Character player;
    public ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    public ArrayList<Sprite> obstacles = new ArrayList<Sprite>();
    public ArrayList<SpinningSprite> spinningObstacles = new ArrayList<SpinningSprite>();
    public ArrayList<Sprite> decisions = new ArrayList<Sprite>();
    public ArrayList<Sprite> tips = new ArrayList<Sprite>();
    public ArrayList<Sprite> invites = new ArrayList<Sprite>();
    public Sprite background;
    public Sprite gameOverStar;
    public static int diffHeight = GameScene.CANVAS_HEIGHT - Main.WINDOW_HEIGHT;

    /**
     * This is the constructor for the class, that sets the width and height
     * of the canvas
     *
     * @param width The width of the canvas
     * @param height The height of the canvas
     */
    public GameCanvas3(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        loadResources();
        setSpritePositions();

        drawWorld();
    }

    /**
     * This method loads all the Sprites from memory and adds them to the ArrayLists
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
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100, 12));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/long_platform.png").toString()), 200));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform_1.png").toString()), 95));
            platforms.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/platforms/platform.png").toString()), 100));
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
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 16));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/gif_obstacles/spinning_spike_tower.gif").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_2 - upside down.png").toString()), 50));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/spikes_1.png").toString()), 60));
            obstacles.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/spikes/single_spike.png").toString()), 13));

            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 22, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 22, -1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_1.png").toString()), 50, 1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_3.png").toString()), 22, -1));
            spinningObstacles.add(new SpinningSprite(new Image(getClass().getClassLoader().getResource("res/rotating_blades/blade_2.png").toString()), 22, 1));

            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));
            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));
            tips.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/tip.png").toString()), 30));

            invites.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/invite1.png").toString()), 100));
            invites.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/invite2.png").toString()), 100));
            invites.add(new Sprite(new Image(getClass().getClassLoader().getResource("res/invite1.png").toString()), 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method moves all the sprites on the screen to their new position
     * after their positions have been updated and they have been rendered
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
        for (Sprite s : invites) {
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
        for (Sprite s : invites) {
            s.update();
            s.render(gc);
        }
        gameOverStar.update();
        gameOverStar.render(gc);
    }

    /**
     * This method sets the original positions of all the Sprites, which is where
     * theSprites reset to when the player respawns.
     *
     * @param playerX The relative position of the player sprite to the beginning of the game.
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
        for (Sprite s : invites) {
            s.setOriginalPos(s.getOriginalPos().x - diffPlayerX, s.getOriginalPos().y);
        }
        gameOverStar.setOriginalPos(gameOverStar.getOriginalPos().x - diffPlayerX, gameOverStar.getOriginalPos().y);
    }

    /**
     * This method draws and renders all the sprites
     * on the canvas
     */
    public void drawWorld() {
        gc.clearRect(0, 0, GameScene.CANVAS_WIDTH, GameScene.CANVAS_HEIGHT);
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
        for (Sprite s : invites) {
            s.render(gc);
        }
        gameOverStar.render(gc);
    }

    /**
     * This method sets the sprite's positions after the images have
     * been loaded from memory
     */
    private void setSpritePositions() {
        gameOverStar.setOriginalPos(3765, 290 - gameOverStar.getHeight() + diffHeight);
        gameOverStar.resetPos();

        decisions.get(0).setOriginalPos(1065, 365 - decisions.get(0).getHeight() + diffHeight);
        decisions.get(1).setOriginalPos(1905, 380 - decisions.get(1).getHeight() + diffHeight);
        decisions.get(2).setOriginalPos(3195, 365 - decisions.get(2).getHeight() + diffHeight);
        for (Sprite s : decisions) {
            s.resetPos();
        }

        platforms.get(0).setOriginalPos(175, 550 - platforms.get(0).getHeight() + diffHeight);
        platforms.get(1).setOriginalPos(300, 450 + diffHeight);
        platforms.get(2).setOriginalPos(440, 550 - platforms.get(2).getHeight() + diffHeight);
        platforms.get(3).setOriginalPos(690, 520 + diffHeight);
        platforms.get(4).setOriginalPos(800, 460 + diffHeight);
        platforms.get(5).setOriginalPos(930, 405 + diffHeight);
        platforms.get(6).setOriginalPos(1060, 365 + diffHeight);
        platforms.get(7).setOriginalPos(1360, 365 + diffHeight);
        platforms.get(8).setOriginalPos(1490, 325 + diffHeight);
        platforms.get(9).setOriginalPos(1630, 355 + diffHeight);
        platforms.get(10).setOriginalPos(1760, 420 + diffHeight);
        platforms.get(11).setOriginalPos(1900, 380 + diffHeight);
        platforms.get(12).setOriginalPos(1270, 550 - platforms.get(12).getHeight() + diffHeight);
        platforms.get(13).setOriginalPos(2195, 370 + diffHeight);
        platforms.get(14).setOriginalPos(2325, 340 + diffHeight);
        platforms.get(15).setOriginalPos(2455, 310 + diffHeight);
        platforms.get(16).setOriginalPos(2505, 550 - platforms.get(16).getHeight() + diffHeight);
        platforms.get(17).setOriginalPos(2645, 450 + diffHeight);
        platforms.get(18).setOriginalPos(2775, 415 + diffHeight);
        platforms.get(19).setOriginalPos(2910, 380 + diffHeight);
        platforms.get(20).setOriginalPos(3050, 410 + diffHeight);
        platforms.get(21).setOriginalPos(3190, 365 + diffHeight);
        platforms.get(22).setOriginalPos(3485, 365 + diffHeight);
        platforms.get(23).setOriginalPos(3610, 325 + diffHeight);
        platforms.get(24).setOriginalPos(3745, 290 + diffHeight);
        for (int i = 25; i < 125; i++) {
            platforms.get(i).setOriginalPos(50*(i-25), 550 + diffHeight);
        }
        for (Sprite platform : platforms) {
            platform.resetPos();
        }

        obstacles.get(0).setOriginalPos(257, platforms.get(0).getPos().y - obstacles.get(0).getHeight());
        obstacles.get(1).setOriginalPos(677, 550 - obstacles.get(1).height + diffHeight);
        obstacles.get(2).setOriginalPos(777, platforms.get(3).getPos().y - obstacles.get(2).getHeight());
        obstacles.get(3).setOriginalPos(887, platforms.get(4).getPos().y - obstacles.get(3).getHeight());
        obstacles.get(4).setOriginalPos(1447, platforms.get(7).getPos().y - obstacles.get(4).getHeight());
        obstacles.get(5).setOriginalPos(1717, platforms.get(9).getPos().y - obstacles.get(5).getHeight());
        obstacles.get(6).setOriginalPos(1847, platforms.get(10).getPos().y - obstacles.get(6).getHeight());
        obstacles.get(7).setOriginalPos(1600, 550 - obstacles.get(7).getHeight() + diffHeight);
        obstacles.get(8).setOriginalPos(1720, 550 - obstacles.get(8).getHeight() + diffHeight);
        obstacles.get(9).setOriginalPos(1360, 370 + diffHeight);
        obstacles.get(10).setOriginalPos(1410, 370 + diffHeight);
        obstacles.get(11).setOriginalPos(2282, platforms.get(13).getPos().y - obstacles.get(11).getHeight());
        obstacles.get(12).setOriginalPos(2412, platforms.get(14).getPos().y - obstacles.get(12).getHeight());
        obstacles.get(13).setOriginalPos(2350, 550 - obstacles.get(13).getHeight() + diffHeight);
        obstacles.get(14).setOriginalPos(2587, platforms.get(16).getPos().y - obstacles.get(14).getHeight());
        obstacles.get(15).setOriginalPos(2732, platforms.get(17).getPos().y - obstacles.get(15).getHeight());
        obstacles.get(16).setOriginalPos(2862, platforms.get(18).getPos().y - obstacles.get(16).getHeight());
        obstacles.get(17).setOriginalPos(3572, platforms.get(22).getPos().y - obstacles.get(17).getHeight());
        obstacles.get(18).setOriginalPos(3697, platforms.get(23).getPos().y - obstacles.get(18).getHeight());
        obstacles.get(19).setOriginalPos(3700, 550 - obstacles.get(19).getHeight() + diffHeight);
        obstacles.get(20).setOriginalPos(3137, platforms.get(20).getPos().y - obstacles.get(20).getHeight());
        for (Sprite obstacle : obstacles) {
            obstacle.resetPos();
        }

        spinningObstacles.get(0).setOriginalPos(143, 550 - spinningObstacles.get(0).getHeight() + diffHeight);
        spinningObstacles.get(1).setOriginalPos(998, platforms.get(5).getPos().y - spinningObstacles.get(1).getHeight());
        spinningObstacles.get(2).setOriginalPos(2520, platforms.get(15).getPos().y - spinningObstacles.get(2).getHeight());
        spinningObstacles.get(3).setOriginalPos(2478, 550 - spinningObstacles.get(3).getHeight() + diffHeight);
        spinningObstacles.get(4).setOriginalPos(3550, 550 - spinningObstacles.get(4).getHeight() + diffHeight);
        for (Sprite obstacle : spinningObstacles) {
            obstacle.resetPos();
        }

        tips.get(0).setOriginalPos(platforms.get(1).getPos().x+35, platforms.get(1).getPos().y-30);
        tips.get(1).setOriginalPos(platforms.get(8).getPos().x+35, platforms.get(8).getPos().y-30);
        tips.get(2).setOriginalPos(obstacles.get(13).getPos().x-200, obstacles.get(13).getPos().y-5);
        for (Sprite tip : tips) {
            tip.resetPos();
        }

        invites.get(0).setOriginalPos(obstacles.get(1).getPos().x-125, obstacles.get(1).getPos().y-41);
        invites.get(1).setOriginalPos(obstacles.get(13).getPos().x-125, obstacles.get(13).getPos().y-41);
        invites.get(2).setOriginalPos(platforms.get(19).getPos().x, platforms.get(19).getPos().y-64);
        for (Sprite invite : invites) {
            invite.resetPos();
        }
    }

    /**
     * This method resets the sprites' positions to their original positions, this
     * method is used for player respawning
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
        for (Sprite s : invites) {
            s.resetPos();
        }
        gameOverStar.resetPos();
    }

    /**
     * This method is used for rotating all the spinning sprites on the
     * canvas, and rendering them as well
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
