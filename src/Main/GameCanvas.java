package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameCanvas extends Canvas {
    GraphicsContext gc;
    boolean pause;
    boolean respawn;
    Character player;
    SpinningSprite rotating_blade;
    double[] xVals = {435, 455, 445};
    double[] yVals = {600, 600, 570};
    double[] xVals1 = {455, 475, 465};
    double[] xVals2 = {475, 495, 485};
    Sprite platform1;
    Sprite platform2;
    Sprite platform3;
    Sprite platform4;
    Sprite spike1;
    Sprite spike2;
    Sprite spike3;
    Sprite spike4;

    ArrayList<Sprite> platforms = new ArrayList<Sprite>();
    ArrayList<Sprite> obstacles = new ArrayList<Sprite>();

    public GameCanvas(int width, int height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        loadResources();

        obstacles.add(spike1);
        obstacles.add(spike2);
        obstacles.add(spike3);
        obstacles.add(spike4);
        obstacles.add(rotating_blade);

        platforms.add(platform1);
        platforms.add(platform2);
        platforms.add(platform3);
        platforms.add(platform4);

        player.setPos(20, 601 - player.getHeight());
        player.render(gc);

        platform1.setPos(295, 530);
        platform2.setPos(390, 470);
        platform3.setPos(485, 410);
        platform4.setPos(200, 575);

        spike1.setPos(362, 510);
        spike2.setPos(457, 450);
        spike3.setPos(552, 390);
        spike4.setPos(262, 555);

        drawWorld();

        rotating_blade.setPos(174, 575);
        rotating_blade.render(gc);
    }

    public void loadResources() {
        try {
            player = new Character(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\running_man.png")), 40, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\running_man - erase.png")));
            platform1 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.png")));
            platform2 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.PNG")));
            platform3 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform.PNG")), 80, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform - erase.PNG")));
            platform4 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform_1.PNG")), 75, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\platforms\\platform_1 - erase.PNG")));
            spike1 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike2 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike3 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            spike4 = new Sprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike.png")), 13, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\spikes\\single_spike - erase.png")));
            rotating_blade = new SpinningSprite(new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\rotating_blades\\blade_1.png")), 25, new Image(new FileInputStream("D:\\Documents\\School Stuff\\Grade 10\\ICS 12\\ICS ISP\\Friend-Racer\\res\\rotating_blades\\blade_1 - erase.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawWorld() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.RED);

        platform1.render(gc);
        platform2.render(gc);
        platform3.render(gc);
        platform4.render(gc);

        spike1.render(gc);
        spike2.render(gc);
        spike3.render(gc);
        spike4.render(gc);

        rotating_blade.render(gc);

        gc.fillPolygon(xVals, yVals, 3);
        gc.fillPolygon(xVals1, yVals, 3);
        gc.fillPolygon(xVals2, yVals, 3);
    }

    //TODO: make the pause method so that when they press escape, there is
    // a pause screen with settings, instructions, exit, etc. and when they press escape again, there is a 3-2-1 countdown
    // before the game continues.
    public void pause() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.GREEN);
        gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
        gc.setFill(Color.RED);
    }
}
