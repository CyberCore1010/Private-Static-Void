package Objects;

import Backend.Window;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;

public class Fade extends GameObject {
    ObjectHandler objectHandler;
    Boolean fadeOut;
    float alpha = 1.0f;

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
    public Fade(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler, boolean fadeOut) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
        this.fadeOut = fadeOut;
        if(fadeOut) {
            alpha = 0.1f;
        }
    }

    @Override
    public void update() {
        this.x = (int)GameView.camera.getX()-Window.gameWidth/2;
        if(!fadeOut) {
            if(!(alpha <= 0.5f)) {
                alpha -= 0.005f;
            }
        } else {
            if(!(alpha >= 1.0f))
            alpha += 0.005f;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0, alpha));
        g.fillRect(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
