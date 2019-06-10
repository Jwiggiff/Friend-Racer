package Main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This is the Character class used as a template for each player.
 *
 * @author Om Patel
 *
 * @version 1 - May 31 - Om Patel - 1 hour - created the basic framework
 * @version 2 - June 2 - Om Patel - 20 mins - added the playerPlatformStatus method
 * @version 3 - June 6 - Om Patel - 10 mins - updated the gravity and jumping force
 * @version 4 - June 9 - Om Patel - 1 hour - updated the respawn method to make it work with all the GameLoop classes
 */
public class Character extends Sprite {
    /**
     * This is a class constructor that just sets the image and width.
     *
     * @param image The image to use as the sprite
     * @param width The width of the Sprite
     */
    public Character(Image image, int width) {
        super(image, width);
    }

    /**
     * This is a class constructor that makes a new duplicate Character at pos.
     *
     * @param c The Character to duplicate
     * @param pos The pos of the duplicate Character
     */
    public Character(Character c, Vector2D pos) {
        super(c.image, c.width);
        super.pos = pos;
    }

    /**
     * This method creates a Timeline object used to pause for duration seconds.
     *
     * @param timer The AnimationTimer to pause
     * @param duration The amount of time to pause for
     * @return A Timeline used to pause for a set amount of time
     */
    public Timeline createPauseTimerTimeline(AnimationTimer timer, Duration duration) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> timer.stop()),
                new KeyFrame(duration, event -> timer.start())
        );
    }

    /**
     * This method respawns the player.
     *
     * @param respawnY The Y value to respawn the player at
     * @param timer The AnimationTimer to pause after the respawn
     */
    public void respawn(int respawnY, AnimationTimer timer) {
        if (GameScene.onLvl1) {
            GameLoop.respawning = true;
        } else if (GameScene.onLvl2) {
            GameLoop2.respawning = true;
        } else if (GameScene.onLvl3) {
            GameLoop3.respawning = true;
        }
        Timeline delay = createPauseTimerTimeline(timer, new Duration(1000));

        this.setPos(10, respawnY + GameCanvas.diffHeight);

        delay.playFromStart();
        delay.setOnFinished(event -> {
            if (GameScene.onLvl1) {
                GameLoop.respawning = false;
            } else if (GameScene.onLvl2) {
                GameLoop2.respawning = false;
            } else if (GameScene.onLvl3) {
                GameLoop3.respawning = false;
            }
        });
    }

    /**
     * This method returns an integer array that is used to determine whether the user
     * is on a platform, in a platform, or above a platform. It also determines which platform
     * the user is on, in, or above.
     *
     * @param platforms
     * @return int[] the integer array containing the platform status
     */
    public int[] playerPlatformStatus(ArrayList<Sprite> platforms) {
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).intersects(this)) {
                return new int[]{1, i};
            }
            if ((new Character(this, new Vector2D(this.getPos().x, this.getPos().y + 2))).intersects(platforms.get(i))) {
                return new int[]{0, i};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * This method determines whether the player is fully on a platform.
     *
     * @param platforms The AnimationTimer to pause
     * @return A boolean indicating whether the player is fully on a platform
     */
    public boolean isFullyOnPlatform(ArrayList<Sprite> platforms) {
        Sprite temp = new Sprite(this);
        temp.setPos(temp.getPos().x, temp.getPos().y + 1);
        for (Sprite platform : platforms) {
            if (temp.intersects(platform) && this.getPos().x >= platform.getPos().x && this.getPos().x + this.getWidth() <= platform.getPos().x + platform.getWidth()) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method tells the Character to jump.
     */
    public void jump() {
        this.addVel(0, -7);
    }

    /**
     * This method applies gravity to the Character.
     *
     * @param currentTime The current time of the game
     * @param startGravityTime The time the Character jumped
     */
    public void applyGravity(long currentTime, long startGravityTime) {
        if (this.getVel().y <= 15) {
            this.addVel(0, 2 * ((currentTime - startGravityTime) / 1000000000.0));
        }
    }
}
