package Main;

import javafx.animation.AnimationTimer;

/**
 * This class is the AnimationTimer class for level 1.
 *
 * @author Om Patel, Josh Friedman
 * @version 1 - June 2 - 1 hour - Josh Friedman - set up the structure of the class
 * @version 2 - June 6 - 3 hours - Om Patel - set up the checkpoints and the game mechanics like gravity and jumping
 * @version 3 - June 9 - 1 hour - Josh Friedman - set up the Decisions for the map
 *
 * Variable             Type                Description
 * __________________________________________________________________
 * canvas               GameCanvas          This is the canvas for level 1
 * scene                GameScene           This is the game scene
 * jump                 boolean             This indicates whether or not the player has jumped
 * respawning           boolean             This indicates whether the player is respawning
 * startGravityTime     long                This is the system time when gravity starts being applied
 * checkPoint1Time      long                This is the system time when the user reaches checkpoint 1
 * checkPoint2Time      long                This is the system time when the user reaches checkpoint 2
 * checkPoint3Time      long                This is the system time when the user reaches checkpoint 3
 * respawnX             int                 This is the x-coordinate of where the player should respawn
 * respawnY             int                 This is the y-coordinate of where the player should respawn
 * decisions            Decision[]          This is the array that stores the decisions
 * curDecision          int                 This stores the index of the current decision
 * showingDecision      boolean             Indicates whether a decision is being shown
 */
public class GameLoop extends AnimationTimer {
    private GameCanvas canvas;
    private GameScene scene;
    public static boolean jump = false;
    public static boolean respawning = true;
    private long startGravityTime = -1;
    private long checkPoint1Time = -1;
    private long checkPoint2Time = -1;
    private long checkPoint3Time = -1;
    private int respawnX =1;
    private int respawnY;
    private Decision[] decisions = new Decision[3];
    private int curDecision = 0;
    public static boolean showingDecision = false;

    /**
     * This is the constructor for the GameLoop class.
     *
     * @param canvas The GameCanvas for the animation
     * @param scene The GameScene for the game
     */
    public GameLoop(GameCanvas canvas, GameScene scene) {
        this.start();
        this.canvas = canvas;
        this.scene = scene;
        respawnY = 550 - canvas.player.getHeight(); // normally 550

        decisions[0] = new Decision("You walk into the cafeteria at lunch and you see some kids you know sitting together and laughing.", "Go sit with them", "Sit at your locker and play games on your phone", GameScene.gameGroup, canvas.player, canvas, null, null);
        decisions[1] = new Decision("Your teacher tells your class to choose a partner for a project.", "Wait to see who's left", "Ask someone to be your partner", GameScene.gameGroup, canvas.player, canvas, null, null);
        decisions[2] = new Decision("Your friend invites you to come see a movie.", "Tell them you'll be there", "Tell them you're busy and stay home",  GameScene.gameGroup, canvas.player, canvas, null, null);
    }

    /**
     * This is the method that overrides the method in AnimationTimer,
     * it is called every frame for the animation
     *
     * @param currentTime The current system time
     */
    @Override
    public void handle(long currentTime) {
        canvas.player.erase(canvas.gc);

        if (startGravityTime == -1) {
            startGravityTime = currentTime;

            canvas.player.respawn(respawnY, this);
            canvas.setTranslateY(-GameCanvas.diffHeight);
        }

        if (canvas.player.intersects(canvas.gameOverStar)) {
            this.stop();
            GameScene.onLvl1 = false;
            GameScene.gameGroup.getChildren().remove(canvas);
            scene.introLvl2();
        }

        if (checkPoint1Time == -1 && canvas.player.intersects(canvas.decisions.get(0))) {
            checkPoint1Time = currentTime;
            canvas.setOriginalPositions(1640);
            respawnY = 410 - canvas.player.getHeight();
        } else if (checkPoint2Time == -1 && canvas.player.intersects(canvas.decisions.get(1))) {
            checkPoint2Time = currentTime;
            canvas.setOriginalPositions(2600 - 1640);
            respawnY = 240 - canvas.player.getHeight();
        } else if (checkPoint3Time == -1 && canvas.player.intersects(canvas.decisions.get(2))) {
            checkPoint3Time = currentTime;
            canvas.setOriginalPositions(3605 - 2600);
            respawnY = 380 - canvas.player.getHeight();
        }

        if (!showingDecision) {
            if (canvas.player.getPos().x >= 200) {
                for (Sprite s : canvas.platforms)
                    s.setVel(-3, 0);
                for (Sprite s : canvas.obstacles)
                    s.setVel(-3, 0);
                for (Sprite s : canvas.spinningObstacles)
                    s.setVel(-3, 0);
                for (Sprite s : canvas.decisions)
                    s.setVel(-3, 0);
                canvas.gameOverStar.setVel(-3, 0);
                canvas.moveSprites();
                canvas.player.setVel(0, canvas.player.getVel().y);
            } else {
                canvas.drawWorld();
                canvas.player.setVel(3, canvas.player.getVel().y);
            }
        } else {
            canvas.moveSprites();
            canvas.rotateSpinningSprites();
            canvas.player.setVel(0, canvas.player.getVel().y);
        }

        if (canvas.player.isFullyOnPlatform(canvas.platforms)) {
            startGravityTime = currentTime;
        }

        //Run if player is floating above a platform
        if (canvas.player.playerPlatformStatus(canvas.platforms)[0] == -1) {
            canvas.player.applyGravity(currentTime, startGravityTime);
        }

        //Rotate all spinning sprites
        canvas.rotateSpinningSprites();

        //Decision collision detection.
        if (!showingDecision) {
            for (Sprite decision : canvas.decisions) {
                if (canvas.player.intersects(decision)) {
                    showingDecision = true;
                    decisions[curDecision].show();
                    curDecision++;
                    break;
                }
            }
        }

        //More collision detection. Yay! For obstacles
        for (Sprite obstacle : canvas.obstacles) {
            if (canvas.player.intersects(obstacle)) {
                canvas.resetSpritePositions();
                if (!(checkPoint1Time == -1)) {
                    curDecision--;
                }
                canvas.background.setOriginalPos(0, 0);
                canvas.background.resetPos();
                canvas.drawWorld();
                canvas.rotateSpinningSprites();
                canvas.player.respawn(respawnY, this);
                respawning = true;
                jump = false;
                break;
            }
        }
        for (SpinningSprite spinningObstacle : canvas.spinningObstacles) {
            if (canvas.player.intersects(spinningObstacle)) {
                canvas.resetSpritePositions();
                if (!(checkPoint1Time == -1)) {
                    curDecision--;
                }
                canvas.background.setOriginalPos(0, 0);
                canvas.background.resetPos();
                canvas.drawWorld();
                canvas.rotateSpinningSprites();
                canvas.player.respawn(respawnY, this);
                respawning = true;
                jump = false;
                break;
            }
        }

        //Collision detection for platforms
        Sprite temp = new Sprite(canvas.player);
        temp.setVel(canvas.player.getVel().x, canvas.player.getVel().y);
        temp.update();
        for (Sprite platform : canvas.platforms) {
            if (temp.intersects(platform)) {
                canvas.player.setPos(canvas.player.getPos().x, platform.getPos().y - canvas.player.getHeight());
                canvas.player.setVel(canvas.player.getVel().x, 0);
                break;
            }
        }

        //Run on each jump
        if (jump && !respawning) {
            startGravityTime = currentTime;
            canvas.player.jump();
            jump = false;
        }

        //Update player
        canvas.player.update();

        //Render player
        canvas.player.render(canvas.gc);
    }
}
