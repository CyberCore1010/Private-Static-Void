package Objects;

import java.awt.*;
import java.awt.Rectangle;

public abstract class GameObject {
    int x, y, Z, width, height;
    float velX = 0, velY = 0;
    private ObjectID id;
    private boolean blocking;

    Rectangle boundingBox;

    Backend.BufferedImageLoader loader = new Backend.BufferedImageLoader();

    public static Point mousePoint = new Point(0,0);

    public boolean isDead = false;

    /**This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     *
     * @param x         - x location of the object
     * @param y         - y location of the object
     * @param Z         - Z location of the object
     * @param width     - width of the object
     * @param height    - height of the object
     * @param id        - the ID of the object, stored in a enum
     * @param blocking  - if the object is a blocking one
     */
    public GameObject(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking) {
        this.x = x;
        this.y = y;
        this.Z = Z;
        this.width = width;
        this.height = height;
        this.id = id;
        this.blocking = blocking;
        boundingBox = new Rectangle(x, y, width, height);
    }

    /*
    These are all the abstract methods and other methods used in every game object.
     */
    public abstract void update();
    public abstract void render (Graphics g);
    public abstract java.awt.Rectangle getBounds();
    public abstract void activate();
    public abstract void reset();

    /// Getters and Setters start ///

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ObjectID getId() {
        return id;
    }

    public void setId(ObjectID id) { this.id = id; }

    public void setBlocking(boolean blocking) { this.blocking = blocking; }

    public boolean getBlocking() { return blocking; }

    /// Getters and Setters end ///
}
