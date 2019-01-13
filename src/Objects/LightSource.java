package Objects;

import Backend.SFXplayer;
import Backend.Window;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

public class LightSource extends GameObject {
    private Point2D center;
    @SuppressWarnings("FieldCanBeLocal")
    private float radius = 400f;
    private Color color;

    private boolean doOnce = true;

    private ObjectHandler objectHandler;

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
    public LightSource(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler, Color color) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
        this.color = color;
        center = new Point2D.Float(x, y);
    }

    @Override
    public void update() {
        if(getId() == ObjectID.playerLight) {
            if(objectHandler.isVisible()) {
                if(doOnce) {
                    doOnce = false;
                    color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
                }

                for(GameObject object : objectHandler.object) {
                    if(object.getId() == ObjectID.player) {
                        x = object.x+(object.width/2);
                        y = object.y+(object.height/2);
                        center.setLocation(x, y);
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        float[] distance = {0.0f, 1.0f};
        Color[] colors = {color, Color.BLACK};
        RadialGradientPaint p = new RadialGradientPaint(center, radius, distance, colors);
        g2d.setPaint(p);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.992f));
        g2d.fillRect((int)GameView.camera.getX()-100, (int)GameView.camera.getY(), Backend.Window.gameWidth+200, Window.gameHeight);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void activate() {
        if(getId() == ObjectID.playerLight) {
            if(color.getAlpha() == 255) {
                SFXplayer.playSFX("ButtonClick");
                color = new Color(0.0f, 0.0f, 0.0f, 0.0f);
            } else {
                SFXplayer.playSFX("ButtonHover");
                color = new Color(0.0f, 0.0f, 0.0f, 1.0f);
            }
        }
    }

    @Override
    public void reset() {

    }
}
