package Objects;

import Backend.SFXplayer;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuController extends GameObject {
    int faceTime = 20, staticTime = 0;
    BufferedImage Static;

    ObjectHandler objectHandler;

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
    public MenuController(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
        boundingBox = new Rectangle(x, y, width, height);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void update() {
        int randomNum = (int)(Math.random()*((500) + 1));
        if(randomNum == 0) {
            faceTime = 30;
        }
        if(faceTime > 0) {
            faceTime--;
        }

        if (staticTime == 0 || staticTime == 20) {
            Static = objectHandler.static0;
            staticTime = 0;
        } else if(staticTime == 5) {
            Static = objectHandler.static1;
        } else if(staticTime == 10) {
            Static = objectHandler.static2;
        } else if(staticTime == 15) {
            Static = objectHandler.static3;
        }
        staticTime++;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        if(faceTime > 0) {
            if(faceTime == 1) {
                int random = 1 + (int)(Math.random() * ((8 - 1)+1));
                SFXplayer.playSFX("Glitch"+random);
            }
            g.drawImage(objectHandler.menuBackground,x, y, width, height, null);
            g.setColor(new Color(0, 0, 0, 0.9f));
            g.fillRect(x, y, width, height);
            if(faceTime <= 5 || faceTime >= 25) {
                g.setColor(Color.white);
                g.fillRect(x, y, width, height);
            }
        }

        if(objectHandler.isOptionsMenu()) {
            g.drawImage(objectHandler.menuOptions, width-500, height-400, 400, 300, null);
        }

        g.drawImage(Static, x, y, width, height, null);

        g.drawImage(objectHandler.screenDirt, x, y, width, height, null);

        g.setFont(GameView.TitleFont);
        g.setColor(Color.WHITE);
        g.drawString("Private Static", 100, 200);
        g.drawString("Voi", 100, 300);
        g.drawString("d", 270, 300);
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
