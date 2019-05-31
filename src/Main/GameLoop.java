package Main;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    long startTime = -1;
    long startGravityTime = -1;
    //TODO: do the 3-2-1 countdown when returning to the game after pausing
    long startReturnCountdown = -1;

    static boolean jump = false;
    GameCanvas canvas;

    public GameLoop(GameCanvas canvas) {
        this.canvas = canvas;
        this.start();
    }

    @Override
    public void handle(long currentTime) {
        //TODO: Scroll world
        if (startTime == -1) {
            startTime = currentTime;
        }
        if (jump) {
            startGravityTime = currentTime;
            canvas.player.jump(canvas);
            jump = false;
        }
        if (canvas.player.hitObstacle(canvas.obstacles)) {
            canvas.player.respawn(0, canvas.gc);
        }

        //Rotate all spinning sprites
        //TODO: make a method to rotate all spinning sprites (which are stored in their own ArrayList).
        canvas.rotating_blade.rotateImage(canvas.gc, 4);

        //Draw world
        canvas.drawWorld();

        //update player
        canvas.player.update(canvas, canvas.gc, currentTime, startGravityTime, startTime);
    }

        /*
        if (!pause) {
            if (respawn && (currentTime - startRespawnDelay) / 1000000000.0 <= 2) {
                respawn((currentTime - startRespawnDelay) / 1000000000.0);
            } else {
                if (respawn) {
                    respawn = false;
                }
                if (startTime == -1)
                    startTime = currentTime;
                player.erase(gc);
                if (jump && playerPlatformStatus()[0] == -1) {
                    jump = false;
                }
                if (jump && playerPlatformStatus()[0] == 0) {
                    player.addVel(0, -15);
                    jump = false;
                    startGravityTime = currentTime;
                }
                if (startTime == currentTime) {
                    player.addVel(3, 0);
                }
                if (playerPlatformStatus()[0] < 0) {
                    player.addVel(0, (int) Math.round(9.8 * ((currentTime - startGravityTime) / 1000000000.0)));
                }
                player.update();
                if (playerPlatformStatus()[0] == 1) {
                    if (playerPlatformStatus()[1] == -1) {
                        player.setPos(player.getPos().x, 601 - player.getHeight());
                    } else {
                        player.setPos(player.getPos().x, platforms.get(playerPlatformStatus()[1]).getPos().y - player.getHeight() - 1);
                    }
                    player.setVel(player.getVel().x, 0);
                    drawWorld();
                    rotating_blade.render(gc);
                }
                player.render(gc);
                if (hitObstacle()) {
                    respawn = true;
                    startRespawnDelay = currentTime;
                    player.erase(gc);
                    drawWorld();
                    rotating_blade.render(gc);
                }
            }
        }
        */
}

