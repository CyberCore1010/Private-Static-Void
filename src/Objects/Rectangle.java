package Objects;

import java.awt.*;

public class Rectangle extends GameObject {
    private Color color;

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
    public Rectangle(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, Color color) {
        super(x, y, Z, width, height, id, blocking);
        this.color = color;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    @Override
    public java.awt.Rectangle getBounds() {
        return null;
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
