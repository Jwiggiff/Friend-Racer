package Main;

import javafx.animation.AnimationTimer;

/**
 * This class is the AnimationTimer class for level 23.
 *
 * @author Om Patel
 * @version 1 - June 6 - 1 hour - Om Patel - set up the structure of the class
 * @version 2 - June 8 - 3 hours - Om Patel - set up the checkpoints and the game mechanics like gravity and jumping
 * @version 3 - June 9 - 1 hour - Josh Friedman - set up the Decisions, people to invite, and Tips for the map
 *
 * Variable             Type                Description
 * __________________________________________________________________
 * canvas               GameCanvas3         This is the canvas for level 3
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
 * tips                 Tip[]               Stores the Tips for the level
 * curTrip              int                 Stores the index for the current tip
 */
public class GameLoop3 extends AnimationTimer {
    private GameCanvas3 canvas;
    private GameScene scene;
    private Main app;
    public static boolean jump = false;
    public static boolean respawning = true;
    private long startGravityTime = -1;
    private long checkPoint1Time = -1;
    private long checkPoint2Time = -1;
    private long checkPoint3Time = -1;
    private int respawnX = 10; // normally 10
    private int respawnY;
    private Decision[] decisions = new Decision[3];
    private int curDecision = 0;
    public static boolean showingDecision = false;
    public Tip[] tips = new Tip[3];
    public int curTip = 0;

    /**
     * This is the constructor for the GameLoop3 class
     *
     * @param canvas This is the canvas for level 3
     * @param scene This is the GameScene for the game
     * @param app This is the Main application for the entire program, used for ending the game
     */
    public GameLoop3(GameCanvas3 canvas, GameScene scene, Main app) {
        this.start();
        this.canvas = canvas;
        this.scene = scene;
        this.app = app;
        respawnY = 550 - canvas.player.getHeight();

        decisions[0] = new Decision("You're at a party and you see someone looking lonely in the corner.", "Go over and talk to them", "Pretend you don't see them", GameScene.gameGroup, canvas.player, null, null, canvas);
        decisions[1] = new Decision("You're having a conversation with someone you just met. You feel your phone buzz in your pocket.", "Check your phone", "Ignore the notification and keep talking to your new friend", GameScene.gameGroup, canvas.player, null, null, canvas);
        decisions[2] = new Decision("You are walking down the street and see someone you know turn the corner ahead of you.", "Run ahead and say hi to them", "Slow down so they hopefully don't see you", GameScene.gameGroup, canvas.player, null, null, canvas);

        tips[0] = new Tip("If you see someone who looks lonely, remember how you feel when you're lonely and how badly you need someone to come talk to you.", GameScene.gameGroup);
        tips[1] = new Tip("If you're talking to someone and you get a notification on your phone, ignore it for now. The conversation is most likely much more important than the notification.", GameScene.gameGroup);
        tips[2] = new Tip("If you see someone you know in public, say hi!", GameScene.gameGroup);
    }

    /**
     * This method overrides the one in AnimationTimer, it is called
     * every frame during the animation
     *
     * @param currentTime The current system time in nanoseconds
     */
    @Override
    public void handle(long currentTime) {
        //TODO: Scroll world up and down
        canvas.player.erase(canvas.gc);

        if (startGravityTime == -1) {
            startGravityTime = currentTime;
            canvas.player.respawn(respawnY, this);
            canvas.setTranslateY(-GameCanvas.diffHeight);
        }

        if (canvas.player.intersects(canvas.gameOverStar)) {
            this.stop();
            GameScene.onLvl3 = false;
            GameScene.gameGroup.getChildren().remove(canvas);
            app.setGameOverScene();
        }

        if (checkPoint1Time == -1 && canvas.player.intersects(canvas.decisions.get(0))) {
            checkPoint1Time = currentTime;
            canvas.setOriginalPositions(1065);
            respawnY = 365 - canvas.player.getHeight();
        } else if (checkPoint2Time == -1 && canvas.player.intersects(canvas.decisions.get(1))) {
            checkPoint2Time = currentTime;
            canvas.setOriginalPositions(1905 - 1065);
            respawnY = 380 - canvas.player.getHeight();
        } else if (checkPoint3Time == -1 && canvas.player.intersects(canvas.decisions.get(2))) {
            checkPoint3Time = currentTime;
            canvas.setOriginalPositions(3195 - 1905);
            respawnY = 365 - canvas.player.getHeight();
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
                for (Sprite s : canvas.tips)
                    s.setVel(-3, 0);
                for (Sprite s : canvas.invites)
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
                GameScene.numDeaths++;
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
                GameScene.numDeaths++;
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
