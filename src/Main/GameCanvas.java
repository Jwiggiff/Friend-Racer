package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.util.ArrayList;

public class GameCanvas extends Canvas {
    //TODO: make all instance variables private and make getters and setters
    public GraphicsContext gc;
    public boolean pause;
    public boolean returnToGame;
    public Character player;
    public SpinningSprite rotating_blade;
    private double[] xVals = {435, 455, 445};
    private double[] yVals = {600, 600, 570};
    private double[] xVals1 = {455, 475, 465};
    private double[] xVals2 = {475, 495, 485};
    private Sprite platform1;
    private Sprite platform2;
    private Sprite platform3;
    private Sprite platform4;
    private Sprite spike1;
    private Sprite spike2;
    private Sprite spike3;
    private Sprite spike4;
    private Sprite ground;

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

        ground = new Sprite(new Image("./platforms/platform_1.PNG"), 800, 1);
        platforms.add(ground);
        ground.setPos(0, 601);


        player.setPos(20, 600 - player.getHeight());
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
            player = new Character(new Image("./running_man.png"), 40);
            platform1 = new Sprite(new Image("./platforms/platform.PNG"), 80);
            platform2 = new Sprite(new Image("./platforms/platform.PNG"), 80);
            platform3 = new Sprite(new Image("./platforms/platform.PNG"), 80);
            platform4 = new Sprite(new Image("./platforms/platform_1.PNG"), 75);
            spike1 = new Sprite(new Image("./spikes/single_spike.png"), 13);
            spike2 = new Sprite(new Image("./spikes/single_spike.png"), 13);
            spike3 = new Sprite(new Image("./spikes/single_spike.png"), 13);
            spike4 = new Sprite(new Image("./spikes/single_spike.png"), 13);
            rotating_blade = new SpinningSprite(new Image("./rotating_blades/blade_1.png"), 25);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawWorld() {
        gc.clearRect(0,0,800,600);
        gc.setFill(Color.RED);

        platform1.render(gc);
        platform2.render(gc);
        platform3.render(gc);
        platform4.render(gc);

        spike1.render(gc);
        spike2.render(gc);
        spike3.render(gc);
        spike4.render(gc);

        ground.render(gc);

        rotating_blade.render(gc);

        gc.fillPolygon(xVals, yVals, 3);
        gc.fillPolygon(xVals1, yVals, 3);
        gc.fillPolygon(xVals2, yVals, 3);
    }

    public void pause() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFill(Color.GREEN);
        gc.fillText("GAME IS PAUSED, PRESS \'r\' TO CONTINUE", 200, 200);
        gc.setFill(Color.RED);
        //TODO: make a pause and countdown 3-2-1
    }

    public void returnToGame() {
        pause = false;
        returnToGame = false;
    }
}
