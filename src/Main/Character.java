package Main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Character extends Sprite {
    public Character(Image image, int width, Image erase) {
        super(image, width, erase);
    }

    //TODO: remove the time param, use delays to create the flashing animation
    public void respawn(double time, GraphicsContext gc) {
        this.erase(gc);
        this.setPos(20, 601 - this.getHeight());
        this.setVel(this.getVel().x, 0);
        if (time <= 0.25 || (time > 0.5 && time <= 0.75) || (time > 1 && time <= 1.25) || (time > 1.5 && time <= 1.75)) {
            this.render(gc);
        } else {
            this.erase(gc);
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {}
    }

    public int[] playerPlatformStatus(ArrayList<Sprite> platforms) {
        //TODO: make an ArrayList consisting of all the platforms touching the ground
        if (this.getPos().y + this.getHeight() == 601) {
            return new int[]{0, -1};
        }
        for (int i = 0; i < platforms.size(); i++) {
            if (platforms.get(i).intersects(this)) {
                return new int[]{1, i};
            }
            if (this.getPos().y + this.getHeight() + 1 == platforms.get(i).getPos().y && (this.getPos().x + this.getWidth() >= platforms.get(i).getPos().x && this.getPos().x <= platforms.get(i).getPos().x+platforms.get(i).getWidth())) {
                return new int[]{0, i};
            }
        }
        if (this.getPos().y + this.getHeight() > 601) {
            return new int[]{1, -1};
        }
        return new int[]{-1, -1};
    }

    public boolean hitObstacle(ArrayList<Sprite> obstacles) {
        for (Sprite s : obstacles) {
            if (this.intersects(s)) {
                return true;
            }
        }
        return false;
    }

    public void jump(GameCanvas canvas) {
        if (playerPlatformStatus(canvas.platforms)[0] == 0) {
            this.addVel(0, -15);
        }
    }

    public void update(GameCanvas canvas, GraphicsContext gc, long currentTime, long startGravityTime, long startTime) {
        if (startTime == currentTime) {
            this.addVel(3, 0);
        }

        if (playerPlatformStatus(canvas.platforms)[0] == -1) {
            this.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
        } else if (playerPlatformStatus(canvas.platforms)[0] == 1) {
            if (playerPlatformStatus(canvas.platforms)[1] != -1) {
                this.setPos(this.getPos().x, canvas.platforms.get(playerPlatformStatus(canvas.platforms)[1]).getPos().y - this.getHeight() - 1);
            } else {
                this.setPos(this.getPos().x, 601 - this.getHeight());
            }
        }
        this.update();
        this.render(gc);
    }
}
