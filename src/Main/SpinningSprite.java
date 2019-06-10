package Main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;

/**
 * This class is for spinning sprites
 *
 * @author Om Patel
 * @version 1 - June 5 - Om Patel - 30 mins - coded the entire class
 * @version 2 - June 8 - Om Patel - 20 mins - updated the rotation method
 *
 * Variable         Type            Description
 * ____________________________________________________
 *rotationDirection int             the direction of spin
 * curDegrees       int             the current degrees of rotation
 */
public class SpinningSprite extends Sprite {
    private int rotationDirection;
    private int curDegrees = 0;

    /**
     * the class constructor
     *
     * @param image the image for the sprite
     * @param width the width of the sprite
     * @param rotationDirection the spin direction
     */
    public SpinningSprite(Image image, int width, int rotationDirection) {
        super(image, width);
        this.rotationDirection = rotationDirection;
    }

    /**
     * rotates the sprite
     * @param gc the graphicsContext to rotate
     * @param speed the speed of rotation
     */
    public void rotateImage(GraphicsContext gc, double speed) {
        gc.save();
        gc.translate(super.pos.x + super.width/2.0, super.pos.y + super.height/2.0);
        curDegrees += speed;
        gc.rotate(curDegrees);
        gc.drawImage(super.image, -super.width/2.0, -super.height/2.0, super.width, super.height);
        gc.restore();
    }

    /**
     * gets the direction of spin
     * @return rotationDirection
     */
    public int getRotationDirection() {
        return rotationDirection;
    }
}
