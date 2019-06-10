package Main;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * the sprite class
 *
 * @author Josh Friedman
 * @version 1 - Josh Friedman - June 2 - 1 hour - coded the entire class
 * @version 2 - Josh Friedman - June 5 - 30 mins - added the originalPos variable
 *
 * Variable                 Type                    Description
 * ________________________________________________________________________
 *image                     Image                   the image for the sprite
 * originalPos              Vector2D                the original position of the sprite
 * pos                      Vector2D                the position of the sprite
 * vel                      Vector2D                the velocity of the sprite
 * width                    int                     the width of the sprite
 * height                   int                     the height of the sprite
 */
public class Sprite {
    public Image image;
    public Vector2D originalPos;
    public Vector2D pos;
    public Vector2D vel;
    public int width;
    public int height;

    /**
     * constructor that has customized width and height
     * @param image image for the sprite
     * @param width width of the sprite
     * @param height height of the sprite
     */
    public Sprite(Image image, int width, int height) {
        this.image = image;
        pos = new Vector2D(0, 0);
        vel = new Vector2D(0, 0);
        this.width = width;
        this.height = height;
    }

    /**
     * constructor that sets width and keeps ratio
     * @param image image for the sprite
     * @param width width of the sprite
     */
    public Sprite(Image image, int width) {
        this(image, width, (int) Math.round(image.getHeight() / (image.getWidth() / width)));
    }

    /**
     * constructor that sets height and keeps ratio
     * @param height height of the sprite
     * @param image image for the sprite
     */
    public Sprite(int height, Image image) {
        this(image, (int) Math.round(image.getWidth() / (image.getHeight() / height)), height);
    }

    /**
     * constructor that clones sprite
     * @param another the other sprite
     */
    public Sprite(Sprite another) {
        image = another.image;
        pos = new Vector2D(another.getPos().x, another.getPos().y);
        vel = new Vector2D(another.getVel().x, another.getVel().y);
        width = another.width;
        height = another.height;
    }

    /**
     * sets the position of the sprite
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPos(double x, double y) {
        pos.set(x, y);
    }

    /**
     * adds a velocity to the sprite
     * @param x x component
     * @param y y component
     */
    public void addVel(double x, double y) {
        vel.add(new Vector2D(x, y));
    }

    /**
     * gets the velocity of the sprite
     * @return Vector2D
     */
    public Vector2D getVel() { return vel; }

    /**
     * sets the velocity of the sprite
     * @param x x component
     * @param y y component
     */
    public void setVel(double x, double y) {
        vel.set(x, y);
    }

    /**
     * updates the position of the sprite
     */
    public void update() {
        pos.add(vel);
    }

    /**
     * gets the position of the sprite
     * @return Vector2D
     */
    public Vector2D getPos() {
        return pos;
    }

    /**
     * gets the height of the sprite
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * gets the width of the sprite
     * @return int
     */
    public int getWidth() { return width; }

    /**
     * gets the original position of the sprite
     * @return Vector2D
     */
    public Vector2D getOriginalPos() {
        return originalPos;
    }

    /**
     * sets the original position of the sprite
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setOriginalPos(double x, double y) {
        this.originalPos = new Vector2D(x,y);
    }

    /**
     * resets the position of the sprite
     */
    public void resetPos() {
        this.pos = new Vector2D(this.originalPos.x, this.originalPos.y);
    }

    /**
     * renders the sprite
     * @param gc the GraphicsContext to render on
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(image, pos.x, pos.y, width, height);
    }

    /**
     * erases the sprite
     * @param gc the GraphicsContext to erase on
     */
    public void erase(GraphicsContext gc) {
        gc.clearRect(pos.x, pos.y, width, height);
    }

    /**
     * gets the boundary of the sprite
     * @return Rectangle2D
     */
    private Rectangle2D getBoundary() {
        return new Rectangle2D(pos.x, pos.y, width, height);
    }

    /**
     * determines whether this sprite intersects with another one
     * @param s the other sprite
     * @return boolean
     */
    public boolean intersects(Sprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }
}
