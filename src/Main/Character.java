package Main;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

    public void respawn(GraphicsContext gc, AnimationTimer timer) {
        //TODO: make a flashing respawn - mario style!
        GameLoop.respawning = true;
        Timeline delay = createPauseTimerTimeline(timer, new Duration(250));

        this.setPos(20, 550 - this.getHeight());
        this.setVel(3, 0);
        this.render(gc);
        delay.playFromStart();
        delay.setOnFinished(event -> {
            this.setPos(20, 550 - this.getHeight());
            //this.setVel(0, 0);
            this.erase(gc);
            Timeline delay1 = createPauseTimerTimeline(timer, new Duration(250));
            delay1.playFromStart();
            delay1.setOnFinished(event1 -> {
                this.setPos(20, 550 - this.getHeight());
                //this.setVel(0, 0);
                this.render(gc);
                Timeline delay2 = createPauseTimerTimeline(timer, new Duration(250));
                delay2.playFromStart();
                delay2.setOnFinished(event2 -> {
                    this.setPos(20, 550 - this.getHeight());
                    //this.setVel(0, 0);
                    this.erase(gc);
                    Timeline delay3 = createPauseTimerTimeline(timer, new Duration(250));
                    delay3.playFromStart();
                    delay3.setOnFinished(event3 -> GameLoop.respawning = false);
                });
            });
        });
    }

    public int[] playerPlatformStatus(ArrayList<Sprite> platforms) {
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).intersects(this)) {
                return new int[]{1, i};
            }
            if ((new Character(this, new Vector2D(this.getPos().x, this.getPos().y + 1))).intersects(platforms.get(i))) {
                return new int[]{0, i};
            }
        }
        return new int[]{-1, -1};
    }

    public void jump() { this.setVel(3, -15); }

    public void applyGravity(long currentTime, long startGravityTime) {
        this.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
    }
}
