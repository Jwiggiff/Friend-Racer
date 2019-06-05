package Main;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameCanvas canvas;
    static boolean jump = false;
    static boolean respawning = true;
    private long startGravityTime = -1;
    private long startTime = -1;

    public GameLoop(GameCanvas canvas) {
        this.start();
        this.canvas = canvas;
    }

    @Override
    public void handle(long currentTime) {
        if (!canvas.pause) {
            //TODO: Scroll world

            if (startTime == -1) {
                startTime = currentTime;
                canvas.player.addVel(3, 0);
            }

            if (canvas.player.getPos().x >= 200) {
                canvas.setTranslateX(canvas.getTranslateX() - 3);
            }

            //Run first frame only
            if (startGravityTime == -1) {
                startGravityTime = currentTime;
                canvas.player.respawn(canvas.gc, this);
            }

            canvas.player.erase(canvas.gc);

            //Run if player is floating above a platform
            if (canvas.player.playerPlatformStatus(canvas.platforms)[0] == -1) {
                canvas.player.applyGravity(currentTime, startGravityTime);
            }

            //Rotate all spinning sprites
            canvas.rotateSpinningSprites();

            //Collision detection for platforms
            for (Sprite platform : canvas.platforms) {
                if (canvas.player.intersects(platform)) {
                    canvas.player.setPos(canvas.player.getPos().x, platform.getPos().y - canvas.player.getHeight());
                    canvas.player.setVel(3, 0);
                    break;
                }
            }

            //More collision detection. Yay! For obstacles
            for (Sprite obstacle : canvas.obstacles) {
                if (canvas.player.intersects(obstacle)) {
                    canvas.setTranslateX(0);
                    canvas.player.respawn(canvas.gc, this);
                    respawning = true;
                    jump = false;
                    break;
                }
            }
            for (SpinningSprite spinningObstacle : canvas.spinningObstacles) {
                if (canvas.player.intersects(spinningObstacle)) {
                    canvas.setTranslateX(0);
                    canvas.player.respawn(canvas.gc, this);
                    respawning = true;
                    jump = false;
                    break;
                }
            }

            //Run on each jump
            if (jump && !respawning) {
                startGravityTime = currentTime;
                canvas.player.jump();
                jump = false;
            }

            //Draw world
            canvas.drawWorld();

            //Update player
            canvas.player.update();

            //Render player
            canvas.player.render(canvas.gc);
        }
    }
}
