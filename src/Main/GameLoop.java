package Main;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private GameCanvas canvas;
    static boolean jump = false;
    static boolean respawning = true;
    private long startGravityTime = -1;
    private long startTime = -1;
    private int respawnX = 1650;
    private int respawnY;

    public GameLoop(GameCanvas canvas) {
        this.start();
        this.canvas = canvas;
        // normally 550 - canvas.player.getHeight()
        respawnY = 410 - canvas.player.getHeight();
    }

    @Override
    public void handle(long currentTime) {
        if (!canvas.pause) {
            //TODO: Scroll world up and down
            if (startTime == -1) {
                startTime = currentTime;
                startGravityTime = currentTime;
                canvas.player.respawn(canvas, respawnX, respawnY, this);
                canvas.background.setPos(respawnX - 10, 0);
                canvas.background.setVel(2.85, 0);
                canvas.setTranslateY(-GameCanvas.diffHeight);
            }

            if (canvas.player.getPos().x >= 200 + respawnX) {
                canvas.setTranslateX(canvas.getTranslateX() - canvas.player.getVel().x);
                canvas.background.update();
                canvas.background.render(canvas.gc);
            }

            if (canvas.player.getPos().y <= 300 + GameCanvas.diffHeight) {
                canvas.setTranslateY(300 - canvas.player.getPos().y);
            } else {
                canvas.setTranslateY(-GameCanvas.diffHeight);
            }

            canvas.player.erase(canvas.gc);

            if (canvas.player.playerPlatformStatus(canvas.platforms)[0] == 0) {
                startGravityTime = currentTime;
            }
            //Run if player is floating above a platform
            if (canvas.player.playerPlatformStatus(canvas.platforms)[0] == -1) {
                canvas.player.applyGravity(currentTime, startGravityTime);
            }

            //Rotate all spinning sprites
            canvas.rotateSpinningSprites();

            //More collision detection. Yay! For obstacles
            for (Sprite obstacle : canvas.obstacles) {
                if (canvas.player.intersects(obstacle)) {
                    canvas.setTranslateX(0);
                    canvas.background.setPos(0, 0);
                    canvas.player.respawn(canvas, respawnX, respawnY, this);
                    canvas.background.setPos(respawnX - 10, 0);
                    respawning = true;
                    jump = false;
                    break;
                }
            }
            for (SpinningSprite spinningObstacle : canvas.spinningObstacles) {
                if (canvas.player.intersects(spinningObstacle)) {
                    canvas.setTranslateX(0);
                    canvas.background.setPos(0, 0);
                    canvas.player.respawn(canvas, respawnX, respawnY, this);
                    canvas.background.setPos(respawnX - 10, 0);
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
