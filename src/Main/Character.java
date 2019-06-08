package Main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.ietf.jgss.GSSManager;

import java.util.ArrayList;

public class Character extends Sprite {
    public Character(Image image, int width) {
        super(image, width);
    }

    public Character(Character c, Vector2D pos) {
        super(c.image, c.width);
        super.pos = pos;
    }

    public Timeline createPauseTimerTimeline(AnimationTimer timer, Duration duration) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> timer.stop()),
                new KeyFrame(duration, event -> timer.start())
        );
    }

    //TODO: the canvas param is only for testing
    public void respawn(Canvas canvas, int respawnX, int respawnY, AnimationTimer timer) {
        //TODO: make a flashing respawn - mario style!
        GameLoop.respawning = true;
        Timeline delay = createPauseTimerTimeline(timer, new Duration(1000));

        this.setPos(respawnX, respawnY + GameCanvas.diffHeight);
        this.setVel(3, 0);
        canvas.setTranslateX(10 - respawnX);

        delay.playFromStart();
        delay.setOnFinished(event -> GameLoop.respawning = false);
    }

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

    public void jump() { this.addVel(0, -10); }

    public void applyGravity(long currentTime, long startGravityTime) {
        if (this.getVel().y <= 15) {
            this.addVel(0, 6 * ((currentTime - startGravityTime) / 1000000000.0));
        }
    }
}
