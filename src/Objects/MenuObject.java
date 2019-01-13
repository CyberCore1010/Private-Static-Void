package Objects;

import Backend.SFXplayer;
import Game.GameHandler;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;

public class MenuObject extends GameObject {
    private ObjectHandler objectHandler;
    private String text;
    private Boolean active, hover = false, hoverClick = true, click = true;
    private Runnable runnable;

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
    public MenuObject(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler, String text, boolean active, Runnable runnable) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
        this.text = text;
        this.active = active;
        this.runnable = runnable;
    }

    @Override
    public void update() {
        if(GameHandler.level == 0 || objectHandler.isGamePaused()) {
            hover = mousePoint.x >= this.x && mousePoint.x <= this.x + this.width && mousePoint.y >= this.y && mousePoint.y <= this.y + this.height && active;
            if(hover && hoverClick) {
                hoverClick = false;
                SFXplayer.playSFX("ButtonHover");
            } else if(!hover && !hoverClick) {
                hoverClick = true;
                click = true;
            }
            if(hover && objectHandler.isMouseClicked()) {
                if(hover && click) {
                    click = false;
                    SFXplayer.playSFX("ButtonClick");
                } else if(!hover && !click) {
                    click = true;
                }
                runnable.run();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(GameHandler.level == 0 || objectHandler.isGamePaused()) {
            g.setColor(Color.red);
            if(GameView.debug) {
                g.drawRect(x, y, width, height);
            }
            if(active) {
                if(hover) {
                    g.drawString(text, x+15, (y+height)-10);
                    g.setColor(Color.blue);
                    g.drawString(text, x+25, (y+height)-10);
                }
                g.setColor(Color.white);
            } else {
                g.setColor(Color.darkGray);
            }
            g.setFont(GameView.MenuFont);
            g.drawString(text, x+20, (y+height)-10);
        }
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
