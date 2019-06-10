package Main;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;

/**
 * This class is the AnimationTimer class for level 2.
 *
 * @author Om Patel
 * @version 1 - June 5 - 1 hour - Om Patel - set up the structure of the class
 * @version 2 - June 7 - 3 hours - Om Patel - set up the checkpoints and the game mechanics like gravity and jumping
 * @version 3 - June 9 - 1 hour - Josh Friedman - set up the Decisions and Tips for the map
 *
 * Variable             Type                Description
 * __________________________________________________________________
 * canvas               GameCanvas2         This is the canvas for level 2
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
public class GameLoop2 extends AnimationTimer {
    private GameCanvas2 canvas;
    private GameScene scene;
    public static boolean jump = false;
    public static boolean respawning = true;
    private long startGravityTime = -1;
    private long checkPoint1Time = -1;
    private long checkPoint2Time = -1;
    private long checkPoint3Time = -1;
    private int respawnX = 10;
    private int respawnY;
    private Decision[] decisions = new Decision[3];
    private int curDecision = 0;
    public static boolean showingDecision = false;
    public Tip[] tips = new Tip[3];
    public int curTip = 0;

    /**
     * This is the constructor for the GameLoop2 class
     *
     * @param canvas The canvas for level 2
     * @param scene The GameScene for the game
     */
    public GameLoop2(GameCanvas2 canvas, GameScene scene) {
        this.start();
        this.canvas = canvas;
        this.scene = scene;
        respawnY = 550 - canvas.player.getHeight(); // normally 550

        decisions[0] = new Decision("You're at a party and you see someone across the room sitting by themselves.", "Ignore them and check Snapchat", "Talk to them", GameScene.gameGroup, canvas.player, null, canvas, null);
        decisions[1] = new Decision("You walk into class on the first day of school. There is a group of kids already talking in the middle of the classroom.", "Go over and introduce yourself", "Sit in the corner and go on your phone", GameScene.gameGroup, canvas.player, null, canvas, null);
        decisions[2] = new Decision("You are walking down the street and you see someone ahead walking in the opposite direction.", "Smile at them", "Pull out your phone and avoid eye contact", GameScene.gameGroup, canvas.player, null, canvas, null);

        tips[0] = new Tip("It's always better to talk to someone who's by themself than to ignore their existence. Who knows, they might be your new best friend.", GameScene.gameGroup);
        tips[1] = new Tip("Even if you don't know someone, even just a little smile can make both of your days better.", GameScene.gameGroup);
        tips[2] = new Tip("Sometimes it's better to approach them instead of waiting for them to approach you.", GameScene.gameGroup);
    }

    /**
     * This method overrides the one in AnimationTimer, and is called every frame
     *
     * @param currentTime The current system time in nanoseconds
     */
    @Override
    public void handle(long currentTime) {
        //TODO: Scroll world up and down
        canvas.player.erase(canvas.gc);

        if (startGravityTime == -1) {
            startGravityTime = currentTime;

            //FOR DEBUGGING
            //canvas.setOriginalPositions(3605);
            //canvas.resetSpritePositions();

            canvas.player.respawn(respawnY, this);
            canvas.setTranslateY(-GameCanvas2.diffHeight);
        }

        if (canvas.player.intersects(canvas.gameOverStar)) {
            this.stop();
            GameScene.onLvl2 = false;
            GameScene.gameGroup.getChildren().remove(canvas);
            scene.introLvl3();
        }

        if (checkPoint1Time == -1 && canvas.player.intersects(canvas.decisions.get(0))) {
            checkPoint1Time = currentTime;

            //COMMENTED FOR DEBUGGING
            canvas.setOriginalPositions(1050);

            respawnY = 330 - canvas.player.getHeight();
        } else if (checkPoint2Time == -1 && canvas.player.intersects(canvas.decisions.get(1))) {
            checkPoint2Time = currentTime;

            //COMMENTED FOR DEBUGGING
            canvas.setOriginalPositions(1740 - 1050);

            respawnY = 430 - canvas.player.getHeight();
        } else if (checkPoint3Time == -1 && canvas.player.intersects(canvas.decisions.get(2))) {
            checkPoint3Time = currentTime;

            //COMMENTED FOR DEBUGGING
            canvas.setOriginalPositions(2580 - 1740);

            respawnY = 360 - canvas.player.getHeight();
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
