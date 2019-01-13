package Objects;

import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;

public class Blocker extends GameObject {
    /**
     * This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     *
     * @param x        - x location of the object
     * @param y        - y location of the object
     * @param Z        - Z location of the object
     * @param width    - width of the object
     * @param height   - height of the object
     * @param id       - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public Blocker(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking) {
        super(x, y, Z, width, height, id, blocking);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        if(GameView.debug) {
            g.setColor(Color.red);
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
